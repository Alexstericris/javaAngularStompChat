import * as SockJs from 'sockjs-client';
import {Injectable} from '@angular/core';
import {Client, Message, over, Subscription} from 'stompjs';
import {AuthStorageService} from './auth-storage.service';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {
  websocketEndpoint = 'http://localhost:8080/ws';
  stompClient?: Client;
  wsSubscription?: Subscription;
  receivedMessages = '';

  constructor(private tokenStorageService: AuthStorageService) {
  }

  initSocket(url: string): void {
    console.log('Initialize WebSocket Connection');
    const ws = new SockJs(url);
    this.stompClient = over(ws);
    // this.stompClient=this.configureStompClient()

  }

  connect(url: string, callback: (frame: any) => void): void {
    this.disconnect();
    this.initSocket(url);
    // const headers = {
    //   Authorization: 'Bearer ' + this.tokenStorageService.getToken()
    // };
    // console.log('Try to connect.');
    // return this.stompClient?.connect(headers, callback);
  }

  disconnect(): void {
    if (this.stompClient !== null) {
      this.stompClient?.disconnect(() => {
      });
      this.wsSubscription?.unsubscribe();
    }
    console.log('Disconnected');
  }

  subscribe(channel: string, callback: (message: Message) => void): void {
    this.wsSubscription = this.stompClient?.subscribe(channel, callback);
  }

  send(to: any, message: any): void {
    console.log('sending message');
    this.stompClient?.send(to, {}, JSON.stringify(message));
  }
}
