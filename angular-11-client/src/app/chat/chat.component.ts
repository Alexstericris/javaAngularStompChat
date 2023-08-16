import {Component, OnInit} from '@angular/core';
import {Chat, Message, Nullable, User, UserChat} from '../app.types';
import {UserService} from '../_services/user.service';
import {TokenStorageService} from '../_services/token-storage.service';
import {DateHelper} from '../_helpers/date.helper';
import {ChatService} from '../_services/chat.service';
import {WebsocketService} from '../_services/websocket.service';

@Component({
  selector: 'app-chat',
  templateUrl: 'chat.component.html',
  providers: [DateHelper],
})

export class ChatComponent implements OnInit {
  userChats: Array<UserChat> = [];
  allUsers: Array<User> = [];
  chattingUsers: Array<User> = [];
  activeChat: number | null = null;
  user?: User;
  connected = false;
  subscribed = false;
  channel = '/chatting/channel';

  constructor(private userService: UserService,
              private tokenStorage: TokenStorageService,
              private chatService: ChatService,
              private websocketService: WebsocketService,
              public dateHelper: DateHelper) {
  }

  async ngOnInit(): Promise<void> {
    this.user = this.tokenStorage.getUser();
    if (this.user) {
      await this.userService.getUserChats(this.user.id).subscribe(response => {
          this.userChats = response;
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
      this.websocketService.connect('/ws', (frame) => {
        this.connected = true;
        this.selectChat(0);
      });
    }
  }

  startNewChat($event: Event): void {
    if (!this.user) {
      console.log("user is null")
      return
    }
    let withUserEmail=($event.target as HTMLInputElement).value
    let chatWithUser: Nullable<User> = null;

    this.allUsers.forEach((user: User)=>{
      if (user.email===withUserEmail) {
        chatWithUser=user
      }
    })
    if (chatWithUser===null) {
      console.log("with user is null")
      return;
    }else{
      this.chatService.startNewChat(this.user.id,chatWithUser.id,chatWithUser.name).subscribe(data => {
        this.userChats.push(data)
          console.log('new chat added');
        },
        err => {
          console.log(err);
        })
    }
  }

  getChatDate(chat: Chat): string {
    if (chat.messages.length) {
      return this.dateHelper.formatArr(chat.messages.slice(-1)[0].createdAt);
    } else {
      return this.dateHelper.formatArr(chat.createdAt);
    }
  }

  submitMessage(event: Event): void {
    event.preventDefault();
    const message: string | null = (event.target as HTMLFormElement).message.value;

    this.websocketService.send('/app/message', {fromUserId:this.user?.id,message});
    if (this.activeChat !== null && message) {
      // this.userChats[this.activeChat]?.chat.messages.push(JSON.parse(message.body));
      this.chatService.postMessage(this.userChats[this.activeChat].chat.id, message).subscribe(response => {
        console.log('posted');
      });
    }
  }

  selectChat(index:number) {
    this.activeChat=index;
    if (this.userChats.length) {
      this.websocketService.subscribe(this.channel, (message) => {
        if (this.activeChat !== null) {
          this.userChats[this.activeChat]?.chat.messages.push(JSON.parse(message.body));
        }
      });
    }
  }
}
