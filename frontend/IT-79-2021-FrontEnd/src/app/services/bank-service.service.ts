import { BankService } from '../models/bank-service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SERVICE_BY_BRANCH_URL, BANK_SERVICE_URL } from '../constants';


@Injectable({
  providedIn: 'root'
})
export class BankServiceService {

  constructor(private httpClient: HttpClient) { }

  public getAllBankServices():Observable<any> {
    return this.httpClient.get(`${BANK_SERVICE_URL}`);
  }

  public getServiceByBranch(branchId:number):Observable<any> {
    return this.httpClient.get(`${SERVICE_BY_BRANCH_URL}/${branchId}`);
  }

  public addBankService(bankService:BankService):Observable<any>{
    return this.httpClient.post(`${BANK_SERVICE_URL}`, bankService);
  }

  public updateBankService(bankService:BankService):Observable<any>{
    return this.httpClient.put(`${BANK_SERVICE_URL}/id/${bankService.id}`, bankService);
  }

  public deleteBankService(bankServiceId:number):Observable<any>{
    return this.httpClient.delete(`${BANK_SERVICE_URL}/id/${bankServiceId}`, {responseType:"text"});
  }
}