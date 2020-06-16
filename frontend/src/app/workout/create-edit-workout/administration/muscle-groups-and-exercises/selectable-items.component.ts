import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Type} from '../../../shared/type';
import {TreeNode} from '../../workout-tree/tree-node';
import {ReplacePipe} from '../../../shared/pipes/replace.pipe';
import {Set} from '../../../shared/set';
import {Exercise} from '../../../shared/exercise';

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
          [multiplier]="multiplier"
          (createSet)="createChild($event)">
        </app-create-set>
      </div>
    </div>  `
})
export class SelectableItemsComponent implements OnInit {

  @Input() currentSelection: TreeNode;
  @Input() selectableItems: TreeNode[];
  @Output() addItemEvent = new EventEmitter<TreeNode>();
  @Output() deleteItemEvent = new EventEmitter<TreeNode>();
  @Output() createsChildEvent = new EventEmitter<string | Set>();

  constructor(private replacePipe: ReplacePipe) {

  }

  ngOnInit() {
  }

  get isExercise() {
    return this.currentSelection && this.currentSelection.type === Type.Exercise;
  }

  get multiplier() {
    if (this.currentSelection.type === Type.Exercise) {
      return (this.currentSelection as Exercise).multiplier;
    } else {
      return undefined;
    }
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

  select(item: TreeNode) {
    this.addItemEvent.emit(item);
  }

  delete(item: TreeNode) {
    this.deleteItemEvent.emit(item);
  }

  childTypeName() {
    return Type[this.currentSelection ? this.currentSelection.type + 1 : 0];
  }
}
