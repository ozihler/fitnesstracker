import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
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
        <fa name="edit" class="uk-align-right"></fa>
      </button>
      <div *ngIf="editing">
        <form [formGroup]="editForm" (ngSubmit)="saveEditing()">
          <input class="uk-input" type="text" [value]="title" formControlName="workoutTitle">
          <input class="uk-input" type="date" [value]="formattedDate" formControlName="workoutCreationDate">

          <button class="uk-button uk-button-primary uk-width-1-2" type="submit">
            <fa name="save"></fa>
          </button>

          <button class="uk-button uk-button-default uk-width-1-2" (click)="cancelEditing()">
            <fa name="times"></fa>
          </button>
        </form>
      </div>
    </div>
  `
})
export class WorkoutTitleComponent implements OnInit {

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
    return this.workoutCreationDate ? this.datePipe.transform(this.workoutCreationDate, 'yyyy-MM-dd') : '';
  }

  get title() {
    return this.workoutTitle ? this.workoutTitle : '';
  }

  ngOnInit() {
    this.editForm = this.formBuilder.group({
        workoutTitle: ['', Validators.required],
        workoutCreationDate: ['', Validators.required]
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
    console.error("EMITTING: ", update);
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
