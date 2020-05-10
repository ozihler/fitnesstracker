import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Type} from "../../../shared/type";
import {TreeNode} from "../../workout-tree/tree-node";

@Component({
  selector: 'muscle-group-or-exercise-selection',
  template: `
    <div class="uk-text-center">
      <div *ngIf="!hasSelectableElements">
        <span>{{emptyElementsText}}</span>
      </div>

      <div *ngIf="!isExercise">
        <div *ngFor="let element of selectableElements">
          <app-selectable-muscle-group-or-exercise
            [element]="element"
            (selectElementEvent)="select($event)"
            (deleteElementEvent)="delete($event)">
          </app-selectable-muscle-group-or-exercise>
        </div>
        <hr/>
        <app-create-muscle-groups-and-exercises
          (createElementsEvent)="createChild($event)"
          [typename]="formattedTypeName">
        </app-create-muscle-groups-and-exercises>
      </div>

      <div *ngIf="isExercise">
        <app-create-set
          (createSet)="createChild($event)">
        </app-create-set>
      </div>
    </div>  `,
  styles: []
})
export class ElementSelection implements OnInit {

  @Input() currentSelection: TreeNode;
  @Input() selectableElements: TreeNode[];
  @Output() addNodeEvent = new EventEmitter<TreeNode>();
  @Output() deleteNodeEvent = new EventEmitter<TreeNode>();
  @Output() createsChildEvent = new EventEmitter<string>();


  constructor() {
  }

  ngOnInit() {
  }

  select(element: TreeNode) {
    this.addNodeEvent.emit(element);
  }

  delete(element: TreeNode) {
    this.deleteNodeEvent.emit(element);
  }

  createChild(elementsString: string) {
    this.createsChildEvent.emit(elementsString);
  }

  get formattedTypeName() {
    return ElementSelection.format(this.childTypeName());
  }

  get hasSelectableElements() {
    return this.selectableElements && this.selectableElements.length > 0;
  }

  get title() {
    if (!this.currentSelection) {
      return "";
    }

    return "Add " + ElementSelection.format(this.childTypeName()) + " to " + this.currentSelection.name;
  }

  get emptyElementsText() {
    if (!this.currentSelection || this.childrenAreLeafs) {
      return "";
    }

    return "No " + ElementSelection.format(this.childTypeName()) + " to select. Create a new one first!";
  }

  get childrenAreLeafs() {
    return this.currentSelection && this.currentSelection.children && this.currentSelection.children.some(value => value.isLeaf)
      || this.isExercise && this.currentSelection.children.length === 0;
  }

  get isExercise() {
    return this.currentSelection && this.currentSelection.type === Type.Exercise;
  }

  private static format(typeName: string) {
    return typeName ? typeName.replace("_", " ") : "";
  }

  childTypeName() {
    return Type[this.currentSelection ? this.currentSelection.type + 1 : 0];
  }
}
