import {Component, Inject} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Employee} from "../interface/employee";
import {EmployeeProject} from "../interface/employee-project";
import {CustomResponse} from "../interface/custom-response";
import {MAT_DIALOG_DATA, MatDialog, MatDialogModule, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.css']
})
export class TeamComponent {

  employeeId: number = 0;
  projectId: number = 0;
  employees: Employee[] = [];
  ep: EmployeeProject = {employeeId: 0, id: 0, projectId: 0}

  constructor(private http: HttpClient, public dialog: MatDialog,
              @Inject(MAT_DIALOG_DATA) public data: {projId: number}){

    this.projectId = this.data.projId;

    this.http.get<CustomResponse>("http://localhost:8080/employee/list").subscribe(
      data => this.employees = data.data
    )
  }

  addEmployee(){
    this.ep.employeeId = this.employeeId;
    this.ep.projectId = this.projectId;
    this.http.post("http://localhost:8080/project/team", this.ep).subscribe(
      data => console.log(data)
    );
  }

  openDialog(projId: number) {
    this.projectId = projId;
    const dialogRef = this.dialog.open(TeamComponent);
    dialogRef.afterClosed().subscribe(
      result => this.employeeId = result
    );
    this.addEmployee();
  }

  closeDialog() {
    this.dialog.closeAll();
  }
}
