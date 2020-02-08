import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {WorkoutSelectionComponent} from './workout-selection/workout-selection.component';
import {WorkoutsOverview} from './workouts-overview/workouts-overview.component';
import {CreateWorkoutComponent} from './create-workout/create-workout.component';
import {CreateElementComponent} from './create-workout/create-element.component';
import {ElementSelection} from './create-workout/element-selection.component';
import {ReactiveFormsModule} from "@angular/forms";
import {CreateSetComponent} from './create-workout/create-set.component';
import {TreeViewComponent} from './create-workout/tree-view.component';
import {HttpClientModule} from "@angular/common/http";
import {SetValuesComponent} from './create-workout/set-values.component';
import {SelectableElementComponent} from './create-workout/selectable-element.component';
import {AngularFontAwesomeModule} from "angular-font-awesome";

@NgModule({
  declarations: [
    AppComponent,
    WorkoutSelectionComponent,
    WorkoutsOverview,
    CreateWorkoutComponent,
    CreateElementComponent,
    ElementSelection,
    CreateSetComponent,
    TreeViewComponent,
    SetValuesComponent,
    SelectableElementComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    AngularFontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
