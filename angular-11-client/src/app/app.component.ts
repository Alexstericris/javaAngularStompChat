import {Component, OnInit} from '@angular/core';
import {AuthStorageService} from './_services/auth-storage.service';
import {AuthService} from './_services/auth.service';
import {WebsocketService} from "./_services/websocket.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  private roles: string[] = [];
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  username?: string;

  constructor(private authService: AuthService, private tokenStorageService: AuthStorageService) {
  }

  ngOnInit(): void {
    this.isLoggedIn = JSON.parse(localStorage.isLoggedIn);
    this.authService.isLoggedIn.subscribe((isLoggedIn: boolean) => {
      this.isLoggedIn = isLoggedIn;
    });
    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();

      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showModeratorBoard = this.roles.includes('ROLE_MODERATOR');

      this.username = user.username;
    }
  }

  logout(): void {
    this.tokenStorageService.logout();
    window.location.reload();
  }

}
