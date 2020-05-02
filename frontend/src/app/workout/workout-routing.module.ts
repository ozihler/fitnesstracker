import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {WorkoutsOverview} from "./workouts-overview/workouts-overview.component";
import {CreateWorkoutComponent} from "./create-workout/create-workout.component";


const routes: Routes = [
  {path: "overview", component: WorkoutsOverview},
  {path: "create-workout", component: CreateWorkoutComponent},
  {path: "create-workout/:workoutId", component: CreateWorkoutComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WorkoutRoutingModule {
}
