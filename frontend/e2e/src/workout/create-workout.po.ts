import {Button} from '../page_elements/button.pe';
import {InputField} from '../page_elements/input-field.pe';
import {Span} from '../page_elements/span.pe';
import {Set} from '../../../src/app/workout/shared/set';
import {CumulatedWeight} from '../utils/cumulated-weight';
import {ElementsToId} from '../../../src/app/workout/shared/elements-to-id';

export class CreateWorkout {

  private showFormToInputNewMuscleGroupOrExerciseButton = new Button('ft-show-form-to-input-new-muscle-group-or-exercise-button');
  private buttonToSubmitNewMuscleGroupOrExercise = new Button('ft-button-to-submit-new-muscle-group-or-exercise');
  private editWorkoutTitleButton = new Button('edit-workout-title-button');
  private createNewMuscleGroupOrExerciseInput = new InputField('ft-input-field-to-create-new-muscle-group-or-exercise');
  private emptyElementsTextSpan = new Span('empty-elements-text');

  private addSetToExerciseButton = new Button('ft-add-set-to-exercise');

  private changeWeightButton = new Button('change-weight-button');
  private changeRepetitionsButton = new Button('change-repetitions-button');

  private weightInputField = new InputField('weight-input');
  private repetitionsInputField = new InputField('repetitions-input');

  getWorkoutTitle() {
    return this.editWorkoutTitleButton.visibleTitleText();
  }

  getEmptyElementsTextElement() {
    return this.emptyElementsTextSpan.webElement();
  }

  clickCreateMuscleGroupsOrExercisesButton() {
    return this.showFormToInputNewMuscleGroupOrExerciseButton.click();
  }

  enterSelectableItemsAsText(muscleGroupNames: string) {
    return this.createNewMuscleGroupOrExerciseInput.enterText(muscleGroupNames);
  }

  clickOkButton() {
    return this.buttonToSubmitNewMuscleGroupOrExercise.click();
  }

  selectItem(item: string) {
    return new Button('ft-select-' + ElementsToId.replace(item) + '-button').click();
  }

  deleteSelectableItem(item: string) {
    return new Button('ft-delete-' + ElementsToId.replace(item) + '-button').click();
  }

  getSelectableItemsAsButtons(selectableItems: string[]): Button[] {
    return selectableItems.map(s => new Button('ft-select-' + ElementsToId.replace(s) + '-button'));
  }

  selectWorkoutTreeNodeWithName(nodeName: string) {
    return new Button('select-' + ElementsToId.replace(nodeName) + '-editable-node')
      .clickIfUnselected();
  }

  addSet(sets: Set[]) {
    for (const set of sets) {
      this.changeWeightButton.click()
        .then(() => this.weightInputField.enterText(set.weight + ''))
        .then(() => this.changeRepetitionsButton.click())
        .then(() => this.repetitionsInputField.enterText(set.numberOfRepetitions + ''))
        .then(() => this.addSetToExerciseButton.click())
      ;
    }
  }

  seesCorrectCumulatedWeightsFor(expectedValues: CumulatedWeight[]) {
    let promise;
    for (const expectedValue of expectedValues) {
      const button = new Button('select-' + ElementsToId.replace(expectedValue.name) + '-editable-node');
      expect(button.visibleTitleText()).toContain('(' + expectedValue.expectedValue + ' kg)');
      promise = button.visibleTitleText(); // only here to return a promise
    }
    return promise;
  }
}
