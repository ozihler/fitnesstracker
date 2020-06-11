import {async, TestBed} from '@angular/core/testing';
import {NO_ERRORS_SCHEMA} from '@angular/core';
import {CreateMuscleGroupsOrExercisesComponent} from '../../../../../app/workout/create-edit-workout/administration/muscle-groups-and-exercises/create-muscle-groups-or-exercises.component';
import {ReactiveFormsModule} from '@angular/forms';


describe('CreateMuscleGroupsOrExercisesComponent', () => {
  let fixture;
  let component;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ReactiveFormsModule],
      declarations: [
        CreateMuscleGroupsOrExercisesComponent
      ],
      schemas: [
        NO_ERRORS_SCHEMA
      ]
    }).compileComponents();
    fixture = TestBed.createComponent(CreateMuscleGroupsOrExercisesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('initially shows the create (muscle groups or exercises) button', () => {
    let createButton: HTMLButtonElement = fixture.nativeElement
      .querySelector('#ft-show-form-to-input-new-muscle-group-or-exercise-button');

    let inputField: HTMLInputElement = fixture.nativeElement
      .querySelector('#ft-input-field-to-create-new-muscle-group-or-exercise');

    let submitButton: HTMLButtonElement = fixture.nativeElement
      .querySelector('#ft-button-to-submit-new-muscle-group-or-exercise');

    expect(createButton).toBeDefined();
    expect(inputField).toBeNull();
    expect(submitButton).toBeNull();
  });

  it('submits the input containing muscle groups or exercises to create',
    () => {
      let expectedInputFormValue = 'Chest, Triceps';
      let receivedInputValue: string;

      let createButton: HTMLButtonElement = fixture.nativeElement
        .querySelector('#ft-show-form-to-input-new-muscle-group-or-exercise-button');
      createButton.click();
      fixture.detectChanges();

      let inputField: HTMLInputElement = fixture.nativeElement
        .querySelector('#ft-input-field-to-create-new-muscle-group-or-exercise');
      let submitButton: HTMLButtonElement = fixture.nativeElement
        .querySelector('#ft-button-to-submit-new-muscle-group-or-exercise');

      /**
       * To simulate user input, you can find the input element and set its value property.

       You will call fixture.detectChanges() to trigger Angular's change detection.
       But there is an essential, intermediate step.

       Angular doesn't know that you set the input element's value property.
       It won't read that property until you raise the element's input event by calling dispatchEvent().
       Then you call detectChanges().
       */
      inputField.value = expectedInputFormValue;
      inputField.dispatchEvent(new Event('input'))
      fixture.detectChanges()

      component.createItemsEvent
        .subscribe(inputFormValue => receivedInputValue = inputFormValue);

      submitButton.click();

      expect(receivedInputValue).toBe(expectedInputFormValue);
    });

});
