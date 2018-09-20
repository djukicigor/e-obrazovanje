import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { UserService } from '../main/user.service';
import { User, PassedExamInterface } from '../common.models';

@Component({
  selector: 'app-results-list',
  templateUrl: './results-list.component.html',
  styleUrls: ['./results-list.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ResultsListComponent implements OnInit {
  public passedExams: PassedExamInterface[];

  constructor(private userService:  UserService) {
    this.loadData();
  }

  ngOnInit() {
  }

  private loadData(){
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.userService.getUser(currentUser.username)
      .subscribe((user: User) => {
        console.log(user.passed_exams);

        this.passedExams = user.passed_exams;
      })
  }

}
