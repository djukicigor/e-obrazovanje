import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

import { SubjectInterface, PassingExamsInterface, SubjectLecturesInterface, PassedExamInterface, User } from '../common.models';
import { AuthenticationService } from '../security/authentication.service';

@Injectable()
export class SubjectService {
  private readonly path = 'http://localhost:8080/api/subjects';
  private readonly examsPath = 'http://localhost:8080/api/students/exams';
  private readonly passingExamsPath = 'http://localhost:8080/api/passingexams';
  private readonly pasedExamsPath = 'http://localhost:8080/api/passedexams';

  constructor(private http: HttpClient, private authService: AuthenticationService) { }

  getSubjects(): Observable<SubjectInterface[]> {
    return this.http.get<SubjectInterface[]>(`${this.path}`)
			.catch((error: any) => Observable.throw(error.message || 'Server error'));
  }

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
    if (this.authService.isAdmin()) {
      return this.http.get<PassingExamsInterface[]>(`${this.passingExamsPath}`)
			  .catch((error: any) => Observable.throw(error.message || 'Server error'));
    }
		return this.http.get<PassingExamsInterface[]>(`${this.examsPath}/${id}`)
			.catch((error: any) => Observable.throw(error.message || 'Server error'));
  }


  getExam(id: number): Observable<PassingExamsInterface> {
		return this.http.get<PassingExamsInterface>(`${this.examsPath}/${id}`)
			.catch((error: any) => Observable.throw(error.message || 'Server error'));
  }

  applyExam(passingExam: PassingExamsInterface, id: Number): Observable<PassingExamsInterface> {
    let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put(`${this.passingExamsPath}/${id}`, JSON.stringify(passingExam), { headers })
      .catch((error: any) => Observable.throw(error.message || 'Server error'));
  }

  addExam(lectureExam: SubjectLecturesInterface): Observable<SubjectLecturesInterface> {
    let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    lectureExam.id = null;
    lectureExam.teacher.subjectLectures = [];
    return this.http.post(`${this.passingExamsPath}`, JSON.stringify(lectureExam), { headers })
      .catch((error: any) => Observable.throw(error.message || 'Server error'));
  }

  saveResults(passedExam: PassedExamInterface, student: User): Observable<PassedExamInterface> {
    let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.pasedExamsPath}/${student.id}`, JSON.stringify(passedExam), { headers })
      .catch((error: any) => Observable.throw(error.message || 'Server error'));
  }

}
