import {CreateWorkoutComponent} from '../../app/workout/create-edit-workout/create-workout.component';
import {ComponentFixture} from '@angular/core/testing';
import {Button} from '../unit_test_page_elements/button.utpe';
import {Span} from '../unit_test_page_elements/span.utbp';
import {InputField} from '../unit_test_page_elements/input-field.utpe';
import {MuscleGroup} from '../../app/workout/shared/muscle-group';
import {ElementsToId} from '../../app/workout/shared/elements-to-id';
import {FindElement} from './find-element';
import {Exercise} from '../../app/workout/shared/exercise';
import {Set} from '../../app/workout/shared/set';
import {Mark} from '../unit_test_page_elements/mark.utbp';
import {TreeNode} from '../../app/workout/create-edit-workout/workout-tree/tree-node';
import {SetFormatPipe} from '../../app/workout/shared/pipes/set-format.pipe';


export class CreateWorkoutComponentPageObject {

  constructor(private fixture: ComponentFixture<CreateWorkoutComponent>) {
    this.component = fixture.componentInstance;
    this.findElement = new FindElement(fixture);
  }

  private findElement: FindElement;
  private component: CreateWorkoutComponent;

  private static addOrSubtract(weightButtonValue: SetButtonValues) {
    return parseFloat(weightButtonValue.value) > 0 ? '-add-' : '-subtract-';
  }

  expectTitleToContain(elementsTitleShouldContain: string[]) {
    elementsTitleShouldContain.forEach(element =>
      expect(new Button(this.findElement.by('#edit-workout-title-button')).label)
        .toContain(element));
  }

  expectTitleNotToContain(elementsTitleShouldNOTContain: string[]) {
    const workoutTitleInputBox = new InputField(this.findElement.byId('workout-title-input-box'));
    elementsTitleShouldNOTContain.forEach(element =>
      expect(workoutTitleInputBox.input).not.toContain(element));
  }


  expectEmptyElementsText(elementType: string) {
    expect(new Span(this.findElement.by('#empty-elements-text')).text)
      .toContain('No ' + elementType + ' to select. Create a new one first!');
  }

  showMuscleGroupInputForm() {
    new Button(this.findElement.by('#ft-show-form-to-input-new-muscle-group-or-exercise-button')).click();
  }

  showExercisesInputForm() {
    new Button(this.findElement.by('#ft-show-form-to-input-new-muscle-group-or-exercise-button')).click();
  }

  enterMuscleGroups(muscleGroupNames: string) {
    new InputField(this.findElement.by('#ft-input-field-to-create-new-muscle-group-or-exercise')).input = muscleGroupNames;
  }

  enterExercises(exerciseNames: string) {
    new InputField(this.findElement.by('#ft-input-field-to-create-new-muscle-group-or-exercise')).input = exerciseNames;
  }


  submitMuscleGroups() {
    new Button(this.findElement.by('#ft-button-to-submit-new-muscle-group-or-exercise')).click();
  }

  submitExercises() {
    new Button(this.findElement.by('#ft-button-to-submit-new-muscle-group-or-exercise')).click();
  }

  expectSelectableMuscleGroupsToContain(muscleGroupsArray: MuscleGroup[]) {
    muscleGroupsArray.forEach(muscleGroup => {
      expect(new Button(this.findElement.by('#ft-select-' + muscleGroup.id + '-button')).label)
        .toEqual(muscleGroup.name);
    });
  }

  expectSelectableExercisesToContain(selectableExercises: Exercise[]) {
    selectableExercises.forEach(exercise => {
      expect(new Button(
        this.findElement.by('#ft-select-' + exercise.id + '-button')).label)
        .toEqual(exercise.name);
    });
  }

  chooseSelectableElement(selectableElement: string) {
    const selectableElementIdName = ElementsToId.replace(selectableElement);
    const selectElementButton = new Button(this.findElement.by('#ft-select-' + selectableElementIdName + '-button'));
    selectElementButton.click();
  }


