import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-workout-selection',
  template: `
    <button
      routerLink="/workouts-overview"
      class="uk-button
    uk-button-default
    uk-width-1-1
    uk-margin-small-bottom">Workout
    </button>
    <button class="uk-button
    uk-button-default
    uk-width-1-1
    uk-margin-small-bottom">Cardio
    </button>
  `,
  styles: []
})
export class WorkoutSelectionComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {
  }

}
