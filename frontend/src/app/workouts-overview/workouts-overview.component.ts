import {Component, OnInit} from '@angular/core';
import {WorkoutService} from "../shared/workout.service";
import {WorkoutSimple} from "../shared/workout-simple";

@Component({
  selector: 'app-workouts-overview',
  template: `
    <button routerLink="/create-workout/{{workout.gid.value}}"
            *ngFor="let workout of workouts"
            class="uk-button uk-button-default uk-width-1-1 uk-margin-small-bottom">{{workout.title}} {{workout.creationDate}}
    </button>
    <i class="fa fa-plus-circle" aria-hidden="true"></i>
    <button routerLink="/create-workout"
            class="uk-button uk-button-default uk-width-1-1 uk-margin-small-bottom">New Workout
    </button>
  `,
  styles: []
})
export class WorkoutsOverview implements OnInit {
  workouts: WorkoutSimple[] = [];


  constructor(private workoutService: WorkoutService) {
  }

  ngOnInit() {
    this.workoutService.fetchAllWorkouts().subscribe(workouts => this.workouts = workouts);
  }
}
