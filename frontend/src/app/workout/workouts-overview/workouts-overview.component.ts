import {Component, OnInit} from '@angular/core';
import {WorkoutService} from "../shared/workout.service";
import {WorkoutSimple} from "../shared/workout-simple";
import {WorkoutId} from "../shared/workoutId";
import {Router} from "@angular/router";
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-workouts-overview',
  template: `
    <h3>Select or create a workout</h3>
    <div>
      <a routerLink="/fitness-type-selection">Back</a>
    </div>
    <div>
      <button routerLink="/workout/create-workout"
              class="uk-button uk-button-danger uk-width-1-1 uk-margin-small-bottom">New Workout
      </button>
    </div>
    <div>
      <button (click)="openDownloadLink()"
              class="uk-button uk-button-secondary uk-width-1-1 uk-margin-small-bottom">Download Workouts
      </button>
    </div>
    <div *ngFor="let workout of workouts">
      <button routerLink="/workout/create-workout/{{workout.workoutId.value}}"
              class="uk-button uk-button-default uk-width-2-3">
        {{workout.creationDate | date: 'dd.MM.yy' : '':'de'}}
        {{workout.title}}
      </button>
      <button (click)="copy(workout.workoutId)"
              class="uk-button uk-button-default uk-width-1-3">
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

  openDownloadLink() {
    return window.open(environment.baseUrl + "/workouts/download");
  }

  copy(workoutId: WorkoutId) {
    console.log("Workout id", workoutId)
    this.workoutService.copy(workoutId)
      .subscribe(copiedWorkoutId =>
        this.router.navigate(['workout/create-workout', copiedWorkoutId.value]));

  }
}
