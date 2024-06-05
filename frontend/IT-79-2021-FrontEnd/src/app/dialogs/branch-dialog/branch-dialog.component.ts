import { Branch } from './../../models/branch';
import { Bank } from './../../models/bank';
import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { BankService } from 'src/app/services/bank.service';
import { BranchService } from 'src/app/services/branch.service';

@Component({
  selector: 'app-branch-dialog',
  templateUrl: './branch-dialog.component.html',
  styleUrls: ['./branch-dialog.component.css']
})
export class BranchDialogComponent implements OnInit{
  flag!:number;
  banks!:Bank[];

  constructor(
    public snackBar:MatSnackBar,
    public dialogRef:MatDialogRef<Branch>,
    @Inject (MAT_DIALOG_DATA) public data: Branch,
    public service:BranchService,
    public bankService:BankService
  ){}

  ngOnInit(): void {
    this.bankService.getAllBanks().subscribe(
      (data) => {
        this.banks = data;
      }
    )
  }

public compare(a:any, b:any) {
  return a.id == b.id;
}

public add() {
  this.service.addBranch(this.data).subscribe(
    (data) => {
      this.snackBar.open(`Successefully added branch with ID: ${data.id}`, `OK`, {duration:2500});
    }
  ),
  (error:Error) => {
    console.log(error.name + ' ' + error.message);
    this.snackBar.open(`Unsuccessfull adding`, `Close`, {duration:2500});
  }
}

public update() {
  this.service.updateBranch(this.data).subscribe(
    (data) => {
      this.snackBar.open(`Branch with ID: ${data.id} is updated successfully`, `OK`, {duration:2500});
    }
  ),
  (error:Error) => {
    console.log(error.name + ' ' + error.message);
    this.snackBar.open(`Unsuccessfull updating`, `Close`, {duration:2500});
  }
}

public delete() {
  this.service.deleteBranch(this.data.id).subscribe(
    (data) => {
      this.snackBar.open(`Branch with ID: ${data.id} is deleted successfully`, `OK`, {duration:2500});
    }
  ),
  (error:Error) => {
    console.log(error.name + ' ' + error.message);
    this.snackBar.open(`Unsuccessfull deleting`, `Close`, {duration:2500});
  }
}

public cancel() {
  this.dialogRef.close();
  this.snackBar.open(`You cancelled editing`, `Close`, {duration:2500});
}
}