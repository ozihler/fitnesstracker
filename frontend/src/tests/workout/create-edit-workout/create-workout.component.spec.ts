import {ComponentFixtureAutoDetect, fakeAsync, TestBed} from '@angular/core/testing';
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
import {CreateWorkoutComponentPageObject} from '../../unit_test_page_objects/create-workout-component.utpo';
import {CreateWorkoutComponentUser} from '../../unit_test_users/create-workout-component-user.utu';
import {Exercise} from '../../../app/workout/shared/exercise';

describe('a create workout user', () => {
  let user: CreateWorkoutComponentUser;

  let selectionServiceMock;
  const toMuscleGroups = (muscleGroupNames) => muscleGroupNames.split(',').map(m => new MuscleGroup(undefined, m.trim(), []));
  const toExercises = (exerciseNames) => exerciseNames.split(',').map(m => new Exercise(undefined, m.trim(), []));

  beforeEach(() => {

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

    selectionServiceMock = {
      getMuscleGroups: () => of([]),
      newMuscleGroup: (muscleGroupNames: string) => {
        return of(toMuscleGroups(muscleGroupNames));
      },
      deleteMuscleGroup: (muscleGroupToDelete) => of(muscleGroupToDelete),
      createExercises: (muscleGroup, exercises) => of(toExercises(exercises))
    };
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
    }).compileComponents();

    user = new CreateWorkoutComponentUser(new CreateWorkoutComponentPageObject(TestBed.createComponent(CreateWorkoutComponent)));
  });

  it('can create muscle groups', fakeAsync(() => {
    const muscleGroupNames = 'Chest, Biceps, Triceps';
    const muscleGroupsArray = toMuscleGroups(muscleGroupNames);

    user.seesWorkoutTitleContains([Workout.WORKOUT_PREFIX, Workout.WORKOUT_INITIAL_TITLE]);
    user.seesEmptyMuscleGroupsText();
    user.createsMuscleGroupsToSelect(muscleGroupNames);
    user.seesSelectableMuscleGroups(muscleGroupsArray);
  }));

  it('can add a single muscle group to a workout', fakeAsync(() => {
    const muscleGroupName = 'Chest';
    user.createsMuscleGroupsToSelect(muscleGroupName);

    user.choosesFromSelectableMuscleGroups(muscleGroupName);
    user.seesWorkoutTitleContains([Workout.WORKOUT_PREFIX, muscleGroupName]);
    user.seesWorkoutContainsElementWith('root', [muscleGroupName, '(1)']);
    user.seesWorkoutContainsElementWith(muscleGroupName, [muscleGroupName, '(0)']);
    user.seesEmptyMuscleGroupsText();
  }));

  it('can add multiple muscle groups to a workout', fakeAsync(() => {
    const muscleGroupNames = 'Chest, Biceps, Triceps';
    const muscleGroupsArray = toMuscleGroups(muscleGroupNames);
    user.createsMuscleGroupsToSelect(muscleGroupNames);

    // selects chest
    user.choosesFromSelectableMuscleGroups(muscleGroupsArray[0].name);
    const titleElements = [muscleGroupsArray[0].name];
    user.seesWorkoutTitleContains(titleElements);
    user.seesWorkoutContainsElementWith('root', [muscleGroupsArray[0].name, '(1)']);
    user.seesWorkoutContainsElementWith(muscleGroupsArray[0].name, [muscleGroupsArray[0].name, '(0)']);
    user.seesSelectableMuscleGroups([muscleGroupsArray[1], muscleGroupsArray[2]]);

    // selects biceps
    user.choosesFromSelectableMuscleGroups(muscleGroupsArray[1].name);
    titleElements.push(muscleGroupsArray[1].name);
    user.seesWorkoutTitleContains(titleElements);
    user.seesWorkoutContainsElementWith('root', [muscleGroupsArray[0].name, muscleGroupsArray[1].name, '(2)']);
    user.seesWorkoutContainsElementWith(muscleGroupsArray[0].name, [muscleGroupsArray[0].name, '(0)']);
    user.seesWorkoutContainsElementWith(muscleGroupsArray[1].name, [muscleGroupsArray[1].name, '(0)']);
    user.seesSelectableMuscleGroups([muscleGroupsArray[2]]);

    // selects Triceps
    user.choosesFromSelectableMuscleGroups(muscleGroupsArray[2].name);
    titleElements.push(muscleGroupsArray[2].name);
    user.seesWorkoutTitleContains(titleElements);
    user.seesWorkoutContainsElementWith('root', [muscleGroupsArray[0].name, muscleGroupsArray[1].name, muscleGroupsArray[2].name, '(3)']);
    user.seesWorkoutContainsElementWith(muscleGroupsArray[0].name, [muscleGroupsArray[0].name, '(0)']);
    user.seesWorkoutContainsElementWith(muscleGroupsArray[1].name, [muscleGroupsArray[1].name, '(0)']);
    user.seesWorkoutContainsElementWith(muscleGroupsArray[2].name, [muscleGroupsArray[2].name, '(0)']);
    user.seesEmptyMuscleGroupsText();
  }));

  it('can remove multiple muscle groups from a workout', fakeAsync(() => {
    const muscleGroupNames = 'Chest, Biceps';
    const muscleGroupsArray = toMuscleGroups(muscleGroupNames);
    user.createsMuscleGroupsToSelect(muscleGroupNames);
    user.choosesFromSelectableMuscleGroups(muscleGroupsArray[0].name);
    user.choosesFromSelectableMuscleGroups(muscleGroupsArray[1].name);

    let titleElements = [muscleGroupsArray[0].name, muscleGroupsArray[1].name];
    user.seesWorkoutTitleContains([...titleElements]);
    user.seesWorkoutContainsElementWith('root', [...titleElements, '(2)']);
    user.seesWorkoutContainsElementWith(muscleGroupsArray[0].name, [muscleGroupsArray[0].name, '(0)']);
    user.seesWorkoutContainsElementWith(muscleGroupsArray[1].name, [muscleGroupsArray[1].name, '(0)']);
    user.seesEmptyMuscleGroupsText();

    // removes biceps
    user.removesItemFromWorkoutTree(muscleGroupsArray[1].name);
    titleElements = titleElements.filter(m => m !== muscleGroupsArray[1].name);
    user.seesWorkoutTitleContains(titleElements);
    user.seesWorkoutContainsElementWith('root', [...titleElements, '(1)']);
    user.seesWorkoutContainsElementWith(muscleGroupsArray[0].name, [muscleGroupsArray[0].name, '(0)']);
    user.seesSelectableMuscleGroups([muscleGroupsArray[1]]);

    // removes chest
    user.removesItemFromWorkoutTree(muscleGroupsArray[0].name);
    titleElements = titleElements.filter(m => m !== muscleGroupsArray[0].name); // empty array []
    user.seesWorkoutTitleContains(titleElements);
    user.seesWorkoutContainsElementWith('root', [Workout.WORKOUT_INITIAL_TITLE, '(0)']);
    user.seesSelectableMuscleGroups([muscleGroupsArray[0], muscleGroupsArray[1]]);
  }));

  it('can delete a selectable muscle group', () => {
    const muscleGroupNames = 'Chest, Biceps';
    const muscleGroupsArray = toMuscleGroups(muscleGroupNames);
    user.createsMuscleGroupsToSelect(muscleGroupNames);
    user.seesSelectableMuscleGroups(muscleGroupsArray);

    selectionServiceMock.getMuscleGroups = () => of([new MuscleGroup(undefined, muscleGroupsArray[1].name, [])]);
    user.deletesSelectableItem(muscleGroupsArray[0].name);
    user.seesSelectableMuscleGroups([muscleGroupsArray[1]]);

    selectionServiceMock.getMuscleGroups = () => of([]);
    user.deletesSelectableItem('biceps');
    user.seesEmptyMuscleGroupsText();
  });

  it('can create exercises for a muscle group', () => {
    const muscleGroupNames = 'Chest, Biceps';
    const muscleGroups = toMuscleGroups(muscleGroupNames);
    const exerciseNames = 'Bench Press, Dumbbell Flys';
    const exercises = toExercises(exerciseNames);

    selectionServiceMock
      .fetchExercisesFor = (muscleGroupName) => of([]);

    user.createsMuscleGroupsToSelect(muscleGroupNames);
    user.choosesFromSelectableMuscleGroups(muscleGroups[0].name);
    user.choosesFromSelectableMuscleGroups(muscleGroups[1].name);

    user.selectsMuscleGroupFromWorkout(muscleGroups[0].name);
    user.seesEmptyExercisesText();

    user.createsExercisesToSelect(exerciseNames);
    user.seesSelectableExercises(exercises);
  });

  it('can create sets for an exercise', () => {

  });

});

