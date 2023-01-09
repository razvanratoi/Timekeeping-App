import { Injectable } from '@angular/core';
import {SimpleResponse} from "./interface/simple-response";
import {HttpClient} from "@angular/common/http";
import {waitForAsync} from "@angular/core/testing";

@Injectable({
  providedIn: 'root'
})
export class LogService {

  userId : number = 0;

  checkUser(username: string, password: string) {
    return this.http.get<SimpleResponse>("http://localhost:8080/user/check/" + username + "/" + password)
  }

  checkLogin(username: string, pass: string): number {
    let config = this.checkUser(username, pass).subscribe(
      data => this.userId = data.data
    );
    return this.userId;
  }

  constructor(private http: HttpClient) { }
}
