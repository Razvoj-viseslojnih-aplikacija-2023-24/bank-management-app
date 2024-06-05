import { Branch } from './../../models/branch';
import { BankServiceDialogComponent } from './../../dialogs/bank-service-dialog/bank-service-dialog.component';
import { BankServiceService } from './../../services/bank-service.service';
import { BankService } from './../../models/bank-service';
import { User } from 'src/app/models/user';
import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-bank-service',
  templateUrl: './bank-service.component.html',
  styleUrls: ['./bank-service.component.css']
})
export class BankServiceComponent implements OnChanges{
  dataSource!: MatTableDataSource<BankService>;
  displayedColumns = ['id','name','serviceDescription','contractDate','commission','user','actions'];
  subscription!:Subscription;

  @Input() childSelectBranch!:Branch;
  constructor(private bankServiceService: BankServiceService,
              public dialog: MatDialog){

  }

  ngOnChanges(changes: SimpleChanges): void {
    this.loadData();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }

  public loadData(){
    this.subscription = this.bankServiceService.getServiceByBranch(this.childSelectBranch.id).subscribe(
      data => {this.dataSource = new MatTableDataSource(data);
              console.log(data)}),
      (error:Error) => {console.log(error.name + ' ' + error.message);}
  }

  public openDialog(flag:number, id?:number, name?:string, serviceDescription?:string, contractDate?: Date, commission?: number, user?:User ):void{
    const dialogRef = this.dialog.open(BankServiceDialogComponent, {data:{id,name,serviceDescription,contractDate,commission,user}});
    dialogRef.componentInstance.flag = flag;
    dialogRef.componentInstance.data.branch = this.childSelectBranch;
    dialogRef.afterClosed().subscribe(
      result =>{
        if(result == 1){
          this.loadData();
        }
      }
    )
  }
}