import {Component, EventEmitter, Input, OnChanges, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {DatePipe} from '@angular/common';

// todo split?
@Component({
  selector: 'app-workout-title',
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
        <form [formGroup]="editWorkoutForm"
              (ngSubmit)="saveEditing()">
          <input
            id="workout-title-input-box"
            class="uk-input"
            type="text"
            formControlName="title"/>

          <input
            id="workout-date-input-box"
            class="uk-input"
            type="date"
            formControlName="creationDate"/>

          <button
            id="submit-title-update-button"
            class="uk-button uk-button-primary uk-width-1-2"
            type="submit">
            <i class="fa fa-save"></i>
          </button>

          <button
            id="cancel-title-update-button"
            class="uk-button uk-button-default uk-width-1-2"
            (click)="cancelEditing()">
            <i class="fa fa-times"></i>
          </button>
        </form>
      </div>
    </div>
  `
})
export class WorkoutTitleComponent implements OnChanges {

  @Input() workoutCreationDate: Date = new Date();
  @Input() workoutTitle = '';
  @Input() workoutId;
  @Output() changeTitleEvent = new EventEmitter<any>();

  editing = false;
  editWorkoutForm: FormGroup;

  constructor(private datePipe: DatePipe,
              private formBuilder: FormBuilder) {
  }

  get formattedDate() {
    return this.datePipe.transform(this.workoutCreationDate, 'yyyy-MM-dd', '', 'de');
  }

  ngOnChanges() {
    this.editWorkoutForm = this.formBuilder
      .group({
          title: [this.workoutTitle, Validators.required],
          creationDate: [this.formattedDate, Validators.required]
        }
      );
  }

  enableEditing() {
    this.editing = true;
  }

  saveEditing() {
    // todo fix value propagation when untouched...
    this.changeTitleEvent.emit(
      {
        workoutTitle: this.valueOf('title') ? this.valueOf('title') : this.workoutTitle,
        workoutCreationDate: this.valueOf('creationDate') ? this.valueOf('creationDate') : this.workoutCreationDate
      });
    this.disableEditing();
  }

  cancelEditing() {
    this.editWorkoutForm.get('title').setValue(this.workoutTitle);
    this.editWorkoutForm.get('creationDate').setValue(this.formattedDate);

    this.disableEditing();
  }

  private valueOf(path: string) {
    return this.editWorkoutForm.get(path).value;
  }

  private disableEditing() {
    this.editing = false;
  }
}
