import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs";
import { Transaction } from "../model/transaction.model";

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  private apiUrl = 'http://localhost:8080/api/transaction';

  constructor(private http: HttpClient) { }

  getTransactions(accountId: number): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(`${this.apiUrl}/${accountId}`);
  }

  makeInternalTransaction(accountId: number, toAcc: number, amount: number, description: string): Observable<void> {
    const params = new HttpParams()
      .set('toAcc', toAcc.toString())
      .set('amount', amount.toString())
      .set('description', description);
    return this.http.post<void>(`${this.apiUrl}/interne/${accountId}`, null, { params });
  }

  makeExternalTransaction(accountId: number, benefAccNumber: number, amount: number, description: string): Observable<void> {
    const params = new HttpParams()
      .set('benefAccNumber', benefAccNumber.toString())
      .set('amount', amount.toString())
      .set('description', description);
    return this.http.post<void>(`${this.apiUrl}/externe/${accountId}`, null, { params });
  }
}
