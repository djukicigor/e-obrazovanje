import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { User, PassingExamsInterface, SubjectInterface, SubjectLecturesInterface } from '../common.models';
import { UserService } from '../main/user.service';
import { Router } from '@angular/router';
import { SubjectService } from '../main/subject.service';
import { AuthenticationService } from '../security/authentication.service';

@Component({
  selector: 'app-passing-exams',
  templateUrl: './passing-exams.component.html',
  styleUrls: ['./passing-exams.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class PassingExamsComponent implements OnInit {
  public user: User;
  public isDataAvailable: Boolean;
  public passingExams: PassingExamsInterface[];
  public subjectLectures: SubjectLecturesInterface[];
  public money: Number;

  constructor(private userService:  UserService, private router: Router, private examService: SubjectService, private authenticationService: AuthenticationService) {
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
        this.money = user.balance;
        this.examService.getExams(user.id).subscribe(
          (exams: PassingExamsInterface[]) => {
            // exam.students.push(this.user);
            this.passingExams = exams;
            this.isDataAvailable = true;
          }
        );
      })
  }

  apply(exam): void {
    exam.students.push(this.user);
    this.examService.applyExam(exam, this.user.id)
      .subscribe(() => {
        let user = this.user;
        user.balance -= 200;
        this.money = user.balance;
        var index = this.passingExams.indexOf(exam);
        if (index > -1) {
          this.passingExams.splice(index, 1);
        }
      });
  }

  isAdmin() {
    return this.authenticationService.isAdmin();
  }
}
