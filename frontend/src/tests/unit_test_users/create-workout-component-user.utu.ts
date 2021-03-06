import {CreateWorkoutComponentPageObject, SetButtonValues} from '../unit_test_page_objects/create-workout-component.utpo';
import {MuscleGroup} from '../../app/workout/shared/muscle-group';
import {Exercise} from '../../app/workout/shared/exercise';
import {Set} from '../../app/workout/shared/set';
import {TreeNode} from '../../app/workout/create-edit-workout/workout-tree/tree-node';

export class SetValues {
  weight: number;
  waitingTime: number;
  repetitions: number;
}


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

  seesWorkoutContainsElementWith(workoutTreeElementName: TreeNode, stringsItShouldContain: string[]) {
    this.createWorkoutComponent.expectWorkoutTreeToContain(workoutTreeElementName, stringsItShouldContain);
  }

  removesItemFromWorkoutTree(elementToRemove: TreeNode) {
    this.createWorkoutComponent.removeSelectedElementFromWorkoutTree(elementToRemove);
  }

  deletesSelectableItem(selectableElementToDelete: string) {
    this.createWorkoutComponent.deleteSelectableItem(selectableElementToDelete);
  }

  // todo replace all name strings with TreeNode::id
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

  seesCurrentSetValuesToAddAre(set: Set, multiplier: number) {
    this.createWorkoutComponent.expectCurrentSetInputMarkToContainValuesOf(set, multiplier);
  }

  configuresRepetitionsWithValues(repetitions: SetButtonValues[]) {
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

  configuresSetWithValues(weights: SetButtonValues[], repetitions: SetButtonValues[], waitingTimes: SetButtonValues[], multiplier: number) {
    this.configuresWeightWithValues(weights);
    this.seesWeightIs(this.sumOf(weights));
    this.seesCurrentSetValuesToAddAre(new Set(this.sumOf(weights), 'kg', undefined, undefined, undefined, undefined), multiplier);

    this.configuresRepetitionsWithValues(repetitions);
    this.seesRepetitionsAre(this.sumOf(repetitions));
    this.seesCurrentSetValuesToAddAre(new Set(this.sumOf(weights), 'kg', this.sumOf(repetitions), undefined, undefined, undefined), multiplier);

    this.configuresWaitingTime(waitingTimes);
    this.seesWaitingTimeIs(this.sumOf(waitingTimes));
    this.seesCurrentSetValuesToAddAre(
      new Set(this.sumOf(weights), 'kg', this.sumOf(repetitions), this.sumOf(waitingTimes), 's', undefined), multiplier);

    this.togglesSetParts();
    this.seesAllSetPartsAreHidden();
  }


  cannotSeeSetOfExerciseWithValues(set: Set) {
    this.createWorkoutComponent.expectWorkoutTreeNotToContain(set.id);
  }

  seesSetOfExercise(exerciseName: string, set: Set) {
    this.createWorkoutComponent.expectWorkoutTreeToContain(set,
      [set.weight.toString(), set.numberOfRepetitions.toString(), set.waitingTime.toString()]);
  }

  seesThatSetWasAddedToExercise(exerciseName: string, set: Set) {
    this.createWorkoutComponent.expectWorkoutTreeToContain(set,
      [set.weight.toString(), set.numberOfRepetitions.toString(), set.waitingTime.toString()]);
  }

  configuresSetByDirectInputtingValues(weight: number, repetitions: number, waitingTime: number) {
    this.createWorkoutComponent.inputSetValue('weight', weight);
    this.createWorkoutComponent.inputSetValue('repetitions', repetitions);
    this.createWorkoutComponent.inputSetValue('waitingTime', waitingTime);
  }

  sumOf(weights: SetButtonValues[]) {
    let sum = 0;
    for (const weight of weights) {
      sum += parseFloat(weight.value) * weight.times;
    }
    return sum;
  }

  opensWorkoutTitleAndDateEditing() {
    this.createWorkoutComponent.clickButtonWithId('edit-workout-title-button');
  }

  seesWorkoutDoesNotContain(treeElementName: string) {
    this.createWorkoutComponent.expectWorkoutTreeNotToContain(treeElementName);
  }

  updatesTitleTo(newTitle: string) {
    this.createWorkoutComponent.inputTitle(newTitle);
  }

  submitsDateUpdate() {
    this.createWorkoutComponent.clickButtonWithId('submit-header-update-button');
  }

  cancelsDateUpdate() {
    this.createWorkoutComponent.clickButtonWithId('cancel-header-update-button');
  }

  seesWorkoutTitleDoesNotContains(stringsTitleShouldNOTContain: string[]) {
    this.createWorkoutComponent.expectTitleNotToContain(stringsTitleShouldNOTContain);
  }

  setsWorkoutDateTo(newDate: Date) {
    this.createWorkoutComponent.setWorkoutDateTo(newDate);
  }

  seesWorkoutDateIs(newDate: Date) {
    this.createWorkoutComponent.expectWorkoutDateToBe(newDate);
  }

  seesThatExerciseHasSets(exerciseName: string, sets: TreeNode[]) {
    this.createWorkoutComponent.expectExerciseToHaveSets(sets);
  }

  seesSelectedItemInWorkoutTreeIs(workoutTreeNode: TreeNode) {
    this.createWorkoutComponent.expectSelectedWorkoutTreeItemToBe(workoutTreeNode);
  }

  seesSelectedItemInWorkoutTreeIsNot(workoutTreeNode: TreeNode) {
    this.createWorkoutComponent.expectSelectedWorkoutTreeItemNotToBe(workoutTreeNode);
  }
}
