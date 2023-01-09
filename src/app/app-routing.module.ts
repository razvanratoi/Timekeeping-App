import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {LoggedHoursComponent} from "./logged-hours/logged-hours.component";
import {FreedaysComponent} from "./freedays/freedays.component";
import {ProjectsComponent} from "./projects/projects.component";
import {LoggedHourDetailComponent} from "./logged-hour-detail/logged-hour-detail.component";
import {ProjectDetailComponent} from "./project-detail/project-detail.component";
import {FreeDaysRequestDetailComponent} from "./free-days-request-detail/free-days-request-detail.component";
import {TaskDetailComponent} from "./task-detail/task-detail.component";
import {ProjectFormComponent} from "./project-form/project-form.component";
import {EmployeeComponent} from "./employee/employee.component";
import {EmployeeDetailComponent} from "./employee-detail/employee-detail.component";

const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: "full"},
  {path: 'login', component: LoginComponent},
  {path: 'logged-hours', component: LoggedHoursComponent},
  {path: 'free-days', component: FreedaysComponent},
  {path: 'projects', component: ProjectsComponent},
  {path: 'logged-hour-details', component: LoggedHourDetailComponent},
  {path: 'project-details', component: ProjectDetailComponent},
  {path: 'request-details', component: FreeDaysRequestDetailComponent},
  {path: 'task-details', component: TaskDetailComponent},
  {path: 'project-form', component: ProjectFormComponent},
  {path: 'employees', component: EmployeeComponent},
  {path: 'employees-detail', component: EmployeeDetailComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
