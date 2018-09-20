import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { User } from '../common.models';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../main/user.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class UserEditComponent implements OnInit {
  public user: User;
  private sub: any;
  public isDataAvailable: boolean;

  constructor(private userService:  UserService, private route: ActivatedRoute, private location: Location) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.isDataAvailable = false;
      this.userService.getUser(params['id'])
        .subscribe((user: User) => {
          console.log();
          this.user = user;
          this.isDataAvailable = true;
        })
    });
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    if (this.user.userAuthorities[0].authority.name === 'ROLE_TEACHER') {
      this.userService.saveUser(this.user)
        .subscribe(() => this.goBack());
    } else {
      this.userService.editUser(this.user)
        .subscribe(() => this.goBack());
    }
  }

}
