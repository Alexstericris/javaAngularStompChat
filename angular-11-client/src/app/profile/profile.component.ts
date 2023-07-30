import { Component, OnInit } from '@angular/core';
import { AuthStorageService } from '../_services/auth-storage.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: any;

  constructor(private token: AuthStorageService) { }

  ngOnInit(): void {
    this.currentUser = this.token.getUser();
  }
}
