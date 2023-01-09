import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Loggedhour} from "../interface/loggedhour";
import {ActivatedRoute, Router} from "@angular/router";
import {CustomResponse} from "../interface/custom-response";
import {User} from "../interface/user";
import {Employee} from "../interface/employee";
import {Project} from "../interface/project";

@Component({
  selector: 'app-logged-hours',
  templateUrl: './logged-hours.component.html',
  styleUrls: ['./logged-hours.component.css']
})
export class LoggedHoursComponent {

  loggedHours: Loggedhour[] = [];
  employee: Employee | undefined;
  userId : number = 0;
  role: number = 0;
  projects: Project[] = [];
  employees: Employee[] = [];
  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router){
    this.route.queryParams.subscribe(
      params => {
        this.userId = params['userId'];
        this.role = params['role'];
      }
    );
    let url = "http://localhost:8080/loggedhour/list/" + this.userId;
    if(this.role != 1) {
      this.http.get<CustomResponse>(url).subscribe(
        (data) => {
          this.loggedHours = <Loggedhour[]>data.data;
          console.log(this.loggedHours)
        });
    }else{
      this.http.get<CustomResponse>("http://localhost:8080/loggedhour/list").subscribe(
        (data) => {
          this.loggedHours = <Loggedhour[]>data.data;
          console.log(this.loggedHours)
        });
    }
    this.http.get<CustomResponse>("http://localhost:8080/employee/get/" + this.userId).subscribe(
      (data ) => {
        let list = <Employee[]>data.data;
        this.employee = list.pop();
        console.log(this.employee)});
    this.http.get<CustomResponse>("http://localhost:8080/employee/list").subscribe(
      (data ) => {
        this.employees = <Employee[]>data.data;
        console.log(this.employees)});
  }

  toFreeDays(){
    let userId = this.userId;
    let role = this.role;
    this.router.navigate(['/free-days'], {queryParams: {userId, role}});
  }

  toProjects(){
    let userId = this.userId;
    let role = this.role;
    this.router.navigate(['/projects'], {queryParams: {userId, role}});
  }

  toDetails(){
    let userId = this.userId;
    let loggedHourId = 0;
    let role = this.role;
    this.router.navigate(['/logged-hour-details'], {queryParams: {userId, loggedHourId, role}});
  }

  toDetailsId(id: number){
    let userId = this.userId;
    let loggedHourId = id;
    let role = this.role;
    this.router.navigate(['logged-hour-details'], {queryParams: {userId, loggedHourId, role}});
  }

  sortTable(filter: number){
    if(filter == 1){
      this.loggedHours.sort((obj1,obj2) => obj1.id - obj2.id );
    }
    if(filter == 2){
     this.loggedHours.sort((obj1,obj2) => obj1.date.valueOf() - obj2.date.valueOf() );

    }
    if(filter == 3){
      this.loggedHours.sort((obj1,obj2) => obj1.startHour - obj2.startHour );
    }
    if(filter == 4){
      this.loggedHours.sort((obj1,obj2) => obj1.duration - obj2.duration );
    }
    if(filter == 5){
      this.loggedHours.sort((obj1,obj2) => obj1.projectId - obj2.projectId );
    }
  }


  getEmployeeName(id: number){
    let emp = this.employees.find(emp => emp.id == id);
    // @ts-ignore
    return emp.name;
  }

  toEmployees(){
    let userId = this.userId;
    let role = this.role;
    this.router.navigate(['/employees'], {queryParams: {userId, role}});
  }
}
