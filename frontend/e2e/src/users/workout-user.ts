import {FitnessTypeSelection} from '../fitness-type-selection.po';
import {WorkoutsOverview} from '../workout/workouts-overview.po';
import {CreateWorkout} from '../workout/create-workout.po';

export class WorkoutUser {
  private fitnessTypeSelection: FitnessTypeSelection;
  private workoutsOverview: WorkoutsOverview;
  private createWorkout: CreateWorkout;

  constructor() {
    this.fitnessTypeSelection = new FitnessTypeSelection();
    this.workoutsOverview = new WorkoutsOverview();
    this.createWorkout = new CreateWorkout();
  }

  navigatesToNewWorkout() {
    return this.navigatesToWorkoutOverview()
      .then(() => this.workoutsOverview.clickNewWorkoutButton());
  }

  navigatesToWorkoutOverview() {
    return this.fitnessTypeSelection.enter()
      .then(() => this.fitnessTypeSelection.clickSelectWorkoutButton());
  }

  createsMuscleGroups(muscleGroupNames: string) {
    return this.createWorkout.clickCreateMuscleGroupsOrExercisesButton()
      .then(() => this.createWorkout.enterSelectableItemsAsText(muscleGroupNames))
      .then(() => this.createWorkout.clickOkButton());
  }

  selectsMuscleGroup(muscleGroupName: string) {
    return this.createWorkout.selectMuscleGroup(muscleGroupName);
  }

  createsExercises(exerciseNames: string) {
    return this.createsMuscleGroups(exerciseNames);
  }

  addsExercisesToMuscleGroup(exercisesToAdd: string, muscleGroup: string) {
    return this.createWorkout.selectWorkoutTreeNodeWithName(muscleGroup)
      .then(() => this.createsExercises(exercisesToAdd));
  }

  seesThatTitleOfWorkoutIs(expectedWorkoutTitle: string) {
    return this.createWorkout.getWorkoutTitle()
      .then(actualWorkoutTitle => expect(actualWorkoutTitle).toEqual(expectedWorkoutTitle));
  }

  seesSelectableMuscleGroups(selectableMuscleGroups: string[]) {
    return this.createWorkout.getSelectableItemsAsButtons(selectableMuscleGroups)
      .forEach(button => button.visibleTitleText()
        .then(buttonText => expect(selectableMuscleGroups).toContain(buttonText)));
  }

  seesEmptyElementsText() {
    return this.createWorkout.getEmptyElementsTextElement()
      .then(textElement => expect(textElement.isDisplayed).toBeTruthy());
  }

  cannotSeeEmptyElementsText() {
    return this.createWorkout.getEmptyElementsTextElement()
      .then(textElement => expect(textElement).not.toBeDefined());
  }

  deletesSelectableMuscleGroupsWithNames(selectableMuscleGroups: string[]) {
    return selectableMuscleGroups.forEach(m => this.createWorkout.deleteMuscleGroup(m));
  }

  seesThatMuscleGroupHasExercises(muscleGroup: string, exercises: string[]) {
    return this.createWorkout.selectWorkoutTreeNodeWithName(muscleGroup)
      .then(() => this.seesSelectableExercises(exercises));
  }

  deletesAllWorkouts() {
    return this.workoutsOverview.deleteAllWorkouts();
  }

  private seesSelectableExercises(exercises: string[]) {
    return this.createWorkout.getSelectableItemsAsButtons(exercises)
      .forEach(button => button.visibleTitleText()
        .then(buttonText => expect(exercises).toContain(buttonText)));
  }
}
