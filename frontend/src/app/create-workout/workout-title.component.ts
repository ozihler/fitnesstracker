import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {WorkoutTitleUpdate} from "./workout-title-update";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-workout-title',
  template: `
    <div class="uk-grid uk-grid-collapse">
      <div *ngIf="!editing">
        <span>{{workoutTitle}}</span>
        <button class="uk-button uk-button-small uk-button-default"
                (click)="enableEditing()">
          <fa name="edit"></fa>
        </button>
      </div>
      <div *ngIf="editing">
        <form [formGroup]="editForm" (ngSubmit)="saveEditing()">
          <input class="uk-input" type="date" [value]="formattedDate" formControlName="workoutCreationDate">
          <input class="uk-input" type="time" [value]="formattedTime" formControlName="workoutCreationTime">
          <input class="uk-input" type="text" [value]="title" formControlName="workoutTitle">
          <button class="uk-button uk-button-primary" type="submit">
            <fa name="save"></fa>
          </button>
        </form>
        <button class="uk-button uk-button-default"
                (click)="cancelEditing()">
          <fa name="times"></fa>
        </button>
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

  get formattedTime() {
    return this.workoutCreationDate ? this.datePipe.transform(this.workoutCreationDate, 'hh:mm', 'GMT+1') : '';

  }

  get title() {
    return this.workoutTitle ? this.workoutTitle : '';
  }

  ngOnInit() {
    this.editForm = this.formBuilder.group({
        workoutCreationDate: ['', Validators.required],
        workoutCreationTime: ['', Validators.required],
        workoutTitle: ['', Validators.required]
      }
    );
  }

  enableEditing() {
    this.editing = true;
  }

  saveEditing() {
    // todo fix value propagation when untouched...
    let update: WorkoutTitleUpdate = {
      workoutTitle: this.valueOf('workoutTitle'),
      workoutCreationDate: this.valueOf('workoutCreationDate'),
      workoutCreationTime: this.valueOf('workoutCreationTime')
    };
    this.changeTitleEvent.emit(update);
    this.cancelEditing();
  }

  private valueOf(path: string) {
    return this.editForm.get(path).value;
  }

  cancelEditing() {
    this.editing = false;
  }
}
