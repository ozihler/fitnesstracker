import {async, TestBed} from "@angular/core/testing";
import {MuscleGroupOrExerciseSelectionComponent} from "../../../../../app/workout/create-edit-workout/administration/muscle-groups-and-exercises/muscle-group-or-exercise-selection.component";
import {NO_ERRORS_SCHEMA} from "@angular/core";
import {ReactiveFormsModule} from "@angular/forms";
import {SelectableMuscleGroupOrExerciseComponent} from "../../../../../app/workout/create-edit-workout/administration/muscle-groups-and-exercises/selectable-muscle-group-or-exercise.component";
import {CreateMuscleGroupsOrExercisesComponent} from "../../../../../app/workout/create-edit-workout/administration/muscle-groups-and-exercises/create-muscle-groups-or-exercises.component";
import {MuscleGroup} from "../../../../../app/workout/shared/muscle-group";
import {Workout} from "../../../../../app/workout/shared/workout";
import {WorkoutId} from "../../../../../app/workout/shared/workout-id";
import {ReplacePipe} from "../../../../../app/workout/shared/pipes/replace.pipe";

describe('MuscleGroupOrExerciseSelectionComponent', () => {
  let fixture;
  let component;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ReactiveFormsModule],
      declarations: [
        MuscleGroupOrExerciseSelectionComponent,
        SelectableMuscleGroupOrExerciseComponent,
        CreateMuscleGroupsOrExercisesComponent,
        ReplacePipe
      ],
      schemas: [
        NO_ERRORS_SCHEMA
      ]
    }).compileComponents()

    fixture = TestBed.createComponent(MuscleGroupOrExerciseSelectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('shows existing muscle groups, exercises, or sets', () => {
    let workout: Workout =
      new Workout(
        WorkoutId.from("Workout"),
        new Date(),
        "Workout",
        [
          new MuscleGroup(undefined, "Chest", []),
          new MuscleGroup(undefined, "Triceps", []),
          new MuscleGroup(undefined, "Shoulders", [])
        ]
      );
    component.currentSelection = workout
    component.selectableElements = workout.children;

    fixture.detectChanges();

    let nativeElement = fixture.debugElement.nativeElement;
    expect(nativeElement.querySelector('#ft-select-chest-button').innerHTML)
      .toEqual("Chest");
    expect(nativeElement.querySelector('#ft-select-triceps-button').innerHTML)
      .toEqual("Triceps");
    expect(nativeElement.querySelector('#ft-select-shoulders-button').innerHTML)
      .toEqual("Shoulders");

  });

  it('can create a new muscle group to select', () => {

  });

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
