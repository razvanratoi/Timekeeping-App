import { Component } from '@angular/core';
import {Freedaysrequest} from "../interface/freedaysrequest";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {CustomResponse} from "../interface/custom-response";
import {Loggedhour} from "../interface/loggedhour";
import {Employee} from "../interface/employee";

@Component({
  selector: 'app-freedays',
  templateUrl: './freedays.component.html',
  styleUrls: ['./freedays.component.css']
})
export class FreedaysComponent {

  freeDays: Freedaysrequest[] = [];
  userId: number = 0;
  employee: Employee | undefined;
  role: number = 0;
  employees: Employee[] = [];

  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router) {

    this.route.queryParams.subscribe(
      params => {
        this.userId = params['userId'];
        this.role = params['role'];
      }
    );
    let url = "http://localhost:8080/freedaysrequest/list/" + this.userId;
    this.http.get<CustomResponse>("http://localhost:8080/employee/get/" + this.userId).subscribe(
      (data) => {
        let list = <Employee[]>data.data;
        this.employee = list.pop();
        console.log(this.employee)
      });
    if(this.role != 1) {
      this.http.get<CustomResponse>(url).subscribe(
        (data ) => {this.freeDays = <Freedaysrequest[]>data.data;
          console.log(this.freeDays)});
    }else{
      this.http.get<CustomResponse>("http://localhost:8080/freedaysrequest/list").subscribe(
        data => {this.freeDays = data.data;
          console.log(data);}
      )
    }
    this.http.get<CustomResponse>("http://localhost:8080/employee/list").subscribe(
      (data ) => {
        this.employees = <Employee[]>data.data;
        console.log(this.employees)});
  }

  toLoggedHours(){
    let userId = this.userId;
    let role = this.role;
    this.router.navigate(['/logged-hours'], {queryParams: {userId, role}});
  }

  toProjects(){
    let userId = this.userId;
    let role = this.role;
    this.router.navigate(['/projects'], {queryParams: {userId, role}});
  }

  toRequestDetail(requestId: number){
    let userId = this.userId;
    let role = this.role;
    this.router.navigate(['/request-details'], {queryParams: {userId,role, requestId}});
  }

  getEmployeeName(id: number){
    let emp = this.employees.find(emp => emp.id == id);
    // @ts-ignore
    return emp.name;
  }
}
