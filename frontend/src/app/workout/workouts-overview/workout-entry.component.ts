import {Component, EventEmitter, Input, Output} from "@angular/core";
import {WorkoutId} from "../shared/workout-id";

@Component({
  selector: `app-workout-entry`,
  template: `
    <div>
      <button routerLink="/workout/create-workout/{{workoutId?.value}}"
              class="uk-button uk-button-default uk-width-2-3">
        {{creationDate | date: 'dd.MM.yy' : '':'de'}}
        {{title}}
      </button>
      <button (click)="copy()"
              class="uk-button uk-button-default uk-width-1-3">
        <i class="fa fa-copy"></i>
      </button>
    </div>`
})
export class WorkoutEntryComponent {
  @Input() workoutId: WorkoutId;
  @Input() creationDate: Date;
  @Input() title: string;

  @Output() copyWorkoutByIdEvent = new EventEmitter<WorkoutId>();

  copy() {
    this.copyWorkoutByIdEvent.emit(this.workoutId);
  }
}
