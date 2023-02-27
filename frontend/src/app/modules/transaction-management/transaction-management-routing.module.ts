import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TransactionManagementComponent } from './transaction-management.component';

const routes: Routes = [{ path: '', component: TransactionManagementComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TransactionManagementRoutingModule { }
