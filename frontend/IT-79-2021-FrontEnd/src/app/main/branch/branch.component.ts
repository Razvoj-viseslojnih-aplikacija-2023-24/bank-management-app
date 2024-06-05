import { BranchService } from './../../services/branch.service';
import { Branch } from './../../models/branch';
import { Bank } from './../../models/bank';
import { User } from 'src/app/models/user';
import { BranchDialogComponent } from './../../dialogs/branch-dialog/branch-dialog.component';
import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';


@Component({
  selector: 'app-branch',
  templateUrl:'./branch.component.html',
  styleUrls: ['./branch.component.css']
})
export class BranchComponent implements OnInit, OnDestroy{
  displayedColumns = ['id', 'address', 'counters', 'hasBoss', 'bank', 'actions'];
  dataSource!:MatTableDataSource<Branch>;
  subscription!:Subscription;
  parentSelectedBranch!:Branch;

  @ViewChild(MatSort, {static:false}) sort!:MatSort;
  @ViewChild(MatPaginator, {static:false}) paginator!:MatPaginator;

  constructor(private service:BranchService, public dialog:MatDialog) {}

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }

  public selectRow(row:Branch) {
    this.parentSelectedBranch = row;
  }

  public loadData(){
    this.subscription = this.service.getAllBranches().subscribe(
      (data) => {
        this.dataSource = new MatTableDataSource(data);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      }
    ),
    (error:Error) => {
        console.log(error.name + ' ' + error.message);
    }
  }

  public openDialog(flag:number, id?:number, address?:string, counters?:number, hasBoss?:boolean, bank?:Bank)
  {
    const dialogRef = this.dialog.open(BranchDialogComponent, {data: {id, address, counters, hasBoss, bank}});
    dialogRef.componentInstance.flag = flag;
    dialogRef.afterClosed().subscribe(
      (result) => {
        if(result == 1){
          this.loadData();
        }
      }
    )
  }
  public applyFilter(filter:any) {
    filter = filter.target.value;
    filter = filter.trim();
    filter = filter.toLocaleLowerCase();
    this.dataSource.filter = filter;
  }

}