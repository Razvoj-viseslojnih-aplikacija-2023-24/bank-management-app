import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BANK_URL } from '../constants';
import { Bank } from '../models/bank';

@Injectable({
  providedIn: 'root'
})
export class BankService {

  constructor(private httpClient:HttpClient) { }

  public getAllBanks():Observable<any> {
    return this.httpClient.get(`${BANK_URL}`)
  }

  public createBank (bank:Bank):Observable<any>{
    return this.httpClient.post(`${BANK_URL}`, bank);
  }

  public updateBank (bank:Bank):Observable<any>{
    return this.httpClient.put(`${BANK_URL}/id/${bank.id}`,bank);
  }

  public deleteBank(bankId: number):Observable<any>{
    return this.httpClient.delete(`${BANK_URL}/id/${bankId}`, {responseType:"text"});
  }
}