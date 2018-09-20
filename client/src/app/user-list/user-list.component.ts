import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { UserService } from '../main/user.service';
import { User } from '../common.models';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class UserListComponent implements OnInit {
  public students: User[];
  public teachers: User[];

  constructor(private userService: UserService, private router: Router) {
    this.loadData();
  }

  private loadData() {
    this.userService.getAllStudents()
      .subscribe((students: User[]) => {
        this.students = students;
      })
      this.userService.getAllTeachers()
      .subscribe((teachers: User[]) => {
        console.log(teachers[0]);
        this.teachers = teachers;
      })
  }

  ngOnInit() {
  }

  edit = function (id) {
    this.router.navigate(['/users/' + id]);
  }

}
