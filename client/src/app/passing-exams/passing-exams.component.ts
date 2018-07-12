import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { User, PassingExamsInterface, SubjectInterface, SubjectLecturesInterface } from '../common.models';
import { UserService } from '../main/user.service';
import { Router } from '@angular/router';
import { SubjectService } from '../main/subject.service';

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

  constructor(private userService:  UserService, private router: Router, private examService: SubjectService) {
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
    this.examService.applyExam(exam)
      .subscribe(() => {
        var index = this.passingExams.indexOf(exam);
        if (index > -1) {
          this.passingExams.splice(index, 1);
        }
      });
  }

}
