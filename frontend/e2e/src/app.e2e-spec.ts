import {browser, logging} from 'protractor';
import {WorkoutUser} from './users/workout-user';


describe('A user', () => {
  let user: WorkoutUser;

  beforeEach(() => {
    user = new WorkoutUser();
  });

  const selectableMuscleGroups = ['Chest', 'Triceps', 'Shoulders', 'Biceps'];
  it('can create a workout with multiple muscle groups and exercises', () => {
    user.navigatesToNewWorkout()
      .then(() => user.seesThatTitleOfWorkoutIs('New Workout'))
      .then(() => user.seesEmptyElementsText())
      .then(() => user.createsMuscleGroups('cHesT; tRICEPS,ShOulders. BiCePs'))
      .then(() => user.seesSelectableMuscleGroups(selectableMuscleGroups))
      .then(() => user.selectsMuscleGroup('Chest'))
      .then(() => user.seesThatTitleOfWorkoutIs('Chest'))
      .then(() => user.addExercises(['Bench Press', '2#Dumbbell Bench Press']));
  });


  afterEach(async () => {
    user.navigatesToNewWorkout()
      .then(() => user.deletesSelectableMuscleGroupsWithNames(selectableMuscleGroups));
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
