import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-fitness-type-selection',
  template: `    
    <h3>Select a fitness type</h3>
    <button
      routerLink="/workout/overview"
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
