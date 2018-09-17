import { Injectable } from '@angular/core';

import { User, TransactionInterface } from '../common.models';

import { Observable } from 'rxjs/Rx';
import { HttpClient } from '@angular/common/http';
import { HttpParams } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

@Injectable()
export class UserService {

  private readonly path = 'http://localhost:8080/api/users';
  private readonly transactionsPath = 'http://localhost:8080/api/transactions';

  constructor(private http: HttpClient) { }

  getUser(username: string): Observable<User> {
    return this.http.get<User>(`${this.path}/username/${username}`)
      .catch((error: any) => Observable.throw(error.message || 'Server error'));
  }

  saveUser(user: User): Observable<User> {
    let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put(`http://localhost:8080/api/teachers/${user.id}`, JSON.stringify(user), { headers })
    .catch((error: any) => Observable.throw(error.message || 'Server error'));
  }

  getTransactions(): Observable<TransactionInterface[]> {
    return this.http.get<TransactionInterface>(`${this.transactionsPath}`)
    .catch((error: any) => Observable.throw(error.message || 'Server error'));
  }
}
