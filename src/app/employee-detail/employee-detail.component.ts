import { Component } from '@angular/core';
import {Employee} from "../interface/employee";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-employee-detail',
  templateUrl: './employee-detail.component.html',
  styleUrls: ['./employee-detail.component.css']
})
export class EmployeeDetailComponent {

  userId: number = 0;
  role: number = 0;
  employee: Employee = {email: "", hireDate: Date.prototype, id: 0, name: "", phoneNumber: "", roleId: 0, workSchedule: 0};

  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router) {
    this.route.queryParams.subscribe(params => {
      this.userId = params['userId'];
      this.role = params['role'];
    })
  }
  save() {
    this.employee.hireDate = new Date();
    console.log(this.employee);
    this.http.post("http://localhost:8080/employee/save", this.employee).subscribe(
      data => console.log(data)
    )
    this.goBack();
  }

  goBack() {
    let role = this.role;
    let userId = this.userId;
    this.router.navigate(['/employees'], {queryParams: {userId, role}});
  }
}
