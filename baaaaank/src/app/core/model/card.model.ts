import {Account} from "./account.model";

export class Card {

  cardId !: number;
  cardNumber !:number;
  expiryDate !:string;
  cardType !: string;
  isActive = true;
  isBlocked =false;
  blockReason !:string;

  account !:Account;

}
