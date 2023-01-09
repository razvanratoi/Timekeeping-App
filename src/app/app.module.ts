import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { LoggedHoursComponent } from './logged-hours/logged-hours.component';
import { FreedaysComponent } from './freedays/freedays.component';
import { ProjectsComponent } from './projects/projects.component';
import { LoggedHourDetailComponent } from './logged-hour-detail/logged-hour-detail.component';
import { ProjectDetailComponent } from './project-detail/project-detail.component';
import { FreeDaysRequestDetailComponent } from './free-days-request-detail/free-days-request-detail.component';
import { TaskDetailComponent } from './task-detail/task-detail.component';
import { ProjectFormComponent } from './project-form/project-form.component';
import { EmployeeComponent } from './employee/employee.component';
import { EmployeeDetailComponent } from './employee-detail/employee-detail.component';
import { TeamComponent } from './team/team.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatDialogModule} from "@angular/material/dialog";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LoggedHoursComponent,
    FreedaysComponent,
    ProjectsComponent,
    LoggedHourDetailComponent,
    ProjectDetailComponent,
    FreeDaysRequestDetailComponent,
    TaskDetailComponent,
    ProjectFormComponent,
    EmployeeComponent,
    EmployeeDetailComponent,
    TeamComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatDialogModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  exports: [LoginComponent]
})
export class AppModule { }
