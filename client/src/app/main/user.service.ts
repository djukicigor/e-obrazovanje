import { Injectable } from '@angular/core';

import { User } from '../common.models';

import { Observable } from 'rxjs/Rx';
import { HttpClient } from '@angular/common/http';
import { HttpParams } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

@Injectable()
export class UserService {

  private readonly path = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) { }

  getUser(username: string): Observable<User> {
    return this.http.get<User>(`${this.path}/username/${username}`)
      .catch((error: any) => Observable.throw(error.message || 'Server error'));
  }

}
