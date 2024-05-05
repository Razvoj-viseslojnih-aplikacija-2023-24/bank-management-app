import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { BankDialogComponent } from 'src/app/dialogs/bank-dialog/bank-dialog.component';
import { Bank } from 'src/app/models/bank';
import { BankService } from 'src/app/services/bank.service';

@Component({
  selector: 'app-bank',
  templateUrl: './bank.component.html',
  styleUrls: ['./bank.component.css']
})
export class BankComponent implements OnInit, OnDestroy{

  displayedColumns = ['id', 'name', 'contact', 'tin', 'actions'];
  dataSource!: MatTableDataSource<Bank>;
  subscription!: Subscription;

  constructor (private service:BankService, public dialog:MatDialog){}

  ngOnInit(): void {
    this.loadData();
  }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  public loadData() {
    this.subscription = this.service.getAllBanks().subscribe(
    (data)=> {
      this.dataSource =  new MatTableDataSource(data);
      }
    ),
    (error:Error)=> {
      console.log(error.name + ' ' + error.message)
    }
  }

  public openDialog(flag:number, id?:number, name?: string, contact?: string, tin?: number){
    const dialogRef = this.dialog.open(BankDialogComponent, {data : {id, name, contact, tin}});
    dialogRef.componentInstance.flag = flag;
    dialogRef.afterClosed().subscribe(
      (result)=> {
        if (result==1){
          this.loadData();
        }
      }
    )
  }
}
