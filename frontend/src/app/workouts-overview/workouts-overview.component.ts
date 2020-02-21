import {Component, OnInit} from '@angular/core';
import {WorkoutService} from "../shared/workout.service";
import {WorkoutSimple} from "../shared/workout-simple";

@Component({
  selector: 'app-workouts-overview',
  template: `
    <h3>Select or create a workout</h3>
    <a routerLink="/workout-selection">Back</a>
    <button routerLink="/create-workout"
            class="uk-button uk-button-danger uk-width-1-1 uk-margin-small-bottom">New Workout
    </button>
    <button routerLink="/create-workout/{{workout.gid.value}}"
            *ngFor="let workout of workouts"
            class="uk-button uk-button-default uk-width-1-1 uk-margin-small-bottom">{{workout.gid.value}}
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
