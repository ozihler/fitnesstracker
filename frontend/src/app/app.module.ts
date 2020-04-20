import {BrowserModule} from '@angular/platform-browser';
import {LOCALE_ID, NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {FitnessTypeSelectionComponent} from "./fitness-type-selection.component";
import {WorkoutsOverview} from "./workout/workouts-overview/workouts-overview.component";
import {DatePipe, registerLocaleData} from "@angular/common";
import localeDe from '@angular/common/locales/de';


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
  providers: [
    {provide: LOCALE_ID, useValue: localeDe},
    {provide: DatePipe}],
  bootstrap: [AppComponent]
})
export class AppModule {

  constructor() {

    registerLocaleData(localeDe);
  }
}
