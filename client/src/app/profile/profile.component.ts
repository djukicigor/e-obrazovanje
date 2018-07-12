import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { User } from '../common.models';
import { UserService } from '../main/user.service';
import { Router } from '@angular/router';
import { AuthenticationService } from '../security/authentication.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ProfileComponent implements OnInit {
  public user: User;
  public isDataAvailable: Boolean;
  public uploadme: Object;

  constructor(private userService:  UserService, private router: Router, private authService: AuthenticationService) {
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

  edit = function () {
    this.router.navigate(['/profile/edit']);
  }

  isTeacher() {
    return this.authService.isTeacher()
  }

  isStudent() {
    return this.authService.isStudent();
  }

  save() {
    console.log(this.uploadme)
  }

}
