import { Branch } from "./branch";
import { User } from "./user";

export class BankService{
    getAllBanks() {
      throw new Error('Method not implemented.');
    }
    id!:number;
    name!: string;
    serviceDescription!: string;
    contractDate!: Date;
    commission!: number;
    branch!: Branch;
    user!: User;
}