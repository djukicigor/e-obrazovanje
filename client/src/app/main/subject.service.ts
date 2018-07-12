import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { SubjectInterface, PassingExamsInterface } from '../common.models';

@Injectable()
export class SubjectService {
  private readonly path = 'http://localhost:8080/api/subjects';
  private readonly examsPath = 'http://localhost:8080/api/students/exams';
  private readonly passingExamsPath = 'http://localhost:8080/api/passingexams';

  constructor(private http: HttpClient) { }

  getSubject(id: string): Observable<SubjectInterface> {
		return this.http.get<SubjectInterface>(`${this.path}/${id}`)
			.catch((error: any) => Observable.throw(error.message || 'Server error'));
  }

  saveSubject(subject: SubjectInterface): Observable<SubjectInterface> {
    let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put(`${this.path}/${subject.id}`, JSON.stringify(subject), { headers })
    .catch((error: any) => Observable.throw(error.message || 'Server error'));
  }

  getExams(id: number): Observable<PassingExamsInterface[]> {
		return this.http.get<PassingExamsInterface[]>(`${this.examsPath}/${id}`)
			.catch((error: any) => Observable.throw(error.message || 'Server error'));
  }


  getExam(id: number): Observable<PassingExamsInterface> {
		return this.http.get<PassingExamsInterface>(`${this.examsPath}/${id}`)
			.catch((error: any) => Observable.throw(error.message || 'Server error'));
  }

  applyExam(passingExam: PassingExamsInterface): Observable<PassingExamsInterface> {
    let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    console.log(JSON.stringify(passingExam))
    return this.http.put(`${this.passingExamsPath}/${passingExam.id}`, JSON.stringify(passingExam), { headers })
      .catch((error: any) => Observable.throw(error.message || 'Server error'));
  }

}
