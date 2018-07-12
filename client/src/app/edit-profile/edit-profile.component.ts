import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { User } from '../common.models';
import { UserService } from '../main/user.service';
import { Router } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class EditProfileComponent implements OnInit {
  public user: User;
  public isDataAvailable: Boolean;

  constructor(private userService:  UserService, private router: Router, private location: Location) {
    this.loadData();
  }

  ngOnInit() {
  }

  private loadData(){
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.isDataAvailable = false;
    this.userService.getUser(currentUser.username)
      .subscribe((user: User) => {
        this.user = user;
        this.isDataAvailable = true;
      })
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.userService.saveUser(this.user)
      .subscribe(() => this.goBack());
  }
}
