import {async, TestBed} from '@angular/core/testing';
import {SelectableItemsComponent} from '../../../../../app/workout/create-edit-workout/administration/muscle-groups-and-exercises/selectable-items.component';
import {NO_ERRORS_SCHEMA} from '@angular/core';
import {ReactiveFormsModule} from '@angular/forms';
import {SelectableMuscleGroupOrExerciseComponent} from '../../../../../app/workout/create-edit-workout/administration/muscle-groups-and-exercises/selectable-muscle-group-or-exercise.component';
import {CreateMuscleGroupsOrExercisesComponent} from '../../../../../app/workout/create-edit-workout/administration/muscle-groups-and-exercises/create-muscle-groups-or-exercises.component';
import {MuscleGroup} from '../../../../../app/workout/shared/muscle-group';
import {Workout} from '../../../../../app/workout/shared/workout';
import {WorkoutId} from '../../../../../app/workout/shared/workout-id';
import {ReplacePipe} from '../../../../../app/workout/shared/pipes/replace.pipe';

describe('MuscleGroupOrExerciseSelectionComponent', () => {
  let fixture;
  let component;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ReactiveFormsModule],
      declarations: [
        SelectableItemsComponent,
        SelectableMuscleGroupOrExerciseComponent,
        CreateMuscleGroupsOrExercisesComponent,
        ReplacePipe
      ],
      providers: [ReplacePipe],
      schemas: [
        NO_ERRORS_SCHEMA
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(SelectableItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('shows nothing if there is no element selected', () => {
    expect(
      fixture.debugElement
        .nativeElement
        .querySelector('#empty-elements-text')
        .innerHTML
        .length)
      .toBe(0);
  });

  it('shows text that there are no element to select first', () => {

    component.currentSelection = new MuscleGroup(undefined, 'Chest', []);
    fixture.detectChanges();

    expect(
      fixture.debugElement
        .nativeElement
        .querySelector('#empty-elements-text')
        .innerHTML
        .length)
      .toBeGreaterThan(0);
  });

  it('shows existing muscle groups, exercises, or sets', () => {
    const workout: Workout =
      new Workout(
        WorkoutId.from('Workout'),
        new Date(),
        'Workout',
        [
          new MuscleGroup(undefined, 'Chest', []),
          new MuscleGroup(undefined, 'Triceps', []),
          new MuscleGroup(undefined, 'Shoulders', [])
        ]
      );
    component.currentSelection = workout;
    component.selectableItems = workout.children;
    fixture.detectChanges();

    const nativeElement = fixture.debugElement.nativeElement;
    expect(nativeElement.querySelector('#ft-select-chest-button').innerHTML)
      .toEqual('Chest');
    expect(nativeElement.querySelector('#ft-select-triceps-button').innerHTML)
      .toEqual('Triceps');
    expect(nativeElement.querySelector('#ft-select-shoulders-button').innerHTML)
      .toEqual('Shoulders');

  });

  it('can create a new muscle group to select', () => {
    let receivedEmittedMuscleGroup;
    const expectedEmittedMuscleGroup = 'Chest';
    component.createsChildEvent.subscribe(emittedElement => receivedEmittedMuscleGroup = emittedElement);

    showCreateMuscleGroupOrExerciseInputField();
    inputMuscleGroup(expectedEmittedMuscleGroup);
    submitMuscleGroup();

    expect(receivedEmittedMuscleGroup).toEqual(expectedEmittedMuscleGroup);
  });

  function showCreateMuscleGroupOrExerciseInputField() {
    fixture.nativeElement.querySelector('#ft-show-form-to-input-new-muscle-group-or-exercise-button').click();
    fixture.detectChanges();
  }

  function inputMuscleGroup(expectedEmittedMuscleGroup: string) {
    const inputField: HTMLInputElement = fixture.nativeElement
      .querySelector('#ft-input-field-to-create-new-muscle-group-or-exercise');
    inputField.value = expectedEmittedMuscleGroup;
    inputField.dispatchEvent(new Event('input'));
  }

  function submitMuscleGroup() {
    const submitButton: HTMLButtonElement = fixture.nativeElement
      .querySelector('#ft-button-to-submit-new-muscle-group-or-exercise');
    submitButton.click();
    fixture.detectChanges();
  }

  it('can create a new exercise to select', () => {

  });

  it('can delete an existing muscle group or exercise', () => {

  });

  it('can add an existing muscle group to a workout', () => {

  });

  it('can add an existing exercise to a muscle group', () => {

  });

  it('can add a set to an exercise', () => {

  });

  it('can toggle all set parts', () => {

  });


});
