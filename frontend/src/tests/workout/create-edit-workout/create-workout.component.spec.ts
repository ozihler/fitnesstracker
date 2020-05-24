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

fdescribe('a user', () => {
  let user: CreateWorkoutComponentUser;

  const toMuscleGroups = (muscleGroupNames) => muscleGroupNames.split(',').map(m => new MuscleGroup(undefined, m, []));

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

    const selectionServiceMock = {
      getMuscleGroups: () => of([]),
      newMuscleGroup: (muscleGroupNames: string) => {
        return of(toMuscleGroups(muscleGroupNames));
      }
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

    user.seesWorkoutTitleIs(Workout.WORKOUT_PREFIX + ' New Workout');
    user.seesEmptyElementsText();
    user.createsMuscleGroupsToSelect(muscleGroupNames);
    user.seesSelectableMuscleGroups(muscleGroupsArray);
  }));

  it('can add a single muscle group to a workout', fakeAsync(() => {
    const muscleGroupName = 'Chest';
    user.createsMuscleGroupsToSelect(muscleGroupName);

    user.selects(muscleGroupName);
    user.seesWorkoutTitleIs(Workout.WORKOUT_PREFIX + ' ' + muscleGroupName);
    user.seesWorkoutContainsElementWith('root', [muscleGroupName, '(1)']);
    user.seesWorkoutContainsElementWith(muscleGroupName, [muscleGroupName, '(0)']);
    user.seesEmptyElementsText();
  }));

  it('can add multiple muscle groups to a workout', fakeAsync(() => {
    const muscleGroupNames = 'Chest, Biceps, Triceps';
    const muscleGroupsArray = toMuscleGroups(muscleGroupNames);
    user.createsMuscleGroupsToSelect(muscleGroupNames);

    // selects chest
    user.selects(muscleGroupsArray[0].name);
    let newTitle = Workout.WORKOUT_PREFIX + ' ' + muscleGroupsArray[0].name;
    user.seesWorkoutTitleIs(newTitle);
    user.seesWorkoutContainsElementWith('root', [muscleGroupsArray[0].name, '(1)']);
    user.seesWorkoutContainsElementWith(muscleGroupsArray[0].name, [muscleGroupsArray[0].name, '(0)']);
    user.seesSelectableMuscleGroups([muscleGroupsArray[1], muscleGroupsArray[2]]);

    // selects biceps
    user.selects(muscleGroupsArray[1].name);
    newTitle += ' ' + muscleGroupsArray[1].name;
    user.seesWorkoutTitleIs(newTitle);
    user.seesWorkoutContainsElementWith('root', [muscleGroupsArray[0].name, muscleGroupsArray[1].name, '(2)']);
    user.seesWorkoutContainsElementWith(muscleGroupsArray[0].name, [muscleGroupsArray[0].name, '(0)']);
    user.seesWorkoutContainsElementWith(muscleGroupsArray[1].name, [muscleGroupsArray[1].name, '(0)']);
    user.seesSelectableMuscleGroups([muscleGroupsArray[2]]);

    // selects Triceps
    user.selects(muscleGroupsArray[2].name);
    newTitle += ' ' + muscleGroupsArray[2].name;
    user.seesWorkoutTitleIs(newTitle);
    user.seesWorkoutContainsElementWith('root', [muscleGroupsArray[0].name, muscleGroupsArray[1].name, muscleGroupsArray[2].name, '(3)']);
    user.seesWorkoutContainsElementWith(muscleGroupsArray[0].name, [muscleGroupsArray[0].name, '(0)']);
    user.seesWorkoutContainsElementWith(muscleGroupsArray[1].name, [muscleGroupsArray[1].name, '(0)']);
    user.seesWorkoutContainsElementWith(muscleGroupsArray[2].name, [muscleGroupsArray[2].name, '(0)']);
    user.seesEmptyElementsText();

  }));
});

