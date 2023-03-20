import {Component, OnInit} from '@angular/core';
import {Chat, Message, User} from '../app.types';
import {UserService} from '../_services/user.service';
import {TokenStorageService} from '../_services/token-storage.service';
import {DateHelper} from '../_helpers/date.helper';
import {ChatService} from '../_services/chat.service';

@Component({
  selector: 'app-chat',
  templateUrl: 'chat.component.html',
  providers: [DateHelper]
})

export class ChatComponent implements OnInit {
  chats: Array<Chat> = [];
  allUsers: Array<User> = [];
  chattingUsers: Array<User> = [];
  activeChat: number | null = null;
  user?: User;

  constructor(private userService: UserService,
              private tokenStorage: TokenStorageService,
              private chatService: ChatService,
              public dateHelper: DateHelper) {
  }

  ngOnInit(): void {
    this.user = this.tokenStorage.getUser();
    if (this.user) {
      this.userService.getUserChats(this.user.id).subscribe(response => {
          this.chats = response;
          if (this.chats.length) {
            this.activeChat = 0;
          }
        },
        err => {
          console.log(err);
        });
      this.userService.getUsers().subscribe(response => {
          this.allUsers = response;
        },
        err => {
          console.log(err);
        });
      this.userService.getChattingUsers(this.user.id).subscribe(response => {
          this.chattingUsers = response;
        },
        err => {
          console.log(err);
        });
    }
  }

  startNewChat($event: Event): void {
    console.log(($event.target as HTMLInputElement).value);
    this.chatService.startNewChat(($event.target as HTMLInputElement).value).subscribe(response => {
        console.log('new chat added');
      },
      err => {
        console.log(err);
      });
  }

  getChatDate(chat: Chat): string {
    if (chat.messages.length) {
      return this.dateHelper.format(chat.messages.slice(-1)[0].createdAt, {
        month: 'short',
        day: 'numeric'
      });
    } else {
      return this.dateHelper.format(chat.createdAt, {
        month: 'short',
        day: 'numeric'
      });
    }
  }

  submitMessage(event: Event): void {
    event.preventDefault();
    const message: string | null = (event.target as HTMLFormElement).message.value;
    if (this.activeChat !== null && message) {
      this.chatService.postMessage(this.chats[this.activeChat].id, message).subscribe(response => {
        console.log('posted');
      });
    }
  }
}
