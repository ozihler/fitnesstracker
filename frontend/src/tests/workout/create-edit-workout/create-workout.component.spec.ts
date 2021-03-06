import {ComponentFixtureAutoDetect, fakeAsync, TestBed, tick} from '@angular/core/testing';
import {CreateWorkoutComponent} from '../../../app/workout/create-edit-workout/create-workout.component';
import {LOCALE_ID, NO_ERRORS_SCHEMA} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {WorkoutService} from '../../../app/workout/shared/services/workout.service';
import {SelectionService} from '../../../app/workout/shared/services/selection.service';
import {of} from 'rxjs';
import {Workout} from '../../../app/workout/shared/workout';
import {WorkoutId} from '../../../app/workout/shared/workout-id';
import {WorkoutHeaderComponent} from '../../../app/workout/create-edit-workout/title/workout-header.component';
import {WorkoutTreeComponent} from '../../../app/workout/create-edit-workout/selection/workout-tree.component';
// tslint:disable-next-line:max-line-length
import {SelectableItemsComponent} from '../../../app/workout/create-edit-workout/administration/muscle-groups-and-exercises/selectable-items.component';
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

describe('a create workout user', () => {
  let user: CreateWorkoutComponentUser;

  let selectionServiceMock;
  const toMuscleGroups = (muscleGroupNames) => muscleGroupNames.split(',').map(m => new MuscleGroup(undefined, m.trim(), []));
  const toExercises = (exerciseNames) => {
    const exercises = exerciseNames.split(',');

    return exercises.map(name => {
      const multiplier = name.indexOf('2#') >= 0 ? 2 : 1;
      const replacedName = name.replace('2#', '').trim();
      return new Exercise(undefined, replacedName, [], multiplier);
    });
  };

  let workoutServiceMock;
  beforeEach(() => {

    class MockRoute {
      public paramMap = of({
        get(x: string) {
          return undefined;
        }
      });
    }

    workoutServiceMock = {
      createNewOrFetchWorkoutWithId: (workoutId: string) =>
        of(new Workout(WorkoutId.from('1234'), new Date(), Workout.WORKOUT_INITIAL_TITLE, undefined)),
      updateWorkout: (w: Workout) => of(w)
    };

    // todo maybe use spy for selectionService mock and override methods as needed...
    selectionServiceMock = {
      fetchExercisesFor: (muscleGroup) => of([]),
      getMuscleGroups: () => of([]),
      newMuscleGroup: (muscleGroupNames: string) => of(toMuscleGroups(muscleGroupNames)),
      deleteMuscleGroup: (muscleGroupToDelete) => of(muscleGroupToDelete),
      createExercises: (muscleGroup, exercises) => of(toExercises(exercises)),
      addSetToExerciseExercise: (workoutId, exercise, setDetails: Set) => of(
        new Set(
          setDetails.weight,
          setDetails.weightUnit,
          setDetails.numberOfRepetitions,
          setDetails.waitingTime,
          setDetails.waitingTimeUnit,
          exercise,
          exercise.children.length,
          exercise.multiplier
        ))
    };
    registerLocaleData(localeDe);
    TestBed.configureTestingModule({
      declarations: [
        CreateWorkoutComponent,
        CreateMuscleGroupsOrExercisesComponent,
        SelectableItemsComponent,
        CreateSetComponent,
        WorkoutTreeComponent,
        SetValuesComponent,
        SelectableMuscleGroupOrExerciseComponent,
        WorkoutHeaderComponent,
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

  it('can create muscle groups', fakeAsync(() => {
    const muscleGroupNames = 'Chest, Biceps, Triceps';
    const muscleGroupsArray = toMuscleGroups(muscleGroupNames);

    user.seesWorkoutTitleContains([Workout.WORKOUT_INITIAL_TITLE]);
    user.seesEmptyMuscleGroupsText();
    user.createsMuscleGroupsToSelect(muscleGroupNames);
    user.seesSelectableMuscleGroups(muscleGroupsArray);
  }));

  const workout = new Workout(WorkoutId.from('1-1'), new Date(), 'Workout', []);

  it('can add a single muscle group to a workout', fakeAsync(() => {
    const muscleGroupName = 'Chest';
    const chest = new MuscleGroup(undefined, muscleGroupName, []);
    user.seesSelectedItemInWorkoutTreeIs(workout);
    user.createsMuscleGroupsToSelect(chest.name);
    user.choosesFromSelectableMuscleGroups(chest.name);
    user.seesWorkoutTitleContains([chest.name]);
    user.seesWorkoutContainsElementWith(workout, [chest.name, '(1)']);
    user.seesWorkoutContainsElementWith(chest, [chest.name, '(0)']);
    user.seesEmptyMuscleGroupsText();
    user.selectsMuscleGroupInWorkout(chest.name);
    user.seesSelectedItemInWorkoutTreeIsNot(workout);
    user.seesSelectedItemInWorkoutTreeIs(chest);

  }));

  it('can add multiple muscle groups to a workout', fakeAsync(() => {
    const muscleGroupNames = 'Chest, Biceps, Triceps';
    const muscleGroupsArray = toMuscleGroups(muscleGroupNames);
    user.createsMuscleGroupsToSelect(muscleGroupNames);

    // selects chest
    user.choosesFromSelectableMuscleGroups(muscleGroupsArray[0].name);
    const titleElements = [muscleGroupsArray[0].name];
    user.seesWorkoutTitleContains(titleElements);
    user.seesWorkoutContainsElementWith(workout, [muscleGroupsArray[0].name, '(1)']);
    user.seesWorkoutContainsElementWith(muscleGroupsArray[0], [muscleGroupsArray[0].name, '(0)']);
    user.seesSelectableMuscleGroups([muscleGroupsArray[1], muscleGroupsArray[2]]);

    // selects biceps
    user.choosesFromSelectableMuscleGroups(muscleGroupsArray[1].name);
    titleElements.push(muscleGroupsArray[1].name);
    user.seesWorkoutTitleContains(titleElements);
    user.seesWorkoutContainsElementWith(workout,
      [muscleGroupsArray[0].name, muscleGroupsArray[1].name, '(2)']);
    user.seesWorkoutContainsElementWith(muscleGroupsArray[0], [muscleGroupsArray[0].name, '(0)']);
    user.seesWorkoutContainsElementWith(muscleGroupsArray[1], [muscleGroupsArray[1].name, '(0)']);
    user.seesSelectableMuscleGroups([muscleGroupsArray[2]]);

    // selects Triceps
    user.choosesFromSelectableMuscleGroups(muscleGroupsArray[2].name);
    titleElements.push(muscleGroupsArray[2].name);
    user.seesWorkoutTitleContains(titleElements);
    user.seesWorkoutContainsElementWith(workout,
      [muscleGroupsArray[0].name, muscleGroupsArray[1].name, muscleGroupsArray[2].name, '(3)']);
    user.seesWorkoutContainsElementWith(muscleGroupsArray[0], [muscleGroupsArray[0].name, '(0)']);
    user.seesWorkoutContainsElementWith(muscleGroupsArray[1], [muscleGroupsArray[1].name, '(0)']);
    user.seesWorkoutContainsElementWith(muscleGroupsArray[2], [muscleGroupsArray[2].name, '(0)']);
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
    user.seesWorkoutContainsElementWith(workout, [...titleElements, '(2)']);
    user.seesWorkoutContainsElementWith(muscleGroupsArray[0], [muscleGroupsArray[0].name, '(0)']);
    user.seesWorkoutContainsElementWith(muscleGroupsArray[1], [muscleGroupsArray[1].name, '(0)']);
    user.seesEmptyMuscleGroupsText();

    // removes biceps
    user.removesItemFromWorkoutTree(muscleGroupsArray[1]);
    titleElements = titleElements.filter(m => m !== muscleGroupsArray[1].name);
    user.seesWorkoutTitleContains(titleElements);
    user.seesWorkoutContainsElementWith(workout, [...titleElements, '(1)']);
    user.seesWorkoutContainsElementWith(muscleGroupsArray[0], [muscleGroupsArray[0].name, '(0)']);
    user.seesSelectableMuscleGroups([muscleGroupsArray[1]]);

    // removes chest
    user.removesItemFromWorkoutTree(muscleGroupsArray[0]);
    titleElements = titleElements.filter(m => m !== muscleGroupsArray[0].name); // empty array []
    user.seesWorkoutTitleContains(titleElements);
    user.seesWorkoutContainsElementWith(workout, [Workout.WORKOUT_INITIAL_TITLE, '(0)']);
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

    selectionServiceMock.fetchExercisesFor = (muscleGroupName) => of([]);

    user.createsMuscleGroupsToSelect(muscleGroupNames);
    user.choosesFromSelectableMuscleGroups(muscleGroups[0].name);
    user.choosesFromSelectableMuscleGroups(muscleGroups[1].name);
    user.seesSelectedItemInWorkoutTreeIs(workout);

    user.selectsMuscleGroupInWorkout(muscleGroups[0].name);
    user.seesSelectedItemInWorkoutTreeIs(muscleGroups[0]);
    user.seesEmptyExercisesText();

    user.createsExercisesToSelect(exerciseNames);
    user.seesSelectableExercises(exercises);
  });

  it('can create and add sets to an exercise', fakeAsync(() => {
    const muscleGroupNames = 'Chest, Biceps';
    const muscleGroups = toMuscleGroups(muscleGroupNames);
    const exerciseNames = 'Bench Press, Dumbbell Flys';
    const exercises = toExercises(exerciseNames);

    const weights = [
      {value: '10', times: 3},
      {value: '1', times: 5},
      {value: '0.5', times: 3},
      {value: '-0.5', times: 2},
      {value: '-1', times: 2},
      {value: '-10', times: 1},
    ];

    const repetitions = [
      {value: '5', times: 3},
      {value: '2', times: 2},
      {value: '1', times: 1},
      {value: '-5', times: 1},
      {value: '-2', times: 1},
      {value: '-1', times: 1},
    ];
    const waitingTimes = [
      {value: '10', times: 10},
      {value: '5', times: 3},
      {value: '1', times: 4},
      {value: '-10', times: 2},
      {value: '-5', times: 4},
      {value: '-1', times: 4},
    ];
    selectionServiceMock.fetchExercisesFor = (muscleGroupName) => of([]);

    // test
    user.createsMuscleGroupsToSelect(muscleGroupNames);
    user.choosesFromSelectableMuscleGroups(muscleGroups[0].name);
    user.selectsMuscleGroupInWorkout(muscleGroups[0].name);
    user.seesSelectedItemInWorkoutTreeIs(muscleGroups[0]);
    user.createsExercisesToSelect(exerciseNames);
    user.choosesFromSelectableExercises(exercises[0].name);
    user.seesWorkoutContainsElementWith(exercises[0], ['(0)', exercises[0].name]);
    user.selectsExerciseInMuscleGroup(exercises[0].name);
    user.seesSelectedItemInWorkoutTreeIs(exercises[0]);

    const multiplier = 1;
    user.configuresSetWithValues(weights, repetitions, waitingTimes, multiplier);

    const expected = new Set(user.sumOf(weights), 'kg', user.sumOf(repetitions), user.sumOf(waitingTimes), 's', exercises[0], 0);
    user.seesCurrentSetValuesToAddAre(expected, multiplier);
    user.addsSetToExercise();
    user.seesThatSetWasAddedToExercise(exercises[0].name, expected);

    // can add set twice without inputting data again by just clicking button
    user.addsSetToExercise();
    user.seesThatSetWasAddedToExercise(exercises[0].name,
      new Set(user.sumOf(weights), 'kg', user.sumOf(repetitions), user.sumOf(waitingTimes), 's', exercises[0], 1));

    const directlyEnteredSet = new Set(34, 'kg', 9, 87, 's', exercises[0], 2);
    user.configuresSetByDirectInputtingValues(
      directlyEnteredSet.weight,
      directlyEnteredSet.numberOfRepetitions,
      directlyEnteredSet.waitingTime
    );
    user.addsSetToExercise();
    user.seesThatSetWasAddedToExercise(exercises[0].name, directlyEnteredSet);
  }));

  it('can adapt the date of the workout', fakeAsync(() => {
    user.opensWorkoutTitleAndDateEditing();
    user.seesWorkoutDateIs(new Date());
    const newDate = new Date('1986-08-13');
    user.setsWorkoutDateTo(newDate);
    user.submitsDateUpdate();
    tick();
    user.opensWorkoutTitleAndDateEditing();
    user.seesWorkoutDateIs(newDate);
  }));

  it('can cancel date adaption', fakeAsync(() => {
    user.opensWorkoutTitleAndDateEditing();
    const oldDate = new Date();
    user.seesWorkoutDateIs(oldDate);
    const newDate = new Date('1986-08-13');
    user.setsWorkoutDateTo(newDate);
    user.cancelsDateUpdate();
    tick();
    user.opensWorkoutTitleAndDateEditing();
    user.seesWorkoutDateIs(oldDate);
  }));

  it('can remove any set without affecting other sets with the same values', fakeAsync(() => {
    user.createsMuscleGroupsToSelect('Chest, Triceps');
    user.choosesFromSelectableMuscleGroups('chest');
    user.choosesFromSelectableMuscleGroups('triceps');
    user.selectsMuscleGroupInWorkout('chest');
    user.createsExercisesToSelect('Bench Press, 2#Dumbbell Bench Press');
    user.choosesFromSelectableExercises('Bench Press');
    user.choosesFromSelectableExercises('Dumbbell Bench Press');
    user.selectsExerciseInMuscleGroup('Bench Press');
    user.configuresSetByDirectInputtingValues(20, 12, 50);
    user.addsSetToExercise();
    user.configuresSetByDirectInputtingValues(20, 12, 50);
    user.addsSetToExercise();
    user.configuresSetByDirectInputtingValues(20, 12, 50);
    user.addsSetToExercise();

    user.selectsExerciseInMuscleGroup('Dumbbell Bench Press');
    user.configuresSetByDirectInputtingValues(20, 12, 50);
    user.addsSetToExercise();
    user.configuresSetByDirectInputtingValues(20, 12, 50);
    user.addsSetToExercise();
    user.configuresSetByDirectInputtingValues(20, 12, 50);
    user.addsSetToExercise();

    const benchPressExercise = new Exercise(
      new MuscleGroup(undefined, 'Chest', []),
      'Bench Press',
      []);
    benchPressExercise.children = [
      new Set(20, 'kg', 12, 50, 's', benchPressExercise, 0),
      new Set(20, 'kg', 12, 50, 's', benchPressExercise, 1),
      new Set(20, 'kg', 12, 50, 's', benchPressExercise, 2)
    ];
    user.selectsExerciseInMuscleGroup('Bench Press');
    user.seesSelectedItemInWorkoutTreeIs(new Exercise(undefined, 'Bench Press'));
    user.seesThatExerciseHasSets('Bench Press', benchPressExercise.children);

    const dumbbellBenchPress = new Exercise(
      new MuscleGroup(undefined, 'Chest', []),
      'Dumbbell Bench Press',
      [],
      2);

    dumbbellBenchPress.children = [
      new Set(20, 'kg', 12, 50, 's', dumbbellBenchPress, 0, 2),
      new Set(20, 'kg', 12, 50, 's', dumbbellBenchPress, 1, 2),
      new Set(20, 'kg', 12, 50, 's', dumbbellBenchPress, 2, 2)
    ];

    user.selectsExerciseInMuscleGroup('Dumbbell Bench Press');
    user.seesThatExerciseHasSets('Dumbbell Bench Press', dumbbellBenchPress.children);

    user.selectsExerciseInMuscleGroup('Bench Press');
    user.seesSelectedItemInWorkoutTreeIs(new Exercise(undefined, 'Bench Press'));
    user.removesItemFromWorkoutTree(benchPressExercise.children[0]);

    user.seesThatExerciseHasSets('Bench Press', [
      new Set(20, 'kg', 12, 50, 's', benchPressExercise, 1, 1),
      new Set(20, 'kg', 12, 50, 's', benchPressExercise, 2, 1)
    ]);

    user.selectsExerciseInMuscleGroup('Dumbbell Bench Press');
    user.seesSelectedItemInWorkoutTreeIs(new Exercise(undefined, 'Dumbbell Bench Press'));
    user.seesThatExerciseHasSets('Dumbbell Bench Press', [
      new Set(20, 'kg', 12, 50, 's', dumbbellBenchPress, 0, 2),
      new Set(20, 'kg', 12, 50, 's', dumbbellBenchPress, 1, 2),
      new Set(20, 'kg', 12, 50, 's', dumbbellBenchPress, 2, 2)
    ]);
  }));

  it('sees how adding muscle groups, exercises, and sets affects displayed numbers and weights in workout tree node labels',
    fakeAsync(() => {
      user.seesSelectedItemInWorkoutTreeIs(workout);
      user.seesWorkoutContainsElementWith(workout, ['(0)']);
      user.createsMuscleGroupsToSelect('Chest, Triceps');
      user.choosesFromSelectableMuscleGroups('chest');
      user.seesWorkoutContainsElementWith(workout, ['(1)']);
      user.choosesFromSelectableMuscleGroups('triceps');

      user.seesWorkoutContainsElementWith(workout, ['(2)']);
      user.selectsMuscleGroupInWorkout('chest');
      user.seesSelectedItemInWorkoutTreeIs(new MuscleGroup(undefined, 'Chest'));

      user.createsExercisesToSelect('Bench Press, Dumbbell Bench Press');
      user.choosesFromSelectableExercises('Bench Press');
      user.seesWorkoutContainsElementWith(workout, ['(2)']);
      user.seesWorkoutContainsElementWith(new MuscleGroup(undefined, 'Chest'), ['(1)']);
      user.choosesFromSelectableExercises('Dumbbell Bench Press');
      user.seesWorkoutContainsElementWith(new MuscleGroup(undefined, 'Chest'), ['(2)']);
      user.selectsExerciseInMuscleGroup('Bench Press');
      user.seesSelectedItemInWorkoutTreeIs(new Exercise(undefined, 'Bench Press'));

      user.seesWorkoutContainsElementWith(new Exercise(undefined, 'Bench Press'), ['(0)']);
      user.configuresSetByDirectInputtingValues(20, 12, 50);
      user.addsSetToExercise();
      user.seesWorkoutContainsElementWith(workout, ['(2)', (20 * 12) + '']);
      user.seesWorkoutContainsElementWith(new MuscleGroup(undefined, 'Chest'), ['(2)', (20 * 12) + '']);
      user.seesWorkoutContainsElementWith(new Exercise(undefined, 'Bench Press'), ['(1)', (20 * 12) + '']);

      user.configuresSetByDirectInputtingValues(20, 12, 50);
      user.addsSetToExercise();
      user.seesWorkoutContainsElementWith(workout, ['(2)', (2 * 20 * 12) + '']);
      user.seesWorkoutContainsElementWith(new MuscleGroup(undefined, 'Chest'), ['(2)', (2 * 20 * 12) + '']);
      user.seesWorkoutContainsElementWith(new Exercise(undefined, 'Bench Press'), ['(2)', (2 * 20 * 12) + '']);

      user.configuresSetByDirectInputtingValues(20, 12, 50);
      user.addsSetToExercise();
      user.seesWorkoutContainsElementWith(workout, ['(2)', (3 * 20 * 12) + '']);
      user.seesWorkoutContainsElementWith(new MuscleGroup(undefined, 'Chest'), ['(2)', (3 * 20 * 12) + '']);
      user.seesWorkoutContainsElementWith(new Exercise(undefined, 'Bench Press'), ['(3)', (3 * 20 * 12) + '']);

      user.selectsExerciseInMuscleGroup('Dumbbell Bench Press');
      user.seesSelectedItemInWorkoutTreeIs(new Exercise(undefined, 'Dumbbell Bench Press'));

      user.seesWorkoutContainsElementWith(new Exercise(undefined, 'Dumbbell Bench Press'), ['(0)']);

      user.configuresSetByDirectInputtingValues(20, 12, 50);
      user.addsSetToExercise();
      user.seesWorkoutContainsElementWith(workout, ['(2)', (4 * 20 * 12) + '']);
      user.seesWorkoutContainsElementWith(new MuscleGroup(undefined, 'Chest'), ['(2)', (4 * 20 * 12) + '']);
      user.seesWorkoutContainsElementWith(new Exercise(undefined, 'Dumbbell Bench Press'), ['(1)', (20 * 12) + '']);

      user.configuresSetByDirectInputtingValues(20, 12, 50);
      user.addsSetToExercise();
      user.seesWorkoutContainsElementWith(workout, ['(2)', (5 * 20 * 12) + '']);
      user.seesWorkoutContainsElementWith(new MuscleGroup(undefined, 'Chest'), ['(2)', (5 * 20 * 12) + '']);
      user.seesWorkoutContainsElementWith(new Exercise(undefined, 'Dumbbell Bench Press'), ['(2)', (2 * 20 * 12) + '']);

      user.configuresSetByDirectInputtingValues(20, 12, 50);
      user.addsSetToExercise();
      user.seesWorkoutContainsElementWith(workout, ['(2)', (6 * 20 * 12) + '']);
      user.seesWorkoutContainsElementWith(new MuscleGroup(undefined, 'Chest'), ['(2)', (6 * 20 * 12) + '']);
      user.seesWorkoutContainsElementWith(new Exercise(undefined, 'Dumbbell Bench Press'), ['(3)', (3 * 20 * 12) + '']);

      // continue with removing nodes again
    }));

});

