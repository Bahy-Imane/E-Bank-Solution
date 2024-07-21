import {Card} from "./card.model";
import {Beneficiary} from "./beneficiary.model";
import {Transaction} from "./transaction.model";
import {User} from "./user.model";

export class Account {
  accountId !:number;
  accountType !:string;
  accNumber !:number;
  balance !:number;
  createdDate !:string;
  isActive !:boolean;
  blockRaison !:string;

  cards !:Card[];
  beneficiaries !:Beneficiary[];
  transactions !:Transaction[];
  user !:User;

}
