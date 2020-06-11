import {Component, EventEmitter, Input, Output} from '@angular/core';
import {TreeNode} from '../workout-tree/tree-node';
import {Type} from '../../shared/type';

@Component({
  selector: 'app-workout-tree',
  template: `
    <div class="uk-grid uk-grid-collapse" *ngIf="node?.isEnabled">
      <button
        id="select-{{nodeId}}-editable-node"
        class="uk-button uk-text-truncate"
        [ngClass]="levelDependentClasses"
        [disabled]="node.isLeaf"
        (click)="changeSelection(this.node)">

        <span *ngIf="node && !node?.isLeaf">
          ({{node?.numberOfChildren}})
        </span>

        <span>
          {{node?.name}}
        </span>

        <span>
          ({{node?.cumulateWeight()}} kg)
        </span>
      </button>

      <button
        id="remove-node-{{nodeId}}-from-workout-tree"
        class="uk-button uk-button-danger uk-width-1-3"
        *ngIf="!isWorkout()"
        (click)="removeFromWorkout(node)">
        <i class="fa fa-trash"></i>
      </button>
    </div>

    <div *ngIf="node?.hasChildren">
      <div *ngFor="let child of node?.children">
        <app-workout-tree
          [currentSelectionId]="currentSelectionId"
          [node]="child"
          (changeSelectionEvent)="changeSelection($event)"
          (removeFromWorkoutEvent)="removeFromWorkout($event)">
        </app-workout-tree>
      </div>
    </div>
  `
})
export class WorkoutTreeComponent {
  @Input() node: TreeNode;
  @Input() currentSelectionId: string;
  @Output() changeSelectionEvent = new EventEmitter<TreeNode>();
  @Output() removeFromWorkoutEvent = new EventEmitter<TreeNode>();

  constructor() {
  }

  get levelDependentClasses(): string[] {
    const classes: string[] = [];
    if (this.isSelectedNode()) {
      classes.push('uk-button-danger');
    }

    if (this.node) {
      if (TreeNode.LEVEL_CLASSES[this.node.typeOfCurrentlySelection]) {
        classes.push(TreeNode.LEVEL_CLASSES[this.node.typeOfCurrentlySelection]);
      }

      if (this.node.typeOfCurrentlySelection === Type.Workout) {
        classes.push('uk-width-1-1');
      } else {
        classes.push('uk-width-2-3');
      }
    }

    return classes;
  }

  changeSelection(node: TreeNode) {
    if (!node) {
      return;
    }

    this.changeSelectionEvent.emit(node);
  }

  private isSelectedNode() {
    //console.error("isSelectedNode, currentSelectionID: ", this.currentSelectionId + ", nodeid: " + this.node.id);
    return this.currentSelectionId
      && this.node
      && this.currentSelectionId === this.node.id;
  }

  removeFromWorkout(node: TreeNode) {
    this.removeFromWorkoutEvent.emit(node);
  }

  isWorkout() {
    return this.node && this.node.typeOfCurrentlySelection === Type.Workout;
  }

  get nodeId() {
    return this.node.id;
  }
}
