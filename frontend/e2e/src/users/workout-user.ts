import {FitnessTypeSelection} from '../fitness-type-selection.po';
import {WorkoutsOverview} from '../workout/workouts-overview.po';
import {CreateWorkout} from '../workout/create-workout.po';
import {Button} from '../page_elements/button.pe';

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
    return this.fitnessTypeSelection.enter()
      .then(() => this.fitnessTypeSelection.clickSelectWorkoutButton())
      .then(() => this.workoutsOverview.clickNewWorkoutButton());
  }

  createsMuscleGroups(muscleGroupNames: string) {
    return this.createWorkout.clickCreateMuscleGroupButton()
      .then(() => this.createWorkout.enterMuscleGroupsAsText(muscleGroupNames))
      .then(() => this.createWorkout.clickOkButton());
  }

  selectsMuscleGroup(muscleGroupName: string) {
    new Button('ft-select-' + muscleGroupName.toLowerCase() + '-button').click();
  }

  addExercises(exerciseNames: string[]) {

  }

  seesThatTitleOfWorkoutIs(expectedWorkoutTitle: string) {
    return this.createWorkout.getWorkoutTitle()
      .then(actualWorkoutTitle => expect(actualWorkoutTitle).toEqual(expectedWorkoutTitle));
  }

  seesSelectableMuscleGroups(selectableMuscleGroups: string[]) {

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
}
