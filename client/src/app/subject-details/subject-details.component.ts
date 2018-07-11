import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { SubjectInterface } from '../common.models';
import { SubjectService } from '../main/subject.service';

@Component({
  selector: 'app-subject-details',
  templateUrl: './subject-details.component.html',
  styleUrls: ['./subject-details.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class SubjectDetailsComponent implements OnInit {
  id: number;
  private sub: any;
  subject: SubjectInterface;
  isDataAvailable: boolean;

  constructor(private subjectService: SubjectService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.isDataAvailable = false;
      this.subjectService.getSubject(params['id']).subscribe(
        (subject: SubjectInterface) => {
          this.subject = subject;
          this.isDataAvailable = true;
        }
      );
  }

}
