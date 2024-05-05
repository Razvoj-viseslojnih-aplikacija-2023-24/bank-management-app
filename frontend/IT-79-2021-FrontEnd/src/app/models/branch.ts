import { Bank } from "./bank";

export class Branch{
    id!: number;
    address!: string;
    counters!: number;
    hasBoss!: boolean;
    bank!: Bank;
}