import { Injectable } from '@angular/core';

import { Record, AuthorInterface, Order } from '../common.models';

// import { Response, RequestOptions,
// 	Headers, URLSearchParams } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { HttpClient } from '@angular/common/http';
import { RequestOptions } from '@angular/http';
import { HttpParams } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { URLSearchParams } from '@angular/http/src/url_search_params';

@Injectable()
export class RecordService {

	private readonly path = 'http://localhost:8080/api/records';

	constructor(private http: HttpClient) { }

	getRecords(order?: Order, lowestPrice?: number, highestPrice?: number): Observable<Record[]> {
		let params: HttpParams = new HttpParams();
		if (+lowestPrice) {
			// HttpParams je imutabilna kolekcija!
			params = params.append('lowestPrice', lowestPrice.toString());
		}
		if (+highestPrice){
			params = params.append('highestPrice', highestPrice.toString());
		}
		if (order !== undefined) {
			params = params.append('ord', order.toString());
		}
		// let options = new RequestOptions({ search: params });
		return this.http.get<Record[]>(this.path, {params})
			.catch((error: any) =>
				Observable.throw(error.message || 'Server error')
			);
	}

	getRecord(id: string): Observable<Record> {
		return this.http.get<Record>(`${this.path}/${id}`)
			.catch((error: any) => Observable.throw(error.message || 'Server error'));
	}

	saveRecord(newRecord: Record): Observable<Record> {
		let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
		// let options = new RequestOptions({ headers: headers });
		return this.http.
			post(this.path, JSON.stringify(newRecord), { headers }).
			catch((error: any) => Observable.throw(error.message || 'Server error'));
	}

	deleteRecord(id: string): Observable<Record> {
		return this.http.delete(`${this.path}/${id}`).
			catch((error: any) =>
				Observable.throw(error.message || 'Server error')
			);
	}
}
