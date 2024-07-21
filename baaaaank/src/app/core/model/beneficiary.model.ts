import {Transaction} from "./transaction.model";
import {Account} from "./account.model";

export class Beneficiary {

  benefId !:number;
  accountType !: string;
  benefNAme !:string;
  benefAccNumber !:number;

  transactions !:Transaction[];
  account !:Account;
}
