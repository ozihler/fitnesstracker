import {Button} from '../page_elements/button.pe';
import {InputField} from '../page_elements/input-field.pe';
import {Span} from '../page_elements/span.pe';
import {ReplacePipe} from '../../../src/app/workout/shared/pipes/replace.pipe';
import {Set} from '../../../src/app/workout/shared/set';

export class CreateWorkout {

  private showFormToInputNewMuscleGroupOrExerciseButton = new Button('ft-show-form-to-input-new-muscle-group-or-exercise-button');
  private buttonToSubmitNewMuscleGroupOrExercise = new Button('ft-button-to-submit-new-muscle-group-or-exercise');
  private editWorkoutTitleButton = new Button('edit-workout-title-button');
  private createNewMuscleGroupOrExerciseInput = new InputField('ft-input-field-to-create-new-muscle-group-or-exercise');
  private emptyElementsTextSpan = new Span('empty-elements-text');

  private static format(value: string) {
    return new ReplacePipe().transform(value, ' ', '-').toLowerCase();
  }

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
    return new Button('ft-select-' + CreateWorkout.format(item) + '-button').click();
  }

  deleteSelectableItem(item: string) {
    return new Button('ft-delete-' + CreateWorkout.format(item) + '-button').click();
  }

  getSelectableItemsAsButtons(selectableItems: string[]): Button[] {
    return selectableItems.map(s => new Button('ft-select-' + CreateWorkout.format(s) + '-button'));
  }

  selectWorkoutTreeNodeWithName(nodeName: string) {
    return new Button('select-' + CreateWorkout.format(nodeName) + '-editable-node')
      .clickIfUnselected();
  }

  addSet(set: Set[]) {

  }
}
