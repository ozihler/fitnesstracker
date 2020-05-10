import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Location} from "@angular/common";

@Component({
  selector: 'app-create-muscle-groups-and-exercises',
  template: `
    <!-- Button that says "Create Muscle Group/Exercise"-->
    <div *ngIf="showButton">

      <button
        id="ft-show-form-to-input-new-muscle-group-or-exercise-button"
        (click)="showInputForm()"
        class="uk-button uk-width-1-1 uk-text-truncate">
        Create {{typename}}
      </button>

    </div>

    <!-- form to input new muscle groups/exercises-->
    <div *ngIf="!showButton">

      <form
        [formGroup]="muscleGroupsOrExercisesInputForm"
        (ngSubmit)="createMuscleGroupsOrExercises()">

        <div class="uk-margin">
          <input
            id="ft-input-field-to-create-new-muscle-group-or-exercise"
            class="uk-input"
                 formControlName="muscleGroupsOrExercisesInputForm"
                 type="text">
        </div>

        <button
          id="ft-button-to-submit-new-muscle-group-or-exercise"
          class="uk-button uk-width-1-1 uk-text-truncate"
          type="submit">
          Ok
        </button>

      </form>
    </div>
  `,
  styles: []
})
export class CreateMuscleGroupsOrExercisesComponent implements OnInit {

  @Output() createElementsEvent = new EventEmitter<string>();
  @Input() typename: string;

  showButton: boolean = true;
  muscleGroupsOrExercisesInputForm: FormGroup;

  constructor(private location: Location,
              private formBuilder: FormBuilder) {

  }

  ngOnInit() {
    this.initForm();
  }

  private initForm() {
    if (this.muscleGroupsOrExercisesInputForm) {
      return;
    }
    this.muscleGroupsOrExercisesInputForm = this.formBuilder.group({
      element: ["", Validators.required]
    });
  }

  createMuscleGroupsOrExercises() {
    if (this.hasEnteredAnything()) {
      this.emit(this.currentElementValue());
    }
  }

  private emit(createdElements: string) {
    this.showButton = true;
    this.muscleGroupsOrExercisesInputForm.reset();
    this.createElementsEvent.emit(createdElements);
  }

  private hasEnteredAnything() {
    return !!this.currentElementValue();
  }

  currentElementValue() {
    return this.muscleGroupsOrExercisesInputForm.get('element').value ? this.muscleGroupsOrExercisesInputForm.get('element').value.trim() : "";
  }

  showInputForm() {
    this.showButton = false;
  }

}
