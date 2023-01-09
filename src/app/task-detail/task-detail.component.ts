import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {CustomResponse} from "../interface/custom-response";
import {Employee} from "../interface/employee";
import {Task} from "../interface/task"

@Component({
  selector: 'app-task-detail',
  templateUrl: './task-detail.component.html',
  styleUrls: ['./task-detail.component.css']
})
export class TaskDetailComponent {

  projId: number = 0;
  userId: number = 0;
  clientId = 0;
  pmId = 0;
  team: Employee[] = [];
  task: Task = {employeeId: 0, id: 0, projectId: 0, status: "", title: ""};

  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute){
    this.route.queryParams.subscribe(
      params => {
        this.userId = params['userId'];
        this.projId = params['projId'];
        this.clientId = params['clientId'];
        this.pmId = params['pmId'];});

    this.http.get<CustomResponse>("http://localhost:8080/employee/getTeam/" + this.projId ).subscribe(
      (data) => {this.team = data.data;
        console.log(data.data);}
    );
  }

  saveTask() {
    console.log(this.task);
    this.task.projectId = this.projId;
    this.task.status = "In progress";
    this.http.post("http://localhost:8080/task/save", this.task).subscribe(
      data => console.log(data)
    );

    let userId = this.userId;
    let projectId = this.projId;
    let clientId = this.clientId;
    let pmId = this.pmId;
    this.router.navigate(['/project-details'], {queryParams: {userId, projectId, clientId, pmId}})
  }
}
