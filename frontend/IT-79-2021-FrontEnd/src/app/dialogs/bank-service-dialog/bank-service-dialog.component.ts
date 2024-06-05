import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { BankService } from 'src/app/models/bank-service';
import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { BankServiceService } from 'src/app/services/bank-service.service';

@Component({
  selector: 'app-bank-service-dialog',
  templateUrl: './bank-service-dialog.component.html',
  styleUrls: ['./bank-service-dialog.component.css']
})
export class BankServiceDialogComponent {

  flag!: number;
  users!: User[];

  constructor(
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<BankService>,
    @Inject (MAT_DIALOG_DATA) public data: BankService,
    public service:BankServiceService,
    public userService:UserService
  ){}

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe(
      (data) => {
        this.users = data;
      }
    )
  }

  public compare(a:any, b:any){
    return a.id == b.id;
  }

  public add(){
    this.service.addBankService(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Successfully added bank service with ID: ${data.id}`, `OK`, {duration:2500});
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Unsuccessfull adding`, `Close`, {duration:1000});
    }
  }

  public update(){
    this.service.updateBankService(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Successfully updated bank service with ID: ${data.id}`, `OK`, {duration:2500});
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Unsuccessfull updating`, `Close`, {duration:1000});
    }
  }

  public delete(){
    this.service.deleteBankService(this.data.id).subscribe(
      (data) => {
        this.snackBar.open(`${data}`, `OK`, {duration:2500});
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Unsuccessfull deleting`, `Close`, {duration:1000});
    }
  }

  public cancel(){
    this.dialogRef.close();
    this.snackBar.open(`You cancelled editing`, `Close`, {duration:2500});
  }
}