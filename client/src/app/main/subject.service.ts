import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { SubjectInterface } from '../common.models';

@Injectable()
export class SubjectService {
  private readonly path = 'http://localhost:8080/api/subjects';

  constructor(private http: HttpClient) { }

  getSubject(id: string): Observable<SubjectInterface> {
		return this.http.get<SubjectInterface>(`${this.path}/${id}`)
			.catch((error: any) => Observable.throw(error.message || 'Server error'));
  }

  saveSubject(subject: SubjectInterface): Observable<SubjectInterface> {
    let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    console.log(JSON.stringify(subject))
    return this.http.put(`${this.path}/${subject.id}`, JSON.stringify(subject), { headers })
    .catch((error: any) => Observable.throw(error.message || 'Server error'));
  }

}
