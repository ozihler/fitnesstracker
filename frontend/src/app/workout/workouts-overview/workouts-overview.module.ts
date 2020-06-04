import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {WorkoutEntryComponent} from './workout-entry.component';
import {WorkoutsOverviewComponent} from './workouts-overview.component';
import {RouterModule} from '@angular/router';
import {WorkoutsOverviewRoutingModule} from './workouts-overview-routing.module';


@NgModule({
  declarations: [
    WorkoutEntryComponent,
    WorkoutsOverviewComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    WorkoutsOverviewRoutingModule
  ]
})
export class WorkoutsOverviewModule {
}
