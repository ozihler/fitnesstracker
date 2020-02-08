import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Type} from "../shared/type";
import {TreeNode} from "./tree/tree-node";

@Component({
  selector: 'app-element-selection',
  template: `
    <div class="uk-text-center">
      <span>{{title}}</span>
      <hr *ngIf="!childrenAreLeafs"/>
      <div *ngIf="!hasSelectableElements">
        <span>{{emptyElementsText}}</span>
      </div>

      <div *ngIf="!isExercise">
        <div class="uk-grid-small uk-child-width-1-2 uk-flex-center" uk-grid
             *ngFor="let element of selectableElements">
          <app-selectable-element
            [element]="element"
            (selectElementEvent)="select($event)"
            (deleteElementEvent)="delete($event)"
          >

          </app-selectable-element>

        </div>
        <hr/>
        <app-create-element (createElementsEvent)="createChild($event)"
                            [typename]="formattedTypeName"></app-create-element>
      </div>
      <div *ngIf="isExercise">
        <app-create-set (createSet)="createChild($event)"></app-create-set>
      </div>
    </div>  `,
  styles: []
})
export class ElementSelection implements OnInit {

  @Input() currentSelection: TreeNode;
  @Input() selectableElements: TreeNode[];
  @Output() addNodeEvent = new EventEmitter<TreeNode>();
  @Output() createsChildEvent = new EventEmitter<string>();


  constructor() {
  }

  ngOnInit() {
  }

  select(element: TreeNode) {
    this.addNodeEvent.emit(element);
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

  delete(node: TreeNode) {
    if (node.type == Type.Muscle_Group) {
      console.log("Delete Musclegroup ", node);
    } else if (node.type == Type.Exercise) {
      console.log("Delete Exercise ", node);
    }

  }
}
