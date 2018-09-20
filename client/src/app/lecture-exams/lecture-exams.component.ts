import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { SubjectLecturesInterface, User, PassingExamsInterface, SubjectInterface } from '../common.models';
import { UserService } from '../main/user.service';
import { SubjectService } from '../main/subject.service';
import { Router } from '@angular/router';
import { AuthenticationService } from '../security/authentication.service';

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
  public teachers: User[];

  constructor(private userService:  UserService, private examService: SubjectService, private router: Router, private authService: AuthenticationService) {
    this.loadData();
  }

  ngOnInit() {
  }

  private loadData(){
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.isDataAvailable = false;
    this.subjectLectures = [];
    if (this.authService.isAdmin()) {
      this.userService.getAllTeachers()
      .subscribe((teachers: User[]) => {
        this.teachers = teachers;
        teachers.forEach(teacher => {
          this.subjectLectures = this.subjectLectures.concat(teacher.subjectLectures)
        });
      })
    } else {
      this.userService.getUser(currentUser.username)
        .subscribe((user: User) => {
          this.user = user;
          this.subjectLectures = user.subjectLectures;
          this.passingExams = user.passingExams;
          this.isDataAvailable = true;
        })
    }
  }

  apply(exam): void {
    exam.date = new Date(this.date).getTime();
    if (this.authService.isAdmin()) {
      this.teachers.forEach(teacher => {
        teacher.subjectLectures.forEach(sub => {
          if (sub.subject.name === exam.subject.name) {
            exam.teacher = teacher;
          }
        });
      });
    } else {
      exam.teacher = this.user;
    }
    if (isNaN(exam.date)) {
      alert('You have to set time before you can apply.')
    } else if (exam.date < Date.now()) {
      alert('You have to set date in the future not in the past');
    }
    else {
      this.examService.addExam(exam)
        .subscribe(() => {
          this.passingExams.push(exam);
        });
    }
  }

  details = function (id) {
    this.router.navigate(['/lecture',id]);
  }

  isAdmin = () => {
    return this.authService.isAdmin();
  }
}
