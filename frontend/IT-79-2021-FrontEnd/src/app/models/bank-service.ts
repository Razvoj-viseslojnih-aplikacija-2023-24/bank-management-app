import { Branch } from "./branch";
import { User } from "./user";

export class BankService{
    id!:number;
    name!: string;
    serviceDescription!: string;
    contractDate!: Date;
    commission!: number;
    branch!: Branch;
    user!: User;
}