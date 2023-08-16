import {EventEmitter, Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {API_URL, AUTH_API} from './constants';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  // observe: 'response',
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isLoggedIn = new EventEmitter<boolean>();
  constructor(private http: HttpClient) { }

  login(email: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'login', {
      email,
      password
    }, httpOptions);
  }

  register(name: string, email: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'register', {
      name,
      email,
      password
    }, httpOptions);
  }
}
