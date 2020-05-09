import {NgModule} from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';

import {WorkoutRoutingModule} from './workout-routing.module';
import {CreateWorkoutComponent} from "./create-edit-workout/create-workout.component";
import {CreateElementComponent} from "./create-edit-workout/create-element.component";
import {ElementSelection} from "./create-edit-workout/element-selection.component";
import {CreateSetComponent} from "./create-edit-workout/create-set.component";
import {TreeViewComponent} from "./create-edit-workout/tree-view.component";
import {SetValuesComponent} from "./create-edit-workout/set-values.component";
import {SelectableElementComponent} from "./create-edit-workout/selectable-element.component";
import {WorkoutTitleComponent} from "./create-edit-workout/workout-title.component";
import {ReactiveFormsModule} from "@angular/forms";
import {WorkoutEntryComponent} from "./workouts-overview/workout-entry.component";
import { SetFormatPipe } from './shared/pipes/set-format.pipe';


@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    WorkoutRoutingModule
  ],
  declarations: [
    CreateWorkoutComponent,
    CreateElementComponent,
    ElementSelection,
    CreateSetComponent,
    TreeViewComponent,
    SetValuesComponent,
    SelectableElementComponent,
    WorkoutTitleComponent,
    SetFormatPipe
  ]
})
export class WorkoutModule {
}
