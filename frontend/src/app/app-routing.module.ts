import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { ProtectedRouteGuard } from './configuration/protected-route.guard';

const routes: Routes = [
  {
    path: 'agent', loadChildren: () => import('./modules/agent-management/agent-management.module').then(m => m.AgentManagementModule),
    canActivateChild: [ProtectedRouteGuard]
  },
  {
    path: 'transaction', loadChildren: () => import('./modules/transaction-management/transaction-management.module').then(m => m.TransactionManagementModule),
    canActivateChild: [ProtectedRouteGuard]
  },
  {
    path: 'account', loadChildren: () => import('./modules/account-management/account-management.module').then(m => m.AccountManagementModule),
    canActivateChild: [ProtectedRouteGuard]
  },
  {
    path: '', component: LoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
