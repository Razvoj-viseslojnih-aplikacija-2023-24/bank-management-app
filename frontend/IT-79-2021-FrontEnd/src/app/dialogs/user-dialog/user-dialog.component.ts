import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-dialog',
  templateUrl: './user-dialog.component.html',
  styleUrls: ['./user-dialog.component.css']
})
export class UserDialogComponent {

  flag!: number;

  constructor(
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<User>,
    @Inject (MAT_DIALOG_DATA) public data:User,
    public service: UserService
  ){}
  
  public cancel(){
    this.dialogRef.close();
    this.snackBar.open("You cancelled editing", "Close", {duration:2500});
  }

  public add(){
    this.service.createUser(this.data).subscribe(
      (data)=>{
        this.snackBar.open(`User with name:${data.name} is added successfully`, "OK", {duration:3500});
      }
    ),
    (error:Error)=>{
      console.log(error.name+' '+ error.message);
      this.snackBar.open("Unsuccessfull adding", "Close", {duration:3500});
    }
  }

  public update(){
    this.service.updateUser(this.data).subscribe(
      (data)=>{
        this.snackBar.open(`User with name:${data.name} is updated successfully`, "OK", {duration:3500})
      }
    ),
    (error:Error)=>{
      console.log(error.name+' '+ error.message);
      this.snackBar.open("Unsuccessfull updating", "Close", {duration:3500});
    }
  }

  public delete(){
    this.service.deleteUser(this.data.id).subscribe(
      (data)=>{
        this.snackBar.open("Successfull deleting", "OK", {duration:3500});
      }
    ),
    (error:Error)=>{
      console.log(error.name+' '+ error.message);
      this.snackBar.open("Unsuccessfull deleting", "Close", {duration:3500});
    }
  }
  
}
