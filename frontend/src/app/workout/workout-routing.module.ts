import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CreateWorkoutComponent} from './create-edit-workout/create-workout.component';


const routes: Routes = [
  {path: 'create-workout', component: CreateWorkoutComponent},
  {path: 'create-workout/:workoutId', component: CreateWorkoutComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WorkoutRoutingModule {
}
