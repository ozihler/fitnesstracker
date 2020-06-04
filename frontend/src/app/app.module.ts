import {BrowserModule} from '@angular/platform-browser';
import {LOCALE_ID, NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {FitnessTypeSelectionComponent} from './fitness-type-selection.component';
import {DatePipe, registerLocaleData} from '@angular/common';
import localeDe from '@angular/common/locales/de';
import {WorkoutModule} from './workout/workout.module';
import {StoreModule} from '@ngrx/store';
import {StoreDevtoolsModule} from '@ngrx/store-devtools';
import {environment} from '../environments/environment';
import {EffectsModule} from '@ngrx/effects';


@NgModule({
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FontAwesomeModule,
    WorkoutModule,
    StoreModule.forRoot({}, {}),
    StoreDevtoolsModule.instrument({maxAge: 25, logOnly: environment.production}),
    EffectsModule.forRoot([])
  ],
  declarations: [
    AppComponent,
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
