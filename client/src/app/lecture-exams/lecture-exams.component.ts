import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { SubjectLecturesInterface, User, PassingExamsInterface } from '../common.models';
import { UserService } from '../main/user.service';
import { SubjectService } from '../main/subject.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-lecture-exams',
  templateUrl: './lecture-exams.component.html',
  styleUrls: ['./lecture-exams.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class LectureExamsComponent implements OnInit {
  public user: User;
  public isDataAvailable: Boolean;
  public subjectLectures: SubjectLecturesInterface[];
  date: Date;
  public passingExams: PassingExamsInterface[];

  constructor(private userService:  UserService, private examService: SubjectService, private router: Router) {
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
        this.subjectLectures = user.subjectLectures;
        this.passingExams = user.passingExams;
        this.isDataAvailable = true;
      })
  }

  apply(exam): void {
    exam.date = new Date(this.date).getTime();
    exam.teacher = this.user;
    console.log(exam)
    this.examService.addExam(exam)
      .subscribe(() => {
        var index = this.subjectLectures.indexOf(exam);
        if (index > -1) {
          this.subjectLectures.splice(index, 1);
        }
      });
  }

  details = function (id) {
    this.router.navigate(['/lecture',id]);
  }
}
