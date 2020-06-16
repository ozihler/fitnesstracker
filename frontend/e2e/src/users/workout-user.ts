import {FitnessTypeSelection} from '../fitness-type-selection.po';
import {WorkoutsOverview} from '../workout/workouts-overview.po';
import {CreateWorkout} from '../workout/create-workout.po';
import {Set} from '../../../src/app/workout/shared/set';
import {CumulatedWeight} from '../utils/cumulated-weight';

export class WorkoutUser {

  constructor() {
    this.fitnessTypeSelection = new FitnessTypeSelection();
    this.workoutsOverview = new WorkoutsOverview();
    this.createWorkout = new CreateWorkout();
  }

  // todo maybe add an addition abstraction layer "application" or so
  private fitnessTypeSelection: FitnessTypeSelection;
  private workoutsOverview: WorkoutsOverview;
  private readonly createWorkout: CreateWorkout;

  createsNewWorkout() {
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
    return this.createWorkout.selectItem(muscleGroupName);
  }

  createsExercises(exerciseNames: string) {
    return this.createsMuscleGroups(exerciseNames);
  }

  addsExercisesToMuscleGroup(exercisesToAdd: string, muscleGroup: string) {
    return this.selectNodeInWorkoutTreeWithName(muscleGroup)
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
    return selectableMuscleGroups.forEach(m => this.createWorkout.deleteSelectableItem(m));
  }

  seesThatMuscleGroupHasExercisesToSelect(muscleGroup: string, exercises: string[]) {
    return this.selectNodeInWorkoutTreeWithName(muscleGroup)
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

  selectsExercise(exercise: string) {
    return this.createWorkout.selectItem(exercise);
  }

  addsSetsTo(exercise: string, sets: Set[]) {
    return this.selectNodeInWorkoutTreeWithName(exercise)
      .then(() => this.createWorkout.addSet(sets));
  }

  selectNodeInWorkoutTreeWithName(name: string) {
    return this.createWorkout.selectWorkoutTreeNodeWithName(name);
  }

  seesCumulatedWeights(expectedValues: CumulatedWeight[]) {
    return this.createWorkout.seesCorrectCumulatedWeightsFor(expectedValues);
  }

}

