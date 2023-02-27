import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AgentManagementRoutingModule } from './agent-management-routing.module';
import { AgentManagementComponent } from './agent-management.component';


@NgModule({
  declarations: [
    AgentManagementComponent
  ],
  imports: [
    CommonModule,
    AgentManagementRoutingModule
  ]
})
export class AgentManagementModule { }
