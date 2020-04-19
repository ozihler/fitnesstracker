import {Component, OnInit} from '@angular/core';
import {WorkoutService} from "../shared/workout.service";
import {WorkoutSimple} from "../shared/workout-simple";
import {WorkoutId} from "../shared/workoutId";
import {Router} from "@angular/router";

@Component({
  selector: 'app-workouts-overview',
  template: `
    <h3>Select or create a workout</h3>
    <a routerLink="/workout-selection">Back</a>
    <button routerLink="/create-workout"
            class="uk-button uk-button-danger uk-width-1-1 uk-margin-small-bottom">New Workout
    </button>
    <div *ngFor="let workout of workouts">
      <button routerLink="/create-workout/{{workout.gid.value}}"
              class="uk-button uk-button-default uk-width-2-3 uk-margin-small-bottom">
        {{workout.creationDate |date: 'dd.MM.yy'}}
        {{workout.title}}
      </button>
      <button (click)="copy(workout.gid)"
              class="uk-button uk-button-default uk-width-1-3 uk-margin-small-bottom">
        <i class="fa fa-copy"></i>
      </button>
    </div>
  `
})
export class WorkoutsOverview implements OnInit {
  workouts: WorkoutSimple[] = [];

  constructor(private workoutService: WorkoutService,
              private router: Router) {
  }

  ngOnInit() {
    this.workoutService.fetchAllWorkouts().subscribe(workouts => this.workouts = workouts);
  }

  copy(workoutId: WorkoutId) {
    console.log("Workout id", workoutId)
    this.workoutService.copy(workoutId)
      .subscribe(copiedWorkoutId =>
        this.router.navigate(['create-workout', copiedWorkoutId.value]));

  }
}
