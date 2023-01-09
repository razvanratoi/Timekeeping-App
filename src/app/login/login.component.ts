import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../interface/user";
import {CustomResponse} from "../interface/custom-response";
import {Router} from "@angular/router";
import {map} from "rxjs";
import {SimpleResponse} from "../interface/simple-response";
import {LogService} from "../log.service";
import {waitForAsync} from "@angular/core/testing";
import {Employee} from "../interface/employee";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  users: User[] = [];
  username: string = '';
  password: string = '';
  errorMessage = 'Invalid Credentials';
  invalidLogin = false;
  loginSuccess = false;
  userId : number = 0;
  roleId: number = 0;

  role: number = 0;
  employees: Employee[] = [];
  constructor(private http: HttpClient,
              private router: Router,
              private logService: LogService) {
    this.http.get<CustomResponse>("http://localhost:8080/user/list").subscribe(
      (data ) => {this.users = <User[]>data.data;
        console.log(this.users)});
    this.http.get<CustomResponse>("http://localhost:8080/employee/list").subscribe(
      data => this.employees = data.data
    )
  }

  ngOnInit(): void {
  }

  handleLogin() {
    for(let i = 0; i < this.users.length; i++){
      let user = this.users.at(i);
      if(user == undefined){
        this.invalidLogin = true;
        return;
      }
      if(user.userName == this.username && user.password == this.password){
        this.userId = user.userId;
        // @ts-ignore
        this.role = this.employees.find(emp => emp.id == this.userId).roleId;
        let userId = this.userId;
        let role = this.role;
           this.router.navigate(['/logged-hours'], {queryParams: {userId,role}});
      }
    }
    // waitForAsync(this.checkLogin)
    // if (this.userId == 0) {
    //   this.invalidLogin = true;
    //   this.loginSuccess = false;
    // } else {
    //   if(this.roleId == 1){
    //     this.isCTO = true;
    //   }
    //   let userId= this.userId;
    //   let isCTO = this.isCTO;
    //   this.invalidLogin = false;
    // }

  }

  checkUser(username: string, password: string) {
    return this.http.get<SimpleResponse>("http://localhost:8080/user/check/" + username + "/" + password)
  }

  checkLogin(username: string, pass: string) {
    let ids: number[] = [];
    let config = this.checkUser(username, pass).subscribe(
      data => ids = data.data
    );
    this.userId = <number>ids.at(0);
    this.roleId = <number>ids.at(1);
  }

}
