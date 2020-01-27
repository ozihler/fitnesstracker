import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WorkoutSelectionComponent } from './workout-selection/workout-selection.component';
import { WorkoutsOverview } from './workouts-overview/workouts-overview.component';
import { CreateWorkoutComponent } from './create-workout/create-workout.component';
import { CreateMuscleGroupComponent } from './create-muscle-group/create-muscle-group.component';
 import { ElementSelection } from './muscle-group-selection/muscle-group-selection.component';
import {ReactiveFormsModule} from "@angular/forms";
import { CreateExerciseComponent } from './create-exercise/create-exercise.component';
import { AddExerciseComponent } from './add-exercise/add-exercise.component';
import { CreateSetComponent } from './create-set/create-set.component';
import { FitScreenPipe } from './shared/fit-screen.pipe';
import { ButtonTreeComponent } from './workout-details-view/button-group/button-tree.component';
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    WorkoutSelectionComponent,
    WorkoutsOverview,
    CreateWorkoutComponent,
    CreateMuscleGroupComponent,
     ElementSelection,
    CreateExerciseComponent,
    AddExerciseComponent,
    CreateSetComponent,
    FitScreenPipe,
    ButtonTreeComponent
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
export class AppModule { }
