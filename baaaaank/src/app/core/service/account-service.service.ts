import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Account} from "../model/account.model";

@Injectable({
  providedIn: 'root'
})
export class AccountServiceService {
  private apiUrl = 'http://localhost:8080/api/account';

  constructor(private http : HttpClient) { }

  getAccounts(userId :number) : Observable<Account[]>{
    return this.http.get<Account[]>(`${this.apiUrl}/${userId}`)
  }

  addAccount(userId : number ,account: Account): Observable<Account> {
    return this.http.post<Account>(`${this.apiUrl}/${userId}`, account)
  }

  closeAccount(accountId: number, raison: string): Observable<Account> {
    return this.http.put<Account>(`${this.apiUrl}/${accountId}`, {raison})
  }

}
