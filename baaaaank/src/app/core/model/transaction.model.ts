import {Account} from "./account.model";
import {Beneficiary} from "./beneficiary.model";

export class Transaction {

  transId !:number;
  amount !:number;
  description !:string;
  transType !:string;
  transDateTime !:string;
  target !:number;

  account !:Account;
  beneficiary !:Beneficiary;

}
