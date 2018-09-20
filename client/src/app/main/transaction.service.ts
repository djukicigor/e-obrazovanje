import { Injectable } from '@angular/core';

import { TransactionInterface } from '../common.models';

import { Observable } from 'rxjs/Rx';
import { HttpClient } from '@angular/common/http';
import { HttpParams } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

@Injectable()
export class TransactionService {

  private readonly path = 'http://localhost:8080/api/transactions';

  constructor(private http: HttpClient) { }

  addTransaction(transaction: TransactionInterface, studentId: number): Observable<TransactionInterface> {
    let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    console.log(transaction);
    transaction.id = null;
    return this.http.post(`${this.path}/${studentId}`, JSON.stringify(transaction), { headers })
      .catch((error: any) => Observable.throw(error.message || 'Server error'));
  }
}
