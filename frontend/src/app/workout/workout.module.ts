import {NgModule} from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';

import {WorkoutRoutingModule} from './workout-routing.module';
import {CreateWorkoutComponent} from "./create-workout/create-workout.component";
import {CreateElementComponent} from "./create-workout/create-element.component";
import {ElementSelection} from "./create-workout/element-selection.component";
import {CreateSetComponent} from "./create-workout/create-set.component";
import {TreeViewComponent} from "./create-workout/tree-view.component";
import {SetValuesComponent} from "./create-workout/set-values.component";
import {SelectableElementComponent} from "./create-workout/selectable-element.component";
import {WorkoutTitleComponent} from "./create-workout/workout-title.component";
import {ReactiveFormsModule} from "@angular/forms";


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
    WorkoutTitleComponent
  ],
  providers: [DatePipe]
})
export class WorkoutModule {
}
