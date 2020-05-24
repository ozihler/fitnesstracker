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
  private emptyElementsTextSpan;
  private showFormToInputNewMuscleGroupOrExerciseButton;

  constructor(private fixture: ComponentFixture<CreateWorkoutComponent>) {
    this.component = fixture.componentInstance;
    this.findElement = new FindElement(fixture);

    this.editWorkoutTitleButton = new Button(this.findElement.by('#edit-workout-title-button'));
    this.emptyElementsTextSpan = new Span(this.findElement.by('#empty-elements-text'));
    this.showFormToInputNewMuscleGroupOrExerciseButton
      = new Button(this.findElement.by('#ft-show-form-to-input-new-muscle-group-or-exercise-button'));
  }

  expectTitleToBe(workoutTitle: string) {
    expect(this.editWorkoutTitleButton.label)
      .toContain(workoutTitle);
  }

  expectEmptyElementsText() {
    expect(this.emptyElementsTextSpan.text)
      .toContain('Create a new one first!');
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

  selectSelectableElement(selectableElement: string) {
    const selectableElementIdName = ElementsToId.replace(selectableElement);
    const selectElementButton = new Button(this.findElement.by('#ft-select-' + selectableElementIdName + '-button'));
    selectElementButton.click();
  }

  expectWorkoutTreeToContain(workoutTreeElementName: string, stringsItShouldContain: string[]) {
    const treeNodeButton = new Button(this.findElement.by('#select-' + ElementsToId.replace(workoutTreeElementName) + '-editable-node'));
    for (const stringTheElementShouldContain of stringsItShouldContain) {
      expect(treeNodeButton.label).toContain(stringTheElementShouldContain);
    }
  }
}
