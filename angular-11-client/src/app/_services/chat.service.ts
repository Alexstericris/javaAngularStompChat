import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {API_URL, AUTH_URL} from './constants';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  constructor(private http: HttpClient) {
  }

  startNewChat(from_user_id: number, with_user_id: number, name?: string): Observable<any> {
    return this.http.post(AUTH_URL + 'chats', {
      from_user_id,
      with_user_id,
      name
    });
  }

  postMessage(chatId: number, message: string): Observable<any> {
    return this.http.post(AUTH_URL + 'messages', {chat_id:chatId, message});
  }
}
