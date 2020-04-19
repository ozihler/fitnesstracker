import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {CommonModule, DatePipe} from "@angular/common";
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {WorkoutModule} from "./workout/workout.module";
import {FitnessTypeSelectionComponent} from "./fitness-type-selection.component";
import {WorkoutsOverview} from "./workout/workouts-overview/workouts-overview.component";

@NgModule({
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FontAwesomeModule,
    WorkoutModule
  ],
  declarations: [
    AppComponent,
    WorkoutsOverview,
    FitnessTypeSelectionComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
