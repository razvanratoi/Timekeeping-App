import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {SimpleResponse} from "../interface/simple-response";
import {Project} from "../interface/project";
import {Employee} from "../interface/employee";
import {Client} from "../interface/client";
import {Task} from "../interface/task";
import {CustomResponse} from "../interface/custom-response";
import {TeamComponent} from "../team/team.component";
import {MatDialog} from "@angular/material/dialog";
import {EmployeeProject} from "../interface/employee-project";

@Component({
  selector: 'app-project-detail',
  templateUrl: './project-detail.component.html',
  styleUrls: ['./project-detail.component.css']
})
export class ProjectDetailComponent {

  userId: number = 0;
  projectId: number = 0;
  project: Project = {clientId: 0,
    deadLine: Date.prototype,
    description: "",
    id: 0,
    projectManager: 0,
    title: ""};
  pmId : number = 1;

  projectManager: Employee = {
    email: "",
    hireDate: Date.prototype,
    id: 0,
    name: "",
    phoneNumber: "",
    roleId: 0,
    workSchedule: 0
  }

  selectTeam: boolean = true;
  client: Client = {id: 0, name: ""}
  clientId : number = 0;
  tasks: Task[] = [];
  team: Employee[] = [];
  role: number = 0;
  notTeam: Employee[] = [];
  ep: EmployeeProject = {employeeId: 0, id: 0, projectId: 0};
  newEmployee: Employee = {email: "", hireDate: Date.prototype, id: 0, name: "", phoneNumber: "", roleId: 0, workSchedule: 0};

  constructor(private http: HttpClient,
              private router: Router,
              private route: ActivatedRoute){
    this.route.queryParams.subscribe(
      params => {
        this.userId = params['userId'];
        this.projectId = params['projectId'];
        this.clientId = params['clientId'];
        this.pmId = params['pmId'];
        this.role = params['role'];
      }
    );

    this.http.get<SimpleResponse>("http://localhost:8080/project/get/" + this.projectId ).subscribe(
      (data) => {this.project = data.data;
      console.log(data.data);
      this.pmId = this.project.projectManager;}
    );

    this.http.get<CustomResponse>("http://localhost:8080/employee/getTeam/" + this.projectId ).subscribe(
      (data) => {this.team = data.data;
        console.log(data.data);}
    );

    this.http.get<CustomResponse>("http://localhost:8080/task/list/" + this.projectId ).subscribe(
      (data) => {this.tasks = data.data;
        console.log(data.data);}
    );

    this.http.get<SimpleResponse>("http://localhost:8080/employee/get/" + this.pmId)
      .subscribe(data => this.projectManager = data.data);

    this.http.get<SimpleResponse>("http://localhost:8080/client/get/" + this.clientId)
      .subscribe(data => this.client = data.data);

    this.http.get<CustomResponse>("http://localhost:8080/employee/getNoTeam/" + this.projectId).subscribe(
      data => {this.notTeam = data.data; console.log(data);}
    )
  }

  goBack(){
    let userId = this.userId;
    let role = this.role;
    this.router.navigate(['/projects'], {queryParams:{userId, role}});
  }

  editTask(id: number, pace: number) {
    let task = this.tasks.find(t => t.id == id);
    let statuses = ["In progress", "In testing", "Completed"];
    // @ts-ignore
    let idx = statuses.indexOf(task.status);
    // @ts-ignore
    task.status = statuses.at((idx + pace) % 3);
    this.http.post("http://localhost:8080/task/save", task).subscribe(
      data => console.log(data)
    );
  }

  getEmployeeName(id: number) {
    // @ts-ignore
    return this.team.find(emp => emp.id == id).name;
  }

  toTaskDetails() {
    let userId = this.userId;
    let clientId = this.clientId;
    let pmId = this.pmId;
    let projId = this.projectId;
    this.router.navigate(['/task-details'], {queryParams: {userId, projId, clientId, pmId}});
  }

  addEmployees(){
    this.selectTeam = !this.selectTeam;
  }

  addEmployee(id: number){
    this.ep.employeeId = id;
    this.ep.projectId = this.projectId;
    this.http.post("http://localhost:8080/project/team", this.ep).subscribe(
      data => console.log(data)
    );
    this.selectTeam = true;
  }
}
