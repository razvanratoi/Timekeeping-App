import { Component } from '@angular/core';
import {Loggedhour} from "../interface/loggedhour";
import {Project} from "../interface/project";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {CustomResponse} from "../interface/custom-response";
import {SimpleResponse} from "../interface/simple-response";
import {LoggedHoursComponent} from "../logged-hours/logged-hours.component";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-logged-hour-detail',
  templateUrl: './logged-hour-detail.component.html',
  styleUrls: ['./logged-hour-detail.component.css']
})
export class LoggedHourDetailComponent {

  loggedHour: Loggedhour | undefined;
  userId : number = 0;
  loggedHourId: number = 0;
  projects: Project[] = [];

  startHour: number = 0;
  hoursWorked: number = 0;
  dateWorked!: Date;
  project: Project = {clientId: 0,
    deadLine: Date.prototype,
    description: "",
    id: 0,
    projectManager: 0,
    title: ""};
  role: number = 0;


  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router){
    this.route.queryParams.subscribe(
      params => {
        this.userId = params['userId'];
        this.loggedHourId = params['loggedHourId'];
        this.role = params['role'];
      }
    );

    let urlProjects = "http://localhost:8080/project/list/" + this.userId;
    this.http.get<CustomResponse>(urlProjects).subscribe(
      (data ) => {this.projects = <Project[]>data.data;
        console.log(this.projects)});

    if(this.loggedHourId != 0) {
      let urlLoggedHour = "http://localhost:8080/loggedhour/get/" + this.loggedHourId;
      this.http.get<SimpleResponse>(urlLoggedHour).subscribe(
        (data) => {
          this.loggedHour = <Loggedhour>data.data
          this.startHour = this.loggedHour.startHour;
          this.dateWorked = this.loggedHour.date;
          this.hoursWorked = this.loggedHour.duration
          this.http.get<SimpleResponse>("http://localhost:8080/project/get/" + this.loggedHour.projectId)
            .subscribe((data) => {this.project = data.data;
              console.log(data.data)})
        });
    }
  }

  saveLogged() {
    if (this.loggedHourId == 0) {
      console.log(this.project);
      let logged: Loggedhour = {
        id: 0,
        date: this.dateWorked,
        startHour: this.startHour,
        duration: this.hoursWorked,
        employeeId: this.userId,
        projectId: this.project.id
      };
      console.log(this.startHour);
      let url = "http://localhost:8080/loggedhour/save"
      console.log(JSON.stringify(logged));
      this.http.post<Loggedhour>(url, logged).subscribe(data => console.log(data));
      let userId = this.userId;
      this.router.navigate(['logged-hours'], {queryParams: {userId}});
    }
  }

  goBack(){
    let userId = this.userId;
    let role = this.role;
    this.router.navigate(['logged-hours'], {queryParams: {userId, role}});
  }


}
