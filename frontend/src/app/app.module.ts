import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {FitnessTypeSelectionComponent} from "./fitness-type-selection.component";
import {WorkoutsOverview} from "./workout/workouts-overview/workouts-overview.component";

@NgModule({
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FontAwesomeModule
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
