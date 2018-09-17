import { Component, OnInit, ViewEncapsulation, Input } from '@angular/core';

import { SubjectPresencesInterface } from '../common.models';

@Component({
  selector: 'app-subject-list',
  templateUrl: './subject-list.component.html',
  styleUrls: ['./subject-list.component.css'],
})

export class SubjectListComponent implements OnInit {

  @Input() subjectPresences: SubjectPresencesInterface[];

  constructor() { }

  ngOnInit() {
  }

}
