import {CreateWorkoutComponentPageObject} from '../unit_test_page_objects/create-workout-component.utpo';
import {MuscleGroup} from '../../app/workout/shared/muscle-group';

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

  seesSelectableMuscleGroups(muscleGroupsArray: MuscleGroup[]) {
    this.createWorkoutComponent.expectSelectableMuscleGroupsToContain(muscleGroupsArray);
  }

  choosesFromSelectableMuscleGroups(selectableElement: string) {
    this.createWorkoutComponent.chooseSelectableElement(selectableElement);
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

  selectsMuscleGroupFromWorkout(workoutTreeNodeName: string) {
    this.createWorkoutComponent.selectWorkoutTreeNodeWithName(workoutTreeNodeName);
  }

  seesEmptyMuscleGroupsText() {
    this.createWorkoutComponent.expectEmptyElementsText('Muscle Group');
  }

  seesEmptyExercisesText() {
    this.createWorkoutComponent.expectEmptyElementsText('Exercise');
  }
}
