import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TransactionManagementRoutingModule } from './transaction-management-routing.module';
import { TransactionManagementComponent } from './transaction-management.component';


@NgModule({
  declarations: [
    TransactionManagementComponent
  ],
  imports: [
    CommonModule,
    TransactionManagementRoutingModule
  ]
})
export class TransactionManagementModule { }
