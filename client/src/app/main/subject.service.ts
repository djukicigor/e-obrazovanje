import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { HttpClient } from '@angular/common/http';

import { SubjectInterface } from '../common.models';

@Injectable()
export class SubjectService {
  private readonly path = 'http://localhost:8080/api/subjects';

  constructor(private http: HttpClient) { }

  getSubject(id: string): Observable<SubjectInterface> {
		return this.http.get<SubjectInterface>(`${this.path}/${id}`)
			.catch((error: any) => Observable.throw(error.message || 'Server error'));
	}

}
