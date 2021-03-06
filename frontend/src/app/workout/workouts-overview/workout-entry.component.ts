import {Component, EventEmitter, Input, Output} from '@angular/core';
import {WorkoutId} from '../shared/workout-id';

@Component({
  selector: `app-workout-entry`,
  styles: [``],
  template: `
    <div class="uk-divider">
      <button (click)="delete()"
              id="ft-delete-workout-{{workoutId?.value}}"
              class="uk-button uk-button-danger uk-width-1-5 ft-delete-workout-button">
        <i class="fa fa-trash-o"></i>
      </button>
      <button id="select-workout-{{workoutId?.value}}-button"
              routerLink="/create-workout/{{workoutId?.value}}"
              class="uk-button uk-button-default uk-width-3-5 workout-selection-button">
        {{creationDate | date: 'dd.MM.yy' : '':'de'}} {{title}}
      </button>
      <button (click)="copy()"
              class="uk-button uk-button-secondary uk-width-1-5">
        <i class="fa fa-copy"></i>
      </button>
    </div>`
})
export class WorkoutEntryComponent {
  @Input() workoutId: WorkoutId;
  @Input() creationDate: Date;
  @Input() title: string;

  @Output() copyWorkoutWithIdEvent = new EventEmitter<WorkoutId>();
  @Output() deleteWorkoutWithIdEvent = new EventEmitter<WorkoutId>();

  copy() {
    this.copyWorkoutWithIdEvent.emit(this.workoutId);
  }

  delete() {
    this.deleteWorkoutWithIdEvent.emit(this.workoutId);
  }
}
