import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { UserDialogComponent } from 'src/app/dialogs/user-dialog/user-dialog.component';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {
  displayedColumns = ['id', 'name', 'surname', 'idNumber', 'actions'];
  dataSource!: MatTableDataSource<User>;
  subscription!: Subscription;

  constructor (private service:UserService, public dialog:MatDialog){}

  ngOnInit(): void {
    this.loadData();
  }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  public loadData() {
    this.subscription = this.service.getAllUsers().subscribe(
    (data)=> {
      this.dataSource =  new MatTableDataSource(data);
      }
    ),
    (error:Error)=> {
      console.log(error.name + ' ' + error.message)
    }
  }

  public openDialog(flag:number, id?:number, name?: string, surname?: string, idNumber?: number){
    const dialogRef = this.dialog.open(UserDialogComponent, {data : {id, name, surname, idNumber}});
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
