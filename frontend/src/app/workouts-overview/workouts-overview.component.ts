import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-workouts-overview',
  template: `
    <button *ngFor="let workout of workouts"
            class="uk-button uk-button-default uk-width-1-1 uk-margin-small-bottom">{{workout}}
    </button>
    <i class="fa fa-plus-circle" aria-hidden="true"></i>
    <button routerLink="/create-workout"
            class="uk-button uk-button-default uk-width-1-1 uk-margin-small-bottom">New Workout
    </button>
  `,
  styles: []
})
export class WorkoutsOverview implements OnInit {
  workouts: String[] = [
    "So, 22.01.2020",
  ];


  constructor( ) {
  }

  ngOnInit() {
  }
}
