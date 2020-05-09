import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {WorkoutRoutingModule} from './workout-routing.module';
import {CreateWorkoutComponent} from "./create-edit-workout/create-workout.component";
import {MuscleGroupsAndExercisesAdministrationComponent} from "./create-edit-workout/administration/muscle-groups-and-exercises/muscle-groups-and-exercises-administration.component";
import {ElementSelection} from "./create-edit-workout/administration/muscle-groups-and-exercises/muscle-group-or-exercise-selection.component";
import {CreateSetComponent} from "./create-edit-workout/administration/sets/create-set.component";
import {TreeViewComponent} from "./create-edit-workout/selection/tree-view.component";
import {SetValuesComponent} from "./create-edit-workout/administration/sets/set-values.component";
import {SelectableMuscleGroupOrExerciseComponent} from "./create-edit-workout/administration/muscle-groups-and-exercises/selectable-muscle-group-or-exercise.component";
import {WorkoutTitleComponent} from "./create-edit-workout/title/workout-title.component";
import {ReactiveFormsModule} from "@angular/forms";
import {SetFormatPipe} from './shared/pipes/set-format.pipe';


@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    WorkoutRoutingModule
  ],
  declarations: [
    CreateWorkoutComponent,
    MuscleGroupsAndExercisesAdministrationComponent,
    ElementSelection,
    CreateSetComponent,
    TreeViewComponent,
    SetValuesComponent,
    SelectableMuscleGroupOrExerciseComponent,
    WorkoutTitleComponent,
    SetFormatPipe
  ]
})
export class WorkoutModule {
}
