import {StompService} from '@stomp/ng2-stompjs';
import {StompConfig} from '@stomp/ng2-stompjs';
import {Injectable} from '@angular/core';
import {Message} from '@stomp/stompjs';


@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  private stompService: StompService;

  constructor(stompService: StompService) {
    this.stompService = stompService;
  }

  connect(): void {
    this.stompService.initAndConnect();
  }

  disconnect(): void {
    this.stompService.disconnect();
  }

  subscribe(topic: string, callback: (message: Message) => void): void {
    this.stompService.subscribe(topic).subscribe(callback);
  }

  send(destination: string, message: string): void {
    this.stompService.publish(destination, message);
  }

}
