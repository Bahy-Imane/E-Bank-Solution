import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Card } from "../model/card.model";

@Injectable({
  providedIn: 'root'
})
export class CardService {
  private apiUrl = 'http://localhost:8080/api/card';

  constructor(private http: HttpClient) { }

  getCards(accountId: number): Observable<Card[]> {
    return this.http.get<Card[]>(`${this.apiUrl}/${accountId}`);
  }

  addCard(accountId: number, card: Card): Observable<Card> {
    return this.http.post<Card>(`${this.apiUrl}/${accountId}`, card);
  }

  updateCard(cardId: number, blockReason: string): Observable<Card> {
    return this.http.put<Card>(`${this.apiUrl}/${cardId}`, { blockReason });
  }

  activateCard(cardId: number): Observable<Card> {
    return this.http.put<Card>(`${this.apiUrl}/activate/${cardId}`, {});
  }

  deactivateCard(cardId: number): Observable<Card> {
    return this.http.put<Card>(`${this.apiUrl}/deactivate/${cardId}`, {});
  }
}