  removeSelectedElementFromWorkoutTree(selectedElementToRemove: TreeNode) {
    const button = this.findElement.byId('remove-node-' + selectedElementToRemove.id + '-from-workout-tree');
    const selectElementButton = new Button(button);
    selectElementButton.click();
  }

  expectWorkoutTreeToContain(workoutTreeElement: TreeNode, stringsItShouldContain: string[]) {
    const selector = 'select-' + workoutTreeElement.id + '-editable-node';
    const treeNodeButton = new Button(this.findElement.byId(selector));
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

  setSetValues(setButtonType: string, setButtonValues: SetButtonValues[]) {
    new Button(this.findElement.by('#change-' + setButtonType + '-button')).click();

    setButtonValues.forEach(weightButtonValue => {
      const selector = '#' + setButtonType + CreateWorkoutComponentPageObject.addOrSubtract(weightButtonValue)
        + weightButtonValue.value.toString().replace('-', '').replace('.', '-')
        + '-button';
      const setButton = new Button(this.findElement.by(selector));
      for (let i = 0; i < weightButtonValue.times; ++i) {
        setButton.click();
      }
    });

  }

  expectSetValueToBe(setValueType: string, cumulatedWeight: number) {
    const setValueInputField = new InputField(this.findElement.by('#' + setValueType + '-input'));
    const inputWeight = parseFloat(setValueInputField.input);
    expect(inputWeight).toBe(cumulatedWeight);
  }

  expectCurrentSetInputMarkToContainValuesOf(set: Set, multiplier: number) {
    const setInputMarkText = new Mark(this.findElement.by('#current-set-input-mark')).text;
    const expected = new SetFormatPipe().transform(set);
    expect(setInputMarkText).toContain(expected);
  }

  toggleSetParts() {
    new Button(this.findElement.byId('toggle-set-parts-button')).click();
  }

  expectChangeButtonToBeShown(setPartType: string) {
    expect(new Button(this.findElement.byId('change-' + setPartType + '-button')).hidden)
      .toBe(false);
  }

  clickButtonWithId(buttonId: string) {
    new Button(this.findElement.byId(buttonId)).click();
  }

  inputSetValue(id: string, value: number) {
    new Button(this.findElement.byId(`change-${id}-button`)).click();
    new InputField(this.findElement.byId(`${id}-input`)).change = value ? value.toString() : '';
  }

  expectWorkoutTreeNotToContain(id: string) {
    expect(this.findElement.byId(`select-${id}-editable-node`)).toBeNull();
  }

  inputTitle(newTitle: string) {
    new InputField(this.findElement.byId('workout-title-input-box')).input = newTitle;
  }

  setWorkoutDateTo(newDate: Date) {
    new InputField(this.findElement.byId('workout-date-input-box')).input
      = newDate.toJSON().split('T')[0];
  }

  expectWorkoutDateToBe(date: Date) {
    const dateString = new InputField(this.findElement.byId('workout-date-input-box')).input;
    expect(new Date(dateString).toDateString()).toEqual(date.toDateString());
  }

  expectExerciseToHaveSets(sets: TreeNode[]) {
    for (const set of sets) {
      const selector = `select-${set.id}-editable-node`;
      expect(new Button(this.findElement.byId(selector)).label).toContain(set.name);
    }
  }

  expectSelectedWorkoutTreeItemToBe(workoutTreeNode: TreeNode) {
    const selector = 'select-' + workoutTreeNode.id + '-editable-node';
    const treeNodeButton = new Button(this.findElement.byId(selector));
    expect(treeNodeButton.cssClasses()).toContain('uk-button-danger');
  }

  expectSelectedWorkoutTreeItemNotToBe(workoutTreeNode: TreeNode) {
    const selector = 'select-' + workoutTreeNode.id + '-editable-node';
    const treeNodeButton = new Button(this.findElement.byId(selector));
    expect(treeNodeButton.cssClasses()).not.toContain('uk-button-danger');
  }
}

export class SetButtonValues {
  value: string;
  times: number;

  constructor(value: string, times: number) {
    this.value = value;
    this.times = times;
  }
}
