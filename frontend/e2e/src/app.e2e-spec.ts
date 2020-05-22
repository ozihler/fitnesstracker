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

    // todo summarise larger parts to smaller subsections
    user.createsNewWorkout()
      .then(() => user.seesThatTitleOfWorkoutIs(Workout.WORKOUT_PREFIX + ' New Workout'))
      .then(() => user.seesEmptyElementsText())
      .then(() => user.createsMuscleGroups('cHesT; tRICEPS,ShOulders. BiCePs'))
      .then(() => user.seesSelectableMuscleGroups(selectableMuscleGroups))
      .then(() => user.selectsMuscleGroup('Chest'))
      .then(() => user.seesThatTitleOfWorkoutIs(Workout.WORKOUT_PREFIX + ' Chest'))
      .then(() => user.addsExercisesToMuscleGroup('bENCH PrEsS; 2#dumbbell BENch PrEss', 'Chest'))
      .then(() => user.seesThatMuscleGroupHasExercisesToSelect('Chest', ['Bench Press', 'Dumbbell Bench Press']))
      .then(() => user.selectsExercise('Dumbbell Bench Press'))
      .then(() => user.seesThatMuscleGroupHasExercisesToSelect('Chest', ['Bench Press']))
      .then(() => user.addsSetsTo('Dumbbell Bench Press', [
        new Set(25, 'kg', 10, 0, 's', undefined)
      ]))
      .then(() => user.seesCorrectCumulatedWeightsFor([
        {name: 'Dumbbell Bench Press', expectedValue: 25 * 10 * 2, isRoot: false},
        {name: 'Chest', expectedValue: 25 * 10 * 2, isRoot: false},
        {name: 'Chest', expectedValue: 25 * 10 * 2, isRoot: true},
      ]))
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
