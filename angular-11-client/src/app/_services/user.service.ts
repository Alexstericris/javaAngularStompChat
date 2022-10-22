import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../app.types';

const API_TEST_URL = 'http://localhost:8080/api/test/';
const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) {
  }

  getPublicContent(): Observable<any> {
    return this.http.get(API_TEST_URL + 'all', {responseType: 'text'});
  }

  getUserBoard(): Observable<any> {
    return this.http.get(API_TEST_URL + 'user', {responseType: 'text'});
  }

  getModeratorBoard(): Observable<any> {
    return this.http.get(API_TEST_URL + 'mod', {responseType: 'text'});
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_TEST_URL + 'admin', {responseType: 'text'});
  }

  getUserMessages(userId: number): Observable<any> {
    return this.http.get(API_URL + 'user/messages', {
      params: new HttpParams().append('userId', String(userId))
    });
  }

  getUserChats(userId: number): Observable<any> {
    return this.http.get(API_URL + 'user/chats', {
      params: new HttpParams().append('userId', String(userId))
    });
  }

  getChattingUsers(userId: number): Observable<any> {
    return this.http.get(API_URL + 'user/chats/users', {
      params: new HttpParams().append('userId', String(userId))
    });
  }

}
