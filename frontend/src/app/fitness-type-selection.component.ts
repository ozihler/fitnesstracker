import {Component, Injectable, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {DatePipe} from '@angular/common';
import {environment} from '../environments/environment';
import {map} from 'rxjs/operators';

@Component({
  selector: 'app-fitness-type-selection',
  template: `
    <h3>Select a fitness type</h3>
    <button
      id="select-workout-button"
      routerLink="/overview"
      class="uk-button
    uk-button-default
    uk-width-1-1
    uk-margin-small-bottom">Workout
    </button>
    <!--
    <button class="uk-button
    uk-button-default
    uk-width-1-1
    uk-margin-small-bottom">Cardio
    </button>
    -->

    <span>Instantiation time: {{time}}</span>
  `,
  styles: []
})
export class FitnessTypeSelectionComponent implements OnInit {

  time: string;

  constructor(private instantiationTimeService: InstantiationTimeService) {
    this.instantiationTimeService.getInstantiationTime()
      .subscribe(time => this.time = time);
  }

  ngOnInit() {
  }

}

@Injectable({providedIn: 'root'})
class InstantiationTimeService {

  constructor(private http: HttpClient,
              private datePipe: DatePipe) {

  }

  getInstantiationTime() {
    return this.http.get<number>(environment.baseUrl + "/admin/instantiationTime")
      .pipe(
        map(timeAsUnixTimeStamp => {
          return this.datePipe.transform(
            new Date(timeAsUnixTimeStamp),
            'long',
            '',
            'de'
          )
        }));
  }

}

