import {CreateWorkoutComponentPageObject, SetButtonValues} from '../unit_test_page_objects/create-workout-component.utpo';
import {MuscleGroup} from '../../app/workout/shared/muscle-group';
import {Exercise} from '../../app/workout/shared/exercise';
import {Set} from '../../app/workout/shared/set';

export class CreateWorkoutComponentUser {
  constructor(private createWorkoutComponent: CreateWorkoutComponentPageObject) {

  }

  seesWorkoutTitleContains(elementsTitleShouldContain: string[]) {
    this.createWorkoutComponent.expectTitleToContain(elementsTitleShouldContain);
  }

  createsMuscleGroupsToSelect(muscleGroupNames: string) {
    this.createWorkoutComponent.showMuscleGroupInputForm();
    this.createWorkoutComponent.enterMuscleGroups(muscleGroupNames);
    this.createWorkoutComponent.submitMuscleGroups();
  }

  createsExercisesToSelect(exerciseNames: string) {
    this.createWorkoutComponent.showExercisesInputForm();
    this.createWorkoutComponent.enterExercises(exerciseNames);
    this.createWorkoutComponent.submitExercises();
  }

  seesSelectableMuscleGroups(muscleGroupsArray: MuscleGroup[]) {
    this.createWorkoutComponent.expectSelectableMuscleGroupsToContain(muscleGroupsArray);
  }

  seesSelectableExercises(selectableExercises: Exercise[]) {
    this.createWorkoutComponent.expectSelectableExercisesToContain(selectableExercises);
  }

  choosesFromSelectableMuscleGroups(selectableMuscleGroup: string) {
    this.createWorkoutComponent.chooseSelectableElement(selectableMuscleGroup);
  }

  choosesFromSelectableExercises(selectableExercise: string) {
    this.createWorkoutComponent.chooseSelectableElement(selectableExercise);
  }

  seesWorkoutContainsElementWith(workoutTreeElementName: string, stringsItShouldContain: string[]) {
    this.createWorkoutComponent.expectWorkoutTreeToContain(workoutTreeElementName, stringsItShouldContain);
  }

  removesItemFromWorkoutTree(elementToRemove: string) {
    this.createWorkoutComponent.removeSelectedElementFromWorkoutTree(elementToRemove);
  }

  deletesSelectableItem(selectableElementToDelete: string) {
    this.createWorkoutComponent.deleteSelectableItem(selectableElementToDelete);
  }

  selectsMuscleGroupInWorkout(workoutTreeNodeName: string) {
    this.createWorkoutComponent.selectWorkoutTreeNodeWithName(workoutTreeNodeName);
  }

  selectsExerciseInMuscleGroup(exerciseInMuscleGroup: string) {
    this.createWorkoutComponent.selectWorkoutTreeNodeWithName(exerciseInMuscleGroup);
  }

  seesEmptyMuscleGroupsText() {
    this.createWorkoutComponent.expectEmptyElementsText('Muscle Group');
  }

  seesEmptyExercisesText() {
    this.createWorkoutComponent.expectEmptyElementsText('Exercise');
  }

  configuresWeightWithValues(weightButtonValues: SetButtonValues[]) {
    this.createWorkoutComponent.setSetValues('weight', weightButtonValues);
  }

  seesWeightIs(weight: number) {
    this.createWorkoutComponent.expectSetValueToBe('weight', weight);
  }

  seesCurrentSetValuesToAddAre(set: Set) {
    this.createWorkoutComponent.expectCurrentSetInputMarkToContainValuesOf(set);
  }

  configureRepetitionsWithValues(repetitions: SetButtonValues[]) {
    this.createWorkoutComponent.setSetValues('repetitions', repetitions);
  }

  configuresWaitingTime(waitingTimes: SetButtonValues[]) {
    this.createWorkoutComponent.setSetValues('waitingTime', waitingTimes);
  }

  seesRepetitionsAre(repetitions: number) {
    this.createWorkoutComponent.expectSetValueToBe('repetitions', repetitions);
  }

  seesWaitingTimeIs(waitingTime: number) {
    this.createWorkoutComponent.expectSetValueToBe('waitingTime', waitingTime);
  }

  togglesSetParts() {
    this.createWorkoutComponent.toggleSetParts();
  }

  seesAllSetPartsAreHidden() {
    this.createWorkoutComponent.expectChangeButtonToBeShown('weight');
    this.createWorkoutComponent.expectChangeButtonToBeShown('repetitions');
    this.createWorkoutComponent.expectChangeButtonToBeShown('waitingTime');
  }

  addsSetToExercise() {
    this.createWorkoutComponent.clickButtonWithId('ft-add-set-to-exercise');
  }
}
