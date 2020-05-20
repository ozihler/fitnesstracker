import {Component, OnInit} from '@angular/core';
import {WorkoutService} from '../shared/services/workout.service';
import {WorkoutEntry} from '../shared/workout-entry';
import {WorkoutId} from '../shared/workout-id';
import {Router} from '@angular/router';
import {environment} from '../../../environments/environment';

@Component({
  selector: 'app-workouts-overview',
  template: `
    <h3>Select or create a workout</h3>
    <div>
      <a routerLink="/fitness-type-selection">Back</a>
    </div>
    <div>
      <button
        id="create-new-workout-button"
        routerLink="/workout/create-workout"
        class="uk-button uk-button-danger uk-width-1-1 uk-margin-small-bottom">New Workout
      </button>
    </div>
    <div>
      <button (click)="openDownloadLink()"
              class="uk-button uk-button-secondary uk-width-1-1 uk-margin-small-bottom">Download Workouts
      </button>
    </div>
    <div *ngFor="let workout of workouts">
      <app-workout-entry
        [workoutId]="workout.workoutId"
        [title]="workout.title"
        [creationDate]="workout.creationDate"
        (copyWorkoutWithIdEvent)="copyWorkoutWithId($event)"
        (deleteWorkoutWithIdEvent)="deleteWorkoutWithId($event)">
      </app-workout-entry>
    </div>
  `
})
export class WorkoutsOverviewComponent implements OnInit {
  workouts: WorkoutEntry[] = [];

  constructor(private workoutService: WorkoutService,
              private router: Router) {
  }

  ngOnInit() {
    this.workoutService.fetchAllWorkouts()
      .subscribe(workouts => this.workouts = workouts);
  }

  openDownloadLink() {
    return window.open(environment.baseUrl + '/workouts/download');
  }

  copyWorkoutWithId(workoutId: WorkoutId) {
    this.workoutService.copyWorkoutWithId(workoutId)
      .subscribe(copiedWorkoutId =>
        this.router.navigate(['workout/create-workout', copiedWorkoutId.value]));
  }

  deleteWorkoutWithId(workoutId: WorkoutId) {
    this.workoutService.deleteWorkoutWithId(workoutId)
      .subscribe(deletedWorkout => {
        this.workouts = this.workouts.filter(w => w.workoutId.value !== deletedWorkout.workoutId.value);
      });
  }
}
