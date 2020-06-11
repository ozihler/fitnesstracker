import {Component, EventEmitter, Input, OnChanges, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {DatePipe} from '@angular/common';

// todo split?
@Component({
  selector: 'app-workout-header',
  template: `
    <div class="uk-grid uk-grid-collapse">

      <button
        id="edit-workout-title-button"
        class="uk-button uk-button-default uk-width-1-1"
        *ngIf="!editing"
        (click)="enableEditing()">
        {{workoutTitle}}
        <i class="fa fa-edit uk-align-right"></i>
      </button>

      <div *ngIf="editing">
        <form [formGroup]="editWorkoutHeaderForm"
              (ngSubmit)="saveEditing()">

          <input
            id="workout-date-input-box"
            class="uk-input"
            type="date"
            formControlName="creationDate"/>

          <button
            id="submit-header-update-button"
            class="uk-button uk-button-primary uk-width-1-2"
            type="submit">
            <i class="fa fa-save"></i>
          </button>

          <button
            id="cancel-header-update-button"
            class="uk-button uk-button-default uk-width-1-2"
            (click)="cancelEditing()">
            <i class="fa fa-times"></i>
          </button>
        </form>
      </div>
    </div>
  `
})
export class WorkoutHeaderComponent implements OnChanges {

  @Input() workoutCreationDate: Date = new Date();
  @Input() workoutTitle = '';
  @Input() workoutId;
  @Output() changeHeaderEvent = new EventEmitter<any>();

  editing = false;
  editWorkoutHeaderForm: FormGroup;

  constructor(private datePipe: DatePipe,
              private formBuilder: FormBuilder) {
  }

  get formattedDate() {
    return this.datePipe.transform(this.workoutCreationDate, 'yyyy-MM-dd', '', 'de');
  }

  ngOnChanges() {
    this.editWorkoutHeaderForm = this.formBuilder
      .group({
          creationDate: [this.formattedDate, Validators.required]
        }
      );
  }

  enableEditing() {
    this.editing = true;
  }

  saveEditing() {
    // todo fix value propagation when untouched...
    this.changeHeaderEvent.emit(
      {
        workoutCreationDate: this.valueOf('creationDate') ? this.valueOf('creationDate') : this.workoutCreationDate
      });
    this.disableEditing();
  }

  cancelEditing() {
    this.editWorkoutHeaderForm.get('creationDate').setValue(this.formattedDate);

    this.disableEditing();
  }

  private valueOf(path: string) {
    return this.editWorkoutHeaderForm.get(path).value;
  }

  private disableEditing() {
    this.editing = false;
  }
}
