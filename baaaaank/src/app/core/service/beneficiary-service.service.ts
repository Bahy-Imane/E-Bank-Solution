import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Account} from "../model/account.model";
import {User} from "../model/user.model";
import {Beneficiary} from "../model/beneficiary.model";

@Injectable({
  providedIn: 'root'
})
export class BeneficiaryServiceService {
  private apiUrl = 'http://localhost:8080/api/beneficiary';

  constructor(private http : HttpClient) { }

  getBeneficiaries(accountId :number) : Observable<Beneficiary[]>{
    return this.http.get<Beneficiary[]>(`${this.apiUrl}/${accountId}`)
  }

  addBeneficairy(accountId : number ,beneficiary: Beneficiary): Observable<Beneficiary> {
    return this.http.post<Beneficiary>(`${this.apiUrl}/${accountId}`, beneficiary)
  }

  deleteBenefeciary(benefId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${benefId}`)
  }


  updateBenef(benefId: number, beneficiary: Beneficiary): Observable<User> {
    return this.http.put<User>(`${this.apiUrl}/${benefId}`, beneficiary)
  }
}
