import { Branch } from './../models/branch';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BRANCH_URL } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class BranchService {

  constructor(private httpClient:HttpClient) { }

  public getAllBranches():Observable<any> {
    return this.httpClient.get(`${BRANCH_URL}`)
  }

  public addBranch(branch:Branch):Observable<any> {
    return this.httpClient.post(`${BRANCH_URL}`, branch);
  }

  public updateBranch(branch:Branch):Observable<any> {
    return this.httpClient.put(`${BRANCH_URL}/id/${branch.id}`, branch);
  }

  public deleteBranch(branchId:number):Observable<any> {
    return this.httpClient.delete(`${BRANCH_URL}/id/${branchId}`, {responseType:"text"});
  }
}