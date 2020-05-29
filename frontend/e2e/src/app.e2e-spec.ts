import {browser, logging} from 'protractor';
import {WorkoutUser} from './users/workout-user';
import {Set} from '../../src/app/workout/shared/set';
import {Workout} from '../../src/app/workout/shared/workout';


describe('A workout user', () => {
  let user: WorkoutUser;

  beforeEach(() => {
    user = new WorkoutUser();
  });

  const selectableMuscleGroups = ['Chest', 'Triceps', 'Shoulders', 'Biceps'];
  it('can create a workout with multiple muscle groups and exercises', () => {
    // user creates new workout
    user.createsNewWorkout()
      .then(() => user.seesThatTitleOfWorkoutIs(Workout.WORKOUT_PREFIX + ' New Workout'))
      .then(() => user.seesEmptyElementsText());

    // user adds muscle groups to workout
    user.createsMuscleGroups('cHesT; tRICEPS,ShOulders. BiCePs')
      .then(() => user.seesSelectableMuscleGroups(selectableMuscleGroups))
      .then(() => user.selectsMuscleGroup('Chest'))
      .then(() => user.seesThatTitleOfWorkoutIs(Workout.WORKOUT_PREFIX + ' Chest'))
      .then(() => user.selectsMuscleGroup('Triceps'))
      .then(() => user.seesThatTitleOfWorkoutIs(Workout.WORKOUT_PREFIX + ' Chest ' + 'Triceps'));

    // user adds exercises to chest
    user.addsExercisesToMuscleGroup('bENCH PrEsS; 2#dumbbell BENch PrEss', 'Chest')
      .then(() => user.seesThatMuscleGroupHasExercisesToSelect('Chest', ['Bench Press', 'Dumbbell Bench Press']));

    // user selects exercises for chest
    user.selectsExercise('Bench Press')
      .then(() => user.seesThatMuscleGroupHasExercisesToSelect('Chest', ['Dumbbell Bench Press']))
      .then(() => user.selectsExercise('Dumbbell Bench Press'))
      .then(() => user.seesThatMuscleGroupHasExercisesToSelect('Chest', []))
      .then(() => user.seesEmptyElementsText());

    // user adds exercises to triceps
    user.addsExercisesToMuscleGroup('DIps. overhead dumbbell pull', 'Triceps')
      .then(() => user.seesThatMuscleGroupHasExercisesToSelect('Triceps', ['Overhead Dumbbell Pull', 'Dips']));

    // user selects exercises for triceps
    user.selectsExercise('Dips')
      .then(() => user.seesThatMuscleGroupHasExercisesToSelect('Triceps', ['Overhead Dumbbell Pull']))
      .then(() => user.selectsExercise('Overhead Dumbbell Pull'))
      .then(() => user.seesThatMuscleGroupHasExercisesToSelect('Triceps', []))
      .then(() => user.seesEmptyElementsText());

    const setToAdd = new Set(25, 'kg', 10, 0, 's', undefined);
    user.selectNodeInWorkoutTreeWithName('Chest')
      .then(() => user.addsSetsTo('Dumbbell Bench Press', [setToAdd]))
      .then(() => user.seesCumulatedWeights([
        {name: setToAdd.name, expectedValue: 25 * 10, isRoot: false}, // todo fix this value (2) multiplier
        {name: 'Dumbbell Bench Press', expectedValue: 25 * 10 * 2, isRoot: false},
        {name: 'Chest', expectedValue: 25 * 10 * 2, isRoot: false},
        {name: 'root', expectedValue: 25 * 10 * 2, isRoot: true},
      ]))
    // todo continue
    ;
  });


  function cleanUpWorkoutsAndMuscleGroups() {
    user.navigatesToWorkoutOverview()
      .then(() => user.deletesAllWorkouts())
      .then(() => user.createsNewWorkout())
      .then(() => user.deletesSelectableMuscleGroupsWithNames(selectableMuscleGroups))
      .then(() => user.navigatesToWorkoutOverview())
      .then(() => user.deletesAllWorkouts())
    ;
  }

  afterEach(async () => {
    cleanUpWorkoutsAndMuscleGroups();
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
