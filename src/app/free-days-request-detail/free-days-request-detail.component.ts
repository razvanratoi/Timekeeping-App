import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {FreedaysComponent} from "../freedays/freedays.component";
import {Freedaysrequest} from "../interface/freedaysrequest";
import {SimpleResponse} from "../interface/simple-response";
import {CustomResponse} from "../interface/custom-response";

@Component({
  selector: 'app-free-days-request-detail',
  templateUrl: './free-days-request-detail.component.html',
  styleUrls: ['./free-days-request-detail.component.css']
})
export class FreeDaysRequestDetailComponent {

  request: Freedaysrequest = {
    duration: 0,
    employeeId: 0,
    id: 0,
    startDate: Date.prototype,
    status: "Pending",
    typeOfVacation: ""
  };
  role: number = 0;
  userId: number = 0;
  requestId: number = 0;
  duration: number = 0;
  typeOfVacations: string[] = ['Paid time off leave', 'Sick leave', 'Maternity leave', 'Paternity leave',
  'Bereavement leave', 'Compensatory leave', 'Unpaid leave'];

  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router){
    this.route.queryParams.subscribe(
      params => {
        this.role = params['role'];
        this.userId = params['userId'];
        this.requestId = params['requestId'];
      }
    );
    if(this.requestId != 0){
      this.http.get<SimpleResponse>("http://localhost:8080/freedaysrequest/get/" + this.requestId).subscribe(
        data => this.request = data.data
      )
    }
  }

  saveRequest(){
    this.request.employeeId = this.userId;
    console.log(this.request);
    this.http.post<CustomResponse>("http://localhost:8080/freedaysrequest/save", this.request).subscribe(
      data => console.log(data)
    );
    this.goBack();
  }

  goBack(){
    let userId = this.userId;
    let role = this.role;
    this.router.navigate(['/free-days'], {queryParams:{userId, role}})
  }

  review(approved: boolean){
    if(approved){
      this.request.status = "Approved";
    }else{
      this.request.status = "Rejected";
    }
    console.log(this.request);
    this.http.post<SimpleResponse>("http://localhost:8080/freedaysrequest/update", this.request).subscribe(
      data => console.log(data)
    );
    this.goBack();
  }

  isCTO() {
    return this.role==1
  }
}
