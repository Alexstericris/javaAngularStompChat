import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {API_URL} from './constants';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  constructor(private http: HttpClient) {
  }

  startNewChat(username: string): Observable<any> {
    return this.http.post(API_URL + 'user/chats/new', {username});
  }

  postMessage(chatId: number, message: string): Observable<any> {
    return this.http.post(API_URL + 'user/chat/message', {chatId, message});
  }
}
