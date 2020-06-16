import {browser, logging} from 'protractor';
import {WorkoutUser} from './users/workout-user';
import {Set} from '../../src/app/workout/shared/set';
import {Exercise} from '../../src/app/workout/shared/exercise';


describe('A workout user', () => {
  let user: WorkoutUser;

  beforeEach(() => {
    // RUN ON UBUNTU:
    // Run sudo webdriver-manager start
    // Run protractor frontend/protractor.conf.js
    user = new WorkoutUser();
  });

  const selectableMuscleGroups = ['Chest', 'Triceps', 'Shoulders', 'Biceps'];

  it('can create a workout with multiple muscle groups and exercises', () => {
    // user creates new workout
    user.createsNewWorkout()
      .then(() => user.seesThatTitleOfWorkoutIs('New Workout'))
      .then(() => user.seesEmptyElementsText());

    // user adds muscle groups to workout
    user.createsMuscleGroups('cHesT; tRICEPS,ShOulders. BiCePs')
      .then(() => user.seesSelectableMuscleGroups(selectableMuscleGroups))
      .then(() => user.selectsMuscleGroup('Chest'))
      .then(() => user.seesThatTitleOfWorkoutIs('Chest'))
      .then(() => user.selectsMuscleGroup('Triceps'))
      .then(() => user.seesThatTitleOfWorkoutIs('Chest ' + 'Triceps'));

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

    const exercise = new Exercise(undefined, 'Dumbbell Bench Press', [], 2);
    const setToAdd = new Set(25, 'kg', 10, 0, 's', exercise, 0);

    user.selectNodeInWorkoutTreeWithName('Chest')
      .then(() => user.addsSetsTo('Dumbbell Bench Press', [setToAdd]))
      .then(() => user.seesCumulatedWeights([
        // todo fix this value (2) multiplier
        {name: setToAdd.id, expectedValue: cumulatedWeightOf([setToAdd], exercise.multiplier), isRoot: false},
        {name: 'Dumbbell Bench Press', expectedValue: cumulatedWeightOf([setToAdd], exercise.multiplier), isRoot: false},
        {name: 'Chest', expectedValue: cumulatedWeightOf([setToAdd], exercise.multiplier), isRoot: false},
        {name: 'root', expectedValue: cumulatedWeightOf([setToAdd], exercise.multiplier), isRoot: true},
      ]));

    const setToAdd2 = new Set(27.5, 'kg', 8, 0, 's', exercise, 1);
    user.addsSetsTo('Dumbbell Bench Press', [setToAdd2])
      .then(() => user.seesCumulatedWeights([
        // todo fix this value (2) multiplier
        {name: setToAdd2.id, expectedValue: cumulatedWeightOf([setToAdd2], exercise.multiplier), isRoot: false},
        {name: 'Dumbbell Bench Press', expectedValue: cumulatedWeightOf([setToAdd, setToAdd2], exercise.multiplier), isRoot: false},
        {name: 'Chest', expectedValue: cumulatedWeightOf([setToAdd, setToAdd2], exercise.multiplier), isRoot: false},
        {name: 'root', expectedValue: cumulatedWeightOf([setToAdd, setToAdd2], exercise.multiplier), isRoot: true},
      ]));

    const setToAdd3 = new Set(30, 'kg', 6, 0, 's', exercise, 2);
    user.addsSetsTo('Dumbbell Bench Press', [setToAdd3])
      .then(() => user.seesCumulatedWeights([
        // todo fix this value (2) multiplier
        {name: setToAdd3.id, expectedValue: cumulatedWeightOf([setToAdd3], exercise.multiplier), isRoot: false},
        {
          name: 'Dumbbell Bench Press',
          expectedValue: cumulatedWeightOf([setToAdd, setToAdd2, setToAdd3], exercise.multiplier),
          isRoot: false
        },
        {name: 'Chest', expectedValue: cumulatedWeightOf([setToAdd, setToAdd2, setToAdd3], exercise.multiplier), isRoot: false},
        {name: 'root', expectedValue: cumulatedWeightOf([setToAdd, setToAdd2, setToAdd3], exercise.multiplier), isRoot: true}
      ]));
  });

  function cumulatedWeightOf(sets: Set[], multiplier: number) {
    return multiplier * sets.map(set => set.numberOfRepetitions * set.weight)
      .reduce((p, v) => (p + v));
  }


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
