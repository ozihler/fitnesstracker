import {CreateWorkoutComponent} from '../../app/workout/create-edit-workout/create-workout.component';
import {ComponentFixture} from '@angular/core/testing';
import {Button} from '../unit_test_page_elements/button.utpe';
import {Span} from '../unit_test_page_elements/span.utbp';
import {InputField} from '../unit_test_page_elements/input-field.utpe';
import {MuscleGroup} from '../../app/workout/shared/muscle-group';
import {ElementsToId} from './elements-to-id';
import {FindElement} from './find-element';

export class CreateWorkoutComponentPageObject {
  private findElement: FindElement;
  private component: CreateWorkoutComponent;

  //  page objects
  private editWorkoutTitleButton;
  private showFormToInputNewMuscleGroupOrExerciseButton;

  constructor(private fixture: ComponentFixture<CreateWorkoutComponent>) {
    this.component = fixture.componentInstance;
    this.findElement = new FindElement(fixture);

    this.editWorkoutTitleButton = new Button(this.findElement.by('#edit-workout-title-button'));
    this.showFormToInputNewMuscleGroupOrExerciseButton
      = new Button(this.findElement.by('#ft-show-form-to-input-new-muscle-group-or-exercise-button'));
  }

  expectTitleToContain(elementsTitleShouldContain: string[]) {
    elementsTitleShouldContain.forEach(element => expect(this.editWorkoutTitleButton.label).toContain(element));
  }

  expectEmptyElementsText(elementType: string) {
    expect(new Span(this.findElement.by('#empty-elements-text')).text)
      .toContain('No ' + elementType + ' to select. Create a new one first!');
  }

  showMuscleGroupInputForm() {
    this.showFormToInputNewMuscleGroupOrExerciseButton.click();
  }

  enterMuscleGroups(muscleGroupNames: string) {
    new InputField(this.findElement.by('#ft-input-field-to-create-new-muscle-group-or-exercise')).text = muscleGroupNames;
  }

  submitMuscleGroups() {
    new Button(this.findElement.by('#ft-button-to-submit-new-muscle-group-or-exercise')).click();
  }

  expectSelectableMuscleGroupsToContain(muscleGroupsArray: MuscleGroup[]) {
    muscleGroupsArray.forEach(muscleGroup => {
      expect(new Button(this.findElement.by('#ft-select-' + ElementsToId.replace(muscleGroup.name) + '-button')).label)
        .toEqual(muscleGroup.name);
    });
  }

  chooseSelectableElement(selectableElement: string) {
    const selectableElementIdName = ElementsToId.replace(selectableElement);
    const selectElementButton = new Button(this.findElement.by('#ft-select-' + selectableElementIdName + '-button'));
    selectElementButton.click();
  }

  removeSelectedElementFromWorkoutTree(selectedElementToRemove: string) {
    const selectedElementIdName = ElementsToId.replace(selectedElementToRemove);
    const selectElementButton = new Button(this.findElement.by('#remove-node-' + selectedElementIdName + '-from-workout-tree'));
    selectElementButton.click();
  }

  expectWorkoutTreeToContain(workoutTreeElementName: string, stringsItShouldContain: string[]) {
    const treeNodeButton = new Button(this.findElement.by('#select-' + ElementsToId.replace(workoutTreeElementName) + '-editable-node'));
    for (const stringTheElementShouldContain of stringsItShouldContain) {
      expect(treeNodeButton.label).toContain(stringTheElementShouldContain);
    }
  }

  deleteSelectableItem(selectableElementToDelete: string) {
    const selector = '#ft-delete-' + ElementsToId.replace(selectableElementToDelete) + '-button';
    new Button(this.findElement.by(selector)).click();
  }

  selectWorkoutTreeNodeWithName(workoutTreeNodeName: string) {
    const selector = '#select-' + ElementsToId.replace(workoutTreeNodeName) + '-editable-node';
    new Button(this.findElement.by(selector)).click();
  }
}
