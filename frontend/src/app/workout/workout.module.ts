import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {WorkoutRoutingModule} from './workout-routing.module';
import {CreateWorkoutComponent} from './create-edit-workout/create-workout.component';
import {CreateMuscleGroupsOrExercisesComponent} from './create-edit-workout/administration/muscle-groups-and-exercises/create-muscle-groups-or-exercises.component';
import {SelectableItemsComponent} from './create-edit-workout/administration/muscle-groups-and-exercises/selectable-items.component';
import {CreateSetComponent} from './create-edit-workout/administration/sets/create-set.component';
import {WorkoutTreeComponent} from './create-edit-workout/selection/workout-tree.component';
import {SetValuesComponent} from './create-edit-workout/administration/sets/set-values.component';
import {SelectableMuscleGroupOrExerciseComponent} from './create-edit-workout/administration/muscle-groups-and-exercises/selectable-muscle-group-or-exercise.component';
import {WorkoutHeaderComponent} from './create-edit-workout/title/workout-header.component';
import {ReactiveFormsModule} from '@angular/forms';
import {SetFormatPipe} from './shared/pipes/set-format.pipe';
import {ReplacePipe} from './shared/pipes/replace.pipe';
import {StringifyPipePipe} from './shared/pipes/stringify.pipe';
import {WorkoutsOverviewModule} from './workouts-overview/workouts-overview.module';


@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    WorkoutRoutingModule,
    WorkoutsOverviewModule
  ],
  declarations: [
    CreateWorkoutComponent,
    CreateMuscleGroupsOrExercisesComponent,
    SelectableItemsComponent,
    CreateSetComponent,
    WorkoutTreeComponent,
    SetValuesComponent,
    SelectableMuscleGroupOrExerciseComponent,
    WorkoutHeaderComponent,
    SetFormatPipe,
    ReplacePipe,
    StringifyPipePipe
  ],
  providers: [
    ReplacePipe,
    StringifyPipePipe,
    SetFormatPipe
  ]
})
export class WorkoutModule {
}
