import {Component, OnInit} from '@angular/core';
import {WorkoutService} from "../shared/workout.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-workouts-overview',
  template: `
    <button *ngFor="let workout of workouts"
            class="uk-button uk-button-default uk-width-1-1 uk-margin-small-bottom">{{workout}}
    </button>
    <i class="fa fa-plus-circle" aria-hidden="true"></i>
    <button (click)="createWorkout()"
            class="uk-button uk-button-default uk-width-1-1 uk-margin-small-bottom">New Workout
    </button>
  `,
  styles: []
})
export class WorkoutsOverview implements OnInit {
  workouts: String[] = [
    "So, 22.01.2020",
    "Sa, 21.01.2020",
    "Fr, 20.01.2020",
    "Do, 19.01.2020",
    "Mi, 18.01.2020",
    "Di, 17.01.2020",
    "Mo, 16.01.2020",
  ];


  constructor(private workoutService: WorkoutService,
              private router: Router) {
  }

  ngOnInit() {
  }

  createWorkout() {
    this.workoutService.newWorkout()
      .subscribe(workout => {
        this.router.navigate(['workouts/' + workout.id]);
      });
  }
}
