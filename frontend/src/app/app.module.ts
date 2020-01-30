import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {WorkoutSelectionComponent} from './workout-selection/workout-selection.component';
import {WorkoutsOverview} from './workouts-overview/workouts-overview.component';
import {CreateWorkoutComponent} from './create-workout/create-workout.component';
import {CreateElementComponent} from './create-muscle-group/create-element.component';
import {ElementSelection} from './element-selection/element-selection.component';
import {ReactiveFormsModule} from "@angular/forms";
import {CreateSetComponent} from './create-set/create-set.component';
import {FitScreenPipe} from './shared/fit-screen.pipe';
import {TreeViewComponent} from './workout-details-view/tree-view.component';
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    WorkoutSelectionComponent,
    WorkoutsOverview,
    CreateWorkoutComponent,
    CreateElementComponent,
    ElementSelection,
    CreateSetComponent,
    FitScreenPipe,
    TreeViewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
