import {ComponentFixture, ComponentFixtureAutoDetect, fakeAsync, TestBed} from '@angular/core/testing';
import {CreateWorkoutComponent} from '../../../app/workout/create-edit-workout/create-workout.component';
import {LOCALE_ID, NO_ERRORS_SCHEMA} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {WorkoutService} from '../../../app/workout/shared/services/workout.service';
import {SelectionService} from '../../../app/workout/shared/services/selection.service';
import {of} from 'rxjs';
import {Workout} from '../../../app/workout/shared/workout';
import {WorkoutId} from '../../../app/workout/shared/workout-id';
import {WorkoutTitleComponent} from '../../../app/workout/create-edit-workout/title/workout-title.component';
import {WorkoutTreeComponent} from '../../../app/workout/create-edit-workout/selection/workout-tree.component';
// tslint:disable-next-line:max-line-length
import {MuscleGroupOrExerciseSelectionComponent} from '../../../app/workout/create-edit-workout/administration/muscle-groups-and-exercises/muscle-group-or-exercise-selection.component';
import {DatePipe, registerLocaleData} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {ReplacePipe} from '../../../app/workout/shared/pipes/replace.pipe';
import localeDe from '@angular/common/locales/de';
// tslint:disable-next-line:max-line-length
import {CreateMuscleGroupsOrExercisesComponent} from '../../../app/workout/create-edit-workout/administration/muscle-groups-and-exercises/create-muscle-groups-or-exercises.component';
import {CreateSetComponent} from '../../../app/workout/create-edit-workout/administration/sets/create-set.component';
import {MuscleGroup} from '../../../app/workout/shared/muscle-group';
import {SetValuesComponent} from '../../../app/workout/create-edit-workout/administration/sets/set-values.component';
// tslint:disable-next-line:max-line-length
import {SelectableMuscleGroupOrExerciseComponent} from '../../../app/workout/create-edit-workout/administration/muscle-groups-and-exercises/selectable-muscle-group-or-exercise.component';
import {SetFormatPipe} from '../../../app/workout/shared/pipes/set-format.pipe';
import {Button} from '../../unit_test_page_elements/button.utpe';
import {InputField} from '../../unit_test_page_elements/input-field.utpe';
import {Span} from '../../unit_test_page_elements/span.utbp';

fdescribe('Create Workout', () => {

  let component: CreateWorkoutComponent;
  let fixture: ComponentFixture<CreateWorkoutComponent>;

  class MockRoute {
    public paramMap = of({
      get(x: string) {
        return undefined;
      }
    });
  }

  const workoutServiceMock = {
    createNewOrFetchWorkoutWithId: (workoutId: string) => of(
      new Workout(WorkoutId.from('1234'), new Date(), Workout.WORKOUT_INITIAL_TITLE, undefined)),
    updateWorkout: (workout: Workout) => of(workout)
  };
  const muscleGroups = [];
  const selectionServiceMock = {
    getMuscleGroups: () => of(muscleGroups),
    newMuscleGroup: (muscleGroupNames: string) => {
      return of(toMuscleGroups(muscleGroupNames));
    }
  };

  const toMuscleGroups = (muscleGroupNames) => muscleGroupNames.split(',').map(m => new MuscleGroup(undefined, m, []));

  function find(htmlElementName: string) {
    return fixture.nativeElement.querySelector(htmlElementName);
  }

  beforeEach(() => {
    registerLocaleData(localeDe);
    TestBed.configureTestingModule({
      declarations: [
        CreateWorkoutComponent,
        CreateMuscleGroupsOrExercisesComponent,
        MuscleGroupOrExerciseSelectionComponent,
        CreateSetComponent,
        WorkoutTreeComponent,
        SetValuesComponent,
        SelectableMuscleGroupOrExerciseComponent,
        WorkoutTitleComponent,
        SetFormatPipe,
        ReplacePipe
      ],
      providers: [
        {provide: ActivatedRoute, useClass: MockRoute},
        {provide: WorkoutService, useValue: workoutServiceMock},
        {provide: SelectionService, useValue: selectionServiceMock},
        {provide: ComponentFixtureAutoDetect, useValue: true},
        {provide: LOCALE_ID, useValue: localeDe},
        {provide: DatePipe},
        {provide: ReplacePipe},
      ],
      imports: [
        ReactiveFormsModule
      ],
      schemas: [NO_ERRORS_SCHEMA]
    })
      .compileComponents();

    fixture = TestBed.createComponent(CreateWorkoutComponent);
    component = fixture.componentInstance;

  });

  function replace(input: string, stringToReplace: string = ' ', replacement: string = '-') {
    return new ReplacePipe().transform(input, stringToReplace, replacement).toLowerCase();
  }


  it('add muscle groups to workout', fakeAsync(() => {
    const editWorkoutTitleButton = new Button(find('#edit-workout-title-button'));
    const emptyElementsTextSpan = new Span(find('#empty-elements-text'));
    const showFormToInputNewMuscleGroupOrExerciseButton = new Button(find('#ft-show-form-to-input-new-muscle-group-or-exercise-button'));

    const muscleGroupNames = 'Chest, Biceps, Triceps';
    const muscleGroupsArray = toMuscleGroups(muscleGroupNames);

    expect(editWorkoutTitleButton.label).toContain(Workout.WORKOUT_PREFIX + ' New Workout');
    expect(emptyElementsTextSpan.text).toContain('Create a new one first!');
    showFormToInputNewMuscleGroupOrExerciseButton.click();

    const inputFieldToCreateNewMuscleGroupOrExercise = new InputField(find('#ft-input-field-to-create-new-muscle-group-or-exercise'));
    inputFieldToCreateNewMuscleGroupOrExercise.text = muscleGroupNames;

    const buttonToSubmitNewMuscleGroupOrExercise = new Button(find('#ft-button-to-submit-new-muscle-group-or-exercise'));
    buttonToSubmitNewMuscleGroupOrExercise.click();

    muscleGroupsArray.forEach(muscleGroup => {
      expect(new Button(find('#ft-select-' + replace(muscleGroup.name) + '-button')).label).toEqual(muscleGroup.name);
    });

    const chest = replace(muscleGroupsArray[0].name);
    const selectChestButton = new Button(find('#ft-select-' + chest + '-button'));
    selectChestButton.click();

    // continue here
    const selectChestEditableNode = new Button(find('#select-' + chest + '-editable-node'));

    expect(selectChestEditableNode.label).toContain(muscleGroupsArray[0].name);

  }));
});

