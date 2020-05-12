import {async, TestBed} from "@angular/core/testing";
import {SelectableMuscleGroupOrExerciseComponent} from "../../../../../app/workout/create-edit-workout/administration/muscle-groups-and-exercises/selectable-muscle-group-or-exercise.component";
import {NO_ERRORS_SCHEMA} from "@angular/core";
import {TreeNode} from "../../../../../app/workout/create-edit-workout/workout-tree/tree-node";
import {MuscleGroup} from "../../../../../app/workout/shared/muscle-group";


describe('SelectableMuscleGroupOrExerciseComponent', () => {
  let fixture;
  let component;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        SelectableMuscleGroupOrExerciseComponent
      ],
      schemas: [
        NO_ERRORS_SCHEMA
      ]
    }).compileComponents();
    fixture = TestBed.createComponent(SelectableMuscleGroupOrExerciseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('sends a select event for the element currently present in the component', () => {
    let receivedElement: TreeNode;

    component.element = new MuscleGroup(
      undefined,
      "Chest",
      []);

    component.selectElementEvent
      .subscribe((elementToSelect) => receivedElement = elementToSelect);

    let debugElement = fixture.nativeElement
      .querySelector('#ft-select-muscle-group-or-exercise-button');

    debugElement.click();

    expect(receivedElement.name).toBe('Chest');
  });

  it('sents a delete event for the element currently present in the component', () => {
    let receivedElement: TreeNode;

    component.element = new MuscleGroup(
      undefined,
      "Chest",
      []);

    component.deleteElementEvent
      .subscribe((elementToDelete) => receivedElement = elementToDelete);

    let debugElement = fixture.nativeElement
      .querySelector('#ft-delete-muscle-group-or-exercise-button');

    debugElement.click();

    expect(receivedElement.name).toBe('Chest');
  });
});
