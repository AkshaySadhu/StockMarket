import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserRegisterService {

  constructor(private http : HttpClient) { }

  public doRegistration(user){
    return this.http.post("http://localhost:8080/register",user, {responseType : "text" as "json"});
  }
  public dologin(user){
    return this.http.post("http://localhost:8080/login",user, {responseType : "text" as "json"});
  }

  public getUsers(){
    return this.http.get("http://localhost:8080/all-users");
  }

  public getUserByUsername(username){
    return this.http.get("http://localhost:8080/search/"+username);
  }

  public deleteUser(username){
    return this.http.get("http://localhost:8080/delete/"+username);
  }
}
