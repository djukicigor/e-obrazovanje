import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { UserService } from '../main/user.service';
import { User, TransactionInterface } from '../common.models';
import { AuthenticationService } from '../security/authentication.service';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class TransactionsComponent implements OnInit {
  public user: User;
  public transactions: TransactionInterface[];
  public isDataAvailable: Boolean;

  constructor(private userService:  UserService, private authService: AuthenticationService) {
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
        if (this.authService.isAdmin()) {
          this.userService.getTransactions()
          .subscribe((transactions: TransactionInterface[]) => {
            this.transactions = transactions;
          })
        } else {
          this.transactions = user.transactions;
        }
        this.isDataAvailable = true;
      })
  }

  isAdmin() {
    return this.authService.isAdmin();
  }


}
