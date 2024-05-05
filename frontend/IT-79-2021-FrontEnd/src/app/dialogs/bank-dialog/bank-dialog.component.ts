import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Bank } from 'src/app/models/bank';
import { BankService } from 'src/app/services/bank.service';

@Component({
  selector: 'app-bank-dialog',
  templateUrl: './bank-dialog.component.html',
  styleUrls: ['./bank-dialog.component.css']
})
export class BankDialogComponent {

  flag!: number;

  constructor(
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<Bank>,
    @Inject (MAT_DIALOG_DATA) public data:Bank,
    public service: BankService
  ){}
  
  public cancel(){
    this.dialogRef.close();
    this.snackBar.open("You cancelled editing", "Close", {duration:2500});
  }

  public add(){
    this.service.createBank(this.data).subscribe(
      (data)=>{
        this.snackBar.open(`Bank with name:${data.name} is added successfully`, "OK", {duration:3500});
      }
    ),
    (error:Error)=>{
      console.log(error.name+' '+ error.message);
      this.snackBar.open("Unsuccessfull adding", "Close", {duration:3500});
    }
  }

  public update(){
    this.service.updateBank(this.data).subscribe(
      (data)=>{
        this.snackBar.open(`Bank with name:${data.name} is updated successfully`, "OK", {duration:3500})
      }
    ),
    (error:Error)=>{
      console.log(error.name+' '+ error.message);
      this.snackBar.open("Unsuccessfull updating", "Close", {duration:3500});
    }
  }

  public delete(){
    this.service.deleteBank(this.data.id).subscribe(
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
