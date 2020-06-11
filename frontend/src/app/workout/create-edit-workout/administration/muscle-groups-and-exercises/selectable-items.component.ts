import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Type} from '../../../shared/type';
import {TreeNode} from '../../workout-tree/tree-node';
import {ReplacePipe} from '../../../shared/pipes/replace.pipe';
import {Set} from '../../../shared/set';

@Component({
  selector: 'app-selectable-items',
  template: `
    <div class="uk-text-center">
      <span
        id="empty-elements-text"
        *ngIf="!hasSelectableItems">{{emptyItemsText}}</span>

      <div *ngIf="!isExercise">
        <div *ngFor="let item of selectableItems">
          <app-selectable-muscle-group-or-exercise
            [item]="item"
            (selectItemEvent)="select($event)"
            (deleteItemEvent)="delete($event)">
          </app-selectable-muscle-group-or-exercise>
        </div>
        <hr/>
        <app-create-muscle-groups-and-exercises
          (createItemsEvent)="createChild($event)"
          [typename]="child">
        </app-create-muscle-groups-and-exercises>
      </div>

      <div *ngIf="isExercise">
        <app-create-set
          (createSet)="createChild($event)">
        </app-create-set>
      </div>
    </div>  `
})
export class SelectableItemsComponent implements OnInit {

  @Input() currentSelection: TreeNode;
  @Input() selectableItems: TreeNode[];
  @Output() addNodeEvent = new EventEmitter<TreeNode>();
  @Output() deleteNodeEvent = new EventEmitter<TreeNode>();
  @Output() createsChildEvent = new EventEmitter<string | Set>();

  constructor(private replacePipe: ReplacePipe) {

  }

  ngOnInit() {
  }

  select(item: TreeNode) {
    this.addNodeEvent.emit(item);
  }

  delete(item: TreeNode) {
    this.deleteNodeEvent.emit(item);
  }

  createChild(itemsString: string | Set) {
    this.createsChildEvent.emit(itemsString);
  }

  get hasSelectableItems() {
    return this.selectableItems && this.selectableItems.length > 0;
  }

  get title() {
    if (!this.currentSelection) {
      return '';
    }

    return `Add ${this.child} to ${this.currentSelection.name}`;
  }

  get emptyItemsText() {
    if (!this.currentSelection || this.childrenAreLeafs) {
      return '';
    }

    return `No ${this.child} to select. Create a new one first!`;
  }

  get child() {
    return this.replacePipe.transform(this.childTypeName(), '_', ' ');
  }

  get childrenAreLeafs() {
    return this.currentSelection
      && this.currentSelection.children
      && this.currentSelection.children.some(value => value.isLeaf)
      || this.isExercise && this.currentSelection.children.length === 0;
  }

  get isExercise() {
    return this.currentSelection && this.currentSelection.typeOfCurrentlySelection === Type.Exercise;
  }

  childTypeName() {
    return Type[this.currentSelection ? this.currentSelection.typeOfCurrentlySelection + 1 : 0];
  }
}
