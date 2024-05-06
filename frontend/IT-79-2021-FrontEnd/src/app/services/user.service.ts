import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { USER_URL } from '../constants';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient:HttpClient) { }

  public getAllUsers():Observable<any> {
    return this.httpClient.get(`${USER_URL}`)
  }

  public createUser (user:User):Observable<any>{
    return this.httpClient.post(`${USER_URL}`, user);
  }

  public updateUser (user:User):Observable<any>{
    return this.httpClient.put(`${USER_URL}/id/${user.id}`,user);
  }

  public deleteUser(userId: number):Observable<any>{
    return this.httpClient.delete(`${USER_URL}/id/${userId}`, {responseType:"text"});
  }
}
