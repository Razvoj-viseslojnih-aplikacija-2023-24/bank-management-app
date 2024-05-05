import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ArtiklComponent } from './main/artikl/artikl.component';
import { DobavljacComponent } from './main/dobavljac/dobavljac.component';
import { StavkaPorudzbineComponent } from './main/stavka-porudzbine/stavka-porudzbine.component';
import { PorudzbinaComponent } from './main/porudzbina/porudzbina.component';
import { HomeComponent } from './utility/home/home.component';
import { AboutComponent } from './utility/about/about.component';
import { AuthorComponent } from './utility/author/author.component';
import { BankComponent } from './main/bank/bank.component';
import { UserComponent } from './main/user/user.component';
import { BranchComponent } from './main/branch/branch.component';
import { BankServiceComponent } from './main/bank-service/bank-service.component';

const routes: Routes = [
  {path:'bank', component:BankComponent},
  {path:'user', component:UserComponent},
  {path:'branch', component:BranchComponent},
  {path:'bank-service', component:BankServiceComponent},
  {path:'home', component:HomeComponent},
  {path:'about', component:AboutComponent},
  {path:'author', component:AuthorComponent},
  {path:'', component:HomeComponent, pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
