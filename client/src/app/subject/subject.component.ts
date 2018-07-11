import { Component, OnInit, ViewEncapsulation, Input } from '@angular/core';
import { Router } from '@angular/router';

import { SubjectInterface } from '../common.models';

@Component({
  selector: 'tr[app-subject]',
  templateUrl: './subject.component.html',
  styleUrls: ['./subject.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class SubjectComponent implements OnInit {

  @Input() subject: SubjectInterface;
	@Input() index:number;

  constructor(private router: Router) { }

  ngOnInit() {
    console.log(this.subject)
  }

  details = function (id) {
    this.router.navigate(['/subject',id]);
  }

  edit = function (id) {
    this.router.navigate(['/subject/edit',id]);
  }

}
