import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {WorkoutsOverviewComponent} from './workouts-overview.component';
import {AppRoutingModule} from '../../app-routing.module';

const routes: Routes = [
  {path: 'overview', component: WorkoutsOverviewComponent}
];

@NgModule({
  imports: [
    AppRoutingModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class WorkoutsOverviewRoutingModule {
}
