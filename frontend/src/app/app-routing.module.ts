import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {WorkoutSelectionComponent} from "./workout-selection/workout-selection.component";
import {WorkoutsOverview} from "./workouts-overview/workouts-overview.component";
import {CreateWorkoutComponent} from "./create-workout/create-workout.component";
import {CreateElementComponent} from "./create-workout/create-element.component";
import {CreateSetComponent} from "./create-workout/create-set.component";


const routes: Routes = [
  {path: "workouts-overview", component: WorkoutsOverview},
  {path: "create-workout", component: CreateWorkoutComponent},
  {path: "create-workout/:workout-gid", component: CreateWorkoutComponent},
  {path: "create-muscle-group", component: CreateElementComponent},
  {path: "muscle-group/:name/create-exercise", component: CreateElementComponent},
  {path: "create-set", component: CreateSetComponent},
  {path: "workout-selection", component: WorkoutSelectionComponent},
  {path: "workouts-overview", component: WorkoutsOverview},
  {path: '', redirectTo: 'workout-selection', pathMatch: 'full'},
  {path: '**', redirectTo: 'workout-selection', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
