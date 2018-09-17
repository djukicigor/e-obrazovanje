import { Component, OnInit } from '@angular/core';
import { Record, AuthorInterface, Order, User, SubjectPresencesInterface, SubjectInterface } from '../common.models';

import * as _ from 'lodash';

import { PriceLimits } from '../filter-records/filter-records.component';

import { RecordService } from '../main/record.service';
import { UserService } from '../main/user.service';
import { AuthenticationService } from '../security/authentication.service';
import { SubjectService } from './subject.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  public records: Record[];
  public newRecord: Record;
  public orderTypes = Order;
  public priceFilter: PriceLimits;
  public authors: AuthorInterface[];
  public user: User;
  public subjectPresences: SubjectPresencesInterface[];
  public subjects: SubjectInterface[];

  constructor(private recordService: RecordService, private userService:  UserService, private authService: AuthenticationService, private subjectService: SubjectService) {
    recordService.getRecords();
    this.priceFilter = {
      lowest:0,
      highest: 0
    }
  	this.records = [];
    this.loadData();

  }

  ngOnInit() {
  }

  private loadData(order?: Order){
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.userService.getUser(currentUser.username)
      .subscribe((user: User) => {
        this.user = user;
        if (this.authService.isAdmin()) {
          this.subjectService.getSubjects()
          .subscribe((subjects: SubjectInterface[]) => {
            this.subjects = subjects;
          })
        } else if (this.authService.isStudent()) {
          this.subjectPresences = user.subjectPresences;
        } else {
          this.subjectPresences = user.subjectLectures;
        }
      })
    this.recordService.
      getRecords(order,this.priceFilter.lowest,this.priceFilter.highest).
        subscribe((records: Record[]) => {this.records = records;});
  }

  save(newRecord:Record){
    this.recordService.saveRecord(newRecord).subscribe(
      () => {
        this.loadData();
      }
    );
  }

  delete(id: number){
    this.recordService.deleteRecord(id.toString()).subscribe(
      () => {
        this.loadData();
      }
    );
  }

  filterRecords(priceLimits: PriceLimits){
    this.priceFilter = priceLimits;
    this.loadData();
  }

  resetFilter(){
    this.priceFilter = {lowest:0,highest:0};
  }

}

