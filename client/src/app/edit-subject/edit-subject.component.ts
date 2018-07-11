import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { SubjectInterface } from '../common.models';
import { SubjectService } from '../main/subject.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-edit-subject',
  templateUrl: './edit-subject.component.html',
  styleUrls: ['./edit-subject.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class EditSubjectComponent implements OnInit {
  id: number;
  private sub: any;
  subject: SubjectInterface;
  isDataAvailable: boolean;

  constructor(private subjectService: SubjectService, private route: ActivatedRoute, private location: Location) {}

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.isDataAvailable = false;
      this.subjectService.getSubject(params['id']).subscribe(
        (subject: SubjectInterface) => {
          this.subject = subject;
          this.isDataAvailable = true;
        }
      );
    });
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.subjectService.saveSubject(this.subject)
      .subscribe(() => this.goBack());
  }

}
