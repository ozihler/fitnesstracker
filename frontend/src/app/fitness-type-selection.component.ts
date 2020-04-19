import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-workout-selection',
  template: `
    <h3>Select a fitness type</h3>
    <button
      routerLink="/workouts-overview"
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
  `,
  styles: []
})
export class FitnessTypeSelectionComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {
  }

}
