import { Component } from '@angular/core';
import {Project} from "../interface/project";
import {Client} from "../interface/client";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {CustomResponse} from "../interface/custom-response";
import {SimpleResponse} from "../interface/simple-response";
import {Employee} from "../interface/employee";

@Component({
  selector: 'app-project-form',
  templateUrl: './project-form.component.html',
  styleUrls: ['./project-form.component.css']
})
export class ProjectFormComponent {

  userId: number = 0;
  role: number = 0;
  clients: Client[] = [];
  project: Project = {clientId: 0, deadLine: Date.prototype, description: "", id: 0, projectManager: 0, title: ""};
  projectManagers: Employee[] = [];

  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router) {
    this.route.queryParams.subscribe(
      params => {
        this.userId = params['userId'];
        this.role = params['role'];
      }
    );

    this.http.get<CustomResponse>("http://localhost:8080/client/list").subscribe(
      data => this.clients = data.data
    )

    this.http.get<CustomResponse>("http://localhost:8080/employee/getPM").subscribe(
      data => {this.projectManagers = data.data; console.log(data);}
    )
  }

  goBack(){
    let userId = this.userId;
    let role = this.role;
    this.router.navigate(['/projects'], {queryParams:{userId, role}});
  }

  save() {
    this.http.post("http://localhost:8080/project/save", this.project).subscribe(
      data => console.log(data)
    );
    let userId = this.userId;
    let role = this.role;
    this.router.navigate(['/projects'], {queryParams: {userId, role}})
  }
}
