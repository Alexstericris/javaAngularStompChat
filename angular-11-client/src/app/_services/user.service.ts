import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../app.types';
import {API_TEST_URL, API_URL, AUTH_URL} from './constants';

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
    return this.http.get(AUTH_URL + 'chats', {
      params: new HttpParams().append('userId', String(userId))
    });
  }

  getChattingUsers(userId: number): Observable<any> {
    return this.http.get(AUTH_URL + 'chats/users', {
      params: new HttpParams().append('userId', String(userId))
    });
  }

  getUsers(): Observable<any> {
    return this.http.get(AUTH_URL + 'users');
  }
}
