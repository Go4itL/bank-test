import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AccountManagementRoutingModule } from './account-management-routing.module';
import { AccountManagementComponent } from './account-management.component';


@NgModule({
  declarations: [
    AccountManagementComponent
  ],
  imports: [
    CommonModule,
    AccountManagementRoutingModule
  ]
})
export class AccountManagementModule { }
