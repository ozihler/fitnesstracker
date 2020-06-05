import {ComponentFixtureAutoDetect, TestBed} from '@angular/core/testing';
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
import {Set} from '../../../app/workout/shared/set';
import {StringifyPipePipe} from '../../../app/workout/shared/pipes/stringify.pipe';

describe('a user updating an existing workout', () => {
  let user: CreateWorkoutComponentUser;

  let selectionServiceMock;
  const toMuscleGroups = (muscleGroupNames) => muscleGroupNames.split(',').map(m => new MuscleGroup(undefined, m.trim(), []));
  const toExercises = (exerciseNames) => exerciseNames.split(',').map(m => new Exercise(undefined, m.trim(), []));

  let workoutServiceMock;
  beforeEach(() => {

    class MockRoute {
      public paramMap = of({
        get(x: string) {
          return undefined;
        }
      });
    }

    const workout = new Workout(WorkoutId.from('2123'), new Date(), Workout.WORKOUT_INITIAL_TITLE, []);
    const chest = new MuscleGroup(workout, 'Chest', []);
    workout.children.push(chest);

    const triceps = new MuscleGroup(workout, 'Triceps', []);
    workout.children.push(triceps);

    const chestBenchPress = new Exercise(chest, 'Bench Press', []);
    chest.children.push(chestBenchPress);

    const chestBenchPressSet1 = new Set(50, 'kg', 12, 50, 's', chestBenchPress, 0);
    const chestBenchPressSet2 = new Set(60, 'kg', 10, 50, 's', chestBenchPress, 1);
    const chestBenchPressSet3 = new Set(70, 'kg', 5, 50, 's', chestBenchPress, 2);
    chestBenchPress.children.push(chestBenchPressSet1);
    chestBenchPress.children.push(chestBenchPressSet2);
    chestBenchPress.children.push(chestBenchPressSet3);

    workoutServiceMock = {
      createNewOrFetchWorkoutWithId: (workoutId: string) => of(workout),
      updateWorkout: (w: Workout) => of(w)
    };

    selectionServiceMock = {
      getMuscleGroups: () => of([]),
      fetchExercisesFor: () => of(),
      newMuscleGroup: (muscleGroupNames: string) => of(toMuscleGroups(muscleGroupNames)),
      deleteMuscleGroup: (muscleGroupToDelete) => of(muscleGroupToDelete),
      createExercises: (muscleGroup, exercises) => of(toExercises(exercises)),
      addSetToExerciseExercise: (workoutId, exercise, setDetails) => of(
        new Set(
          setDetails.weight,
          setDetails.weightUnit,
          setDetails.numberOfRepetitions,
          setDetails.waitingTime,
          setDetails.waitingTimeUnit,
          exercise,
          exercise.children.length
        ))
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
        ReplacePipe,
        StringifyPipePipe,
        SetFormatPipe
      ],
      providers: [
        {provide: ComponentFixtureAutoDetect, useValue: true},
        {provide: ActivatedRoute, useClass: MockRoute},
        {provide: WorkoutService, useValue: workoutServiceMock},
        {provide: SelectionService, useValue: selectionServiceMock},
        {provide: LOCALE_ID, useValue: localeDe},
        {provide: DatePipe},
        {provide: ReplacePipe},
        {provide: StringifyPipePipe},
        {provide: SetFormatPipe}
      ],
      imports: [
        ReactiveFormsModule
      ],
      schemas: [NO_ERRORS_SCHEMA]
    }).compileComponents();

    user = new CreateWorkoutComponentUser(new CreateWorkoutComponentPageObject(TestBed.createComponent(CreateWorkoutComponent)));
  });

  it('can remove muscle groups from workout', () => {
    user.seesWorkoutContainsElementWith(new MuscleGroup(undefined, 'Chest', []), ['(1)', 'Chest', '1550 kg']);
    user.seesWorkoutContainsElementWith(new MuscleGroup(undefined, 'Triceps', []), ['(0)', 'Triceps', '0 kg']);

    user.selectsMuscleGroupInWorkout('chest');
    user.seesWorkoutContainsElementWith(new Workout(WorkoutId.from('1-1'), new Date(), 'Workout', []), ['(2)', 'New Workout', '1550 kg']);
    const chest = new MuscleGroup(undefined, 'Chest', []);
    user.seesWorkoutContainsElementWith(chest, ['(1)', 'Chest', '1550 kg']);
    user.seesWorkoutContainsElementWith(new MuscleGroup(undefined, 'Bench Press', []), ['(3)', 'Bench Press', '1550 kg']);
    user.seesWorkoutContainsElementWith(new MuscleGroup(undefined, 'Triceps', []), ['(0)', 'Triceps', '0 kg']);

    user.removesItemFromWorkoutTree(chest);
    user.seesWorkoutContainsElementWith(new Workout(WorkoutId.from('1-1'), new Date(), 'Workout', []), ['(1)', 'New Workout', '0 kg']);
    user.seesWorkoutDoesNotContain('chest');
  });

  it('can remove exercises from muscle group in workout', () => {
    const benchPress = new MuscleGroup(undefined, 'Bench Press', []);
    user.selectsMuscleGroupInWorkout('chest');
    user.seesWorkoutContainsElementWith(new MuscleGroup(undefined, 'Chest', []), ['(1)', 'Chest', '1550 kg']);

    user.seesWorkoutContainsElementWith(benchPress, ['(3)', 'Bench Press', '1550 kg']);
    user.removesItemFromWorkoutTree(benchPress);
    user.seesWorkoutContainsElementWith(new MuscleGroup(undefined, 'Chest', []), ['(0)', 'Chest', '0 kg']);
    user.seesWorkoutDoesNotContain('bench press');
  });

  it('can remove sets from exercises in muscle group of workout', () => {
    user.selectsMuscleGroupInWorkout('chest');
    user.selectsExerciseInMuscleGroup('bench press');
    const benchPress = new Exercise(new MuscleGroup(undefined, 'Chest', []), 'Bench Press', []);
    const set1 = new Set(50, 'kg', 12, 50, 's', benchPress, 0);
    const set2 = new Set(60, 'kg', 10, 50, 's', benchPress, 1);
    const set3 = new Set(70, 'kg', 5, 50, 's', benchPress, 2);

    user.seesSetOfExercise('bench press', set1);
    user.seesSetOfExercise('bench press', set2);
    user.seesSetOfExercise('bench press', set3);

    user.removesItemFromWorkoutTree(set3);
    user.seesSetOfExercise('bench press', set1);
    user.seesSetOfExercise('bench press', set2);
    user.cannotSeeSetOfExerciseWithValues(set3);

    user.removesItemFromWorkoutTree(set2);
    user.seesSetOfExercise('bench press', set1);
    user.cannotSeeSetOfExerciseWithValues(set2);
    user.cannotSeeSetOfExerciseWithValues(set3);

    user.removesItemFromWorkoutTree(set1);
    user.cannotSeeSetOfExerciseWithValues(set1);
    user.cannotSeeSetOfExerciseWithValues(set2);
    user.cannotSeeSetOfExerciseWithValues(set3);
  });
});

