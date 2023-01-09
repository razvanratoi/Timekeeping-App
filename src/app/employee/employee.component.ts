import { Component } from '@angular/core';
import {Employee} from "../interface/employee";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {CustomResponse} from "../interface/custom-response";

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent {

  employees: Employee[] = [];
  userId: number = 0;
  role: number = 0;

  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router) {
    this.route.queryParams.subscribe(
      params => {
        this.role = params['role'];
        this.userId = params['userId'];
      }
    );

    this.http.get<CustomResponse>("http://localhost:8080/employee/list").subscribe(
      data => this.employees = data.data
    )
  }

  toEmployeeDetails() {
    let userId = this.userId;
    let role = this.role;
    this.router.navigate(['/employees-detail'], {queryParams: {userId, role}});
  }

  toLoggedHours() {
    let userId = this.userId;
    let role = this.role;
    this.router.navigate(['/loggedhours'], {queryParams: {userId, role}});
  }

  toProjects() {
    let userId = this.userId;
    let role = this.role;
    this.router.navigate(['/projects'], {queryParams: {userId, role}});
  }

  goBack(){
    let userId = this.userId;
    let role = this.role;
    this.router.navigate(['logged-hours'], {queryParams: {userId, role}});
  }

  toFreeDays() {
    let userId = this.userId;
    let role = this.role;
    this.router.navigate(['/free-days'], {queryParams: {userId, role}});
  }

  promote(emp: Employee) {
    emp.roleId -= 1;
    this.http.post("http://localhost:8080/employee/update", emp).subscribe(
      data => console.log(data)
    );
  }

  getRole(roleId: number) {
    if(roleId == 1){
      return "CTO"
    }else if(roleId == 2){
      return "Project Manager"
    }else if(roleId == 3){
      return "Team Leader"
    }else
      return "Employee"
  }
}
