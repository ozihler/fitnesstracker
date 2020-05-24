import {CreateWorkoutComponentPageObject} from '../unit_test_page_objects/create-workout-component.utpo';
import {MuscleGroup} from '../../app/workout/shared/muscle-group';

export class CreateWorkoutComponentUser {
  constructor(private createWorkoutComponent: CreateWorkoutComponentPageObject) {

  }

  seesWorkoutTitleIs(workoutTitle: string) {
    this.createWorkoutComponent.expectTitleToBe(workoutTitle);
  }

  seesEmptyElementsText() {
    this.createWorkoutComponent.expectEmptyElementsText();
  }

  createsMuscleGroupsToSelect(muscleGroupNames: string) {
    this.createWorkoutComponent.showMuscleGroupInputForm();
    this.createWorkoutComponent.enterMuscleGroups(muscleGroupNames);
    this.createWorkoutComponent.submitMuscleGroups();
  }

  seesSelectableMuscleGroups(muscleGroupsArray: MuscleGroup[]) {
    this.createWorkoutComponent.expectSelectableMuscleGroupsToContain(muscleGroupsArray);
  }

  selects(selectableElement: string) {
    this.createWorkoutComponent.selectSelectableElement(selectableElement);
  }

  seesWorkoutContainsElementWith(workoutTreeElementName: string, stringsItShouldContain: string[]) {
    this.createWorkoutComponent.expectWorkoutTreeToContain(workoutTreeElementName, stringsItShouldContain);
  }
}
