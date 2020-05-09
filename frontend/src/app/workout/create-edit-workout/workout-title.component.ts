import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from '@angular/core';
import {WorkoutTitleUpdate} from "./workout-title-update";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-workout-title',
  template: `
    <div class="uk-grid uk-grid-collapse">
      <button class="uk-button uk-button-default uk-width-1-1"
              *ngIf="!editing"
              (click)="enableEditing()">
        {{workoutTitle}}
        <i class="fa fa-edit uk-align-right"></i>
      </button>
      <div *ngIf="editing">
        <form [formGroup]="editForm" (ngSubmit)="saveEditing()">
          <input class="uk-input" type="text" [value]="title" formControlName="workoutTitle">
          <input class="uk-input" type="date" [value]="formattedDate" formControlName="workoutCreationDate">

          <button class="uk-button uk-button-primary uk-width-1-2" type="submit">
            <i class="fa fa-save"></i>
          </button>

          <button class="uk-button uk-button-default uk-width-1-2" (click)="cancelEditing()">
            <i class="fa fa-times"></i>
          </button>
        </form>
      </div>
    </div>
  `
})
export class WorkoutTitleComponent implements OnChanges {

  @Input() workoutCreationDate: Date = new Date();
  @Input() workoutTitle: string = '';
  @Input() workoutId: string = '';
  @Output() changeTitleEvent = new EventEmitter<any>();
  editing: boolean = false;
  editForm: FormGroup;

  constructor(private datePipe: DatePipe,
              private formBuilder: FormBuilder) {

  }

  get formattedDate() {
    return this.workoutCreationDate ? this.datePipe.transform(this.workoutCreationDate, 'yyyy-MM-dd', '', 'de') : '';
  }

  get title() {
    return this.workoutTitle ? this.workoutTitle : '';
  }

  ngOnChanges() {
    this.editForm = this.formBuilder.group({
        workoutTitle: [this.title, Validators.required],
        workoutCreationDate: [this.formattedDate, Validators.required]
      }
    );
  }

  enableEditing() {
    this.editing = true;
  }

  saveEditing() {
    // todo fix value propagation when untouched...
    let update: WorkoutTitleUpdate = {
      workoutTitle: this.valueOf('workoutTitle')?this.valueOf('workoutTitle'):this.workoutTitle,
      workoutCreationDate: this.valueOf('workoutCreationDate') ? this.valueOf('workoutCreationDate') : this.workoutCreationDate
    };
    this.changeTitleEvent.emit(update);
    this.cancelEditing();
  }

  private valueOf(path: string) {
    return this.editForm.get(path).value
  }

  cancelEditing() {
    this.editing = false;
  }
}
