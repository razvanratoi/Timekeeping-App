import { Component } from '@angular/core';
import {Freedaysrequest} from "../interface/freedaysrequest";
import {Employee} from "../interface/employee";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {CustomResponse} from "../interface/custom-response";
import {Project} from "../interface/project";

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent {

  projects: Project[] = [];
  userId: number = 0;
  employee: Employee | undefined;
  role: number = 0;


  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router) {

    this.route.queryParams.subscribe(
      params => {
        this.userId = params['userId'];
        this.role = params['role'];
      }
    );
    let url = "http://localhost:8080/project/list/" + this.userId;
    if(this.role != 1) {
      this.http.get<CustomResponse>(url).subscribe(
        (data) => {
          this.projects = <Project[]>data.data;
          console.log(this.projects)
        });
    }else{
      this.http.get<CustomResponse>("http://localhost:8080/project/list").subscribe(
        (data) => {
          this.projects = <Project[]>data.data;
          console.log(this.projects)
        });
    }
    this.http.get<CustomResponse>("http://localhost:8080/employee/get/" + this.userId).subscribe(
      (data ) => {
        let list = <Employee[]>data.data;
        this.employee = list.pop();
        console.log(this.employee)});
  }

  toLoggedHours(){
    let userId = this.userId;
    let role = this.role;
    this.router.navigate(['/logged-hours'], {queryParams: {userId, role}});
  }

  toFreeDays(){
    let userId = this.userId;
    let role = this.role;
    this.router.navigate(['/free-days'], {queryParams: {userId, role}});
  }

  toProjectDetails(id: number, cId : number, pmId: number){
    let userId = this.userId;
    let projectId = id;
    let role = this.role;
    let clientId = cId;
    this.router.navigate(['/project-details'], {queryParams: {userId, projectId, clientId, pmId, role}});
  }

  toProjectForm() {
    let userId = this.userId;
    let role = this.role;
    this.router.navigate(['/project-form'], {queryParams: {userId, role}})
  }
}
