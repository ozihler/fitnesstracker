import {by, element} from 'protractor';
import {Button} from '../page_elements/button.pe';

export class CreateWorkout {

  private showFormToInputNewMuscleGroupOrExerciseButton: Button;
  private buttonToSubmitNewMuscleGroupOrExercise: Button;

  constructor() {
    this.showFormToInputNewMuscleGroupOrExerciseButton = new Button('ft-show-form-to-input-new-muscle-group-or-exercise-button');
    this.buttonToSubmitNewMuscleGroupOrExercise = new Button('ft-button-to-submit-new-muscle-group-or-exercise');
  }

  getWorkoutTitle() {
    return element(by.id('edit-workout-title-button')).getText();
  }

  getEmptyElementsTextElement() {
    return element(by.id('empty-elements-text')).getWebElement();
  }

  clickCreateMuscleGroupButton() {
    return this.showFormToInputNewMuscleGroupOrExerciseButton.click();
  }

  enterMuscleGroupsAsText(muscleGroupNames: string) {
    return element(by.id('ft-input-field-to-create-new-muscle-group-or-exercise')).sendKeys(muscleGroupNames);
  }

  clickOkButton() {
    return this.buttonToSubmitNewMuscleGroupOrExercise.click();
  }

  deleteMuscleGroup(muscleGroupName: string) {
    return new Button('ft-delete-' + muscleGroupName.toLowerCase() + '-button').click();
  }
}
