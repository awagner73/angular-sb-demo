import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {DaleOverviewComponent} from './dale-overview/dale-overview.component';
import {DarlehenCreateComponent} from './darlehen-create/darlehen-create.component';

const routes: Routes = [
  {path: 'overview', component: DaleOverviewComponent},
  {path: 'create', component: DarlehenCreateComponent},
  {path: '', pathMatch: 'full', redirectTo: '/overview'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
