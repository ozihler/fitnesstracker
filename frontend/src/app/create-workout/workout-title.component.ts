import {Component, Input, OnInit} from '@angular/core';
import {Workout} from "../shared/workout";

@Component({
  selector: 'app-workout-title',
  template: `
    <div *ngIf="!editing">
      <span>{{workout.creationDate}}</span>
      <span>{{workout.title}}</span>
    </div>
    <div></div>
  `
})
export class WorkoutTitleComponent implements OnInit {

  @Input() workout: Workout = Workout.empty();
  editing: boolean = false;

  constructor() {
  }

  ngOnInit() {
  }

}
