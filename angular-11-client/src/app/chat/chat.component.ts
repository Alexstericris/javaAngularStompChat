import {Component, OnInit} from '@angular/core';
import {Chat, Message, User} from '../app.types';
import {UserService} from '../_services/user.service';
import {TokenStorageService} from '../_services/token-storage.service';
import {DateHelper} from '../_helpers/date.helper';

@Component({
  selector: 'app-chat',
  templateUrl: 'chat.component.html',
  providers: [DateHelper]
})

export class ChatComponent implements OnInit {
  chats: Array<Chat> = [];
  chattingUsers: Array<User> = [];
  activeChat = 0;
  user?: User;

  constructor(private userService: UserService, private tokenStorage: TokenStorageService, public dateHelper: DateHelper) {
  }

  ngOnInit(): void {
    this.user = this.tokenStorage.getUser();
    if (this.user) {
      this.userService.getUserChats(this.user.id).subscribe(response => {
          this.chats = response;
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
}
