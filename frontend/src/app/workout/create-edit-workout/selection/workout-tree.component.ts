import {Component, EventEmitter, Input, Output} from '@angular/core';
import {TreeNode} from '../workout-tree/tree-node';
import {Type} from '../../shared/type';

@Component({
  selector: 'app-workout-tree',
  template: `
    <div class="uk-grid uk-grid-collapse" *ngIf="node?.isEnabled">
      <button
        id="{{!node?.parent?'root-':''}}select-{{node?.name | replace : ' ': '-' | lowercase}}-editable-node"
        class="uk-button uk-text-truncate"
        [ngClass]="levelDependentClasses"
        [disabled]="node.isLeaf"
        (click)="changeSelection()">

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
        id="remove-node-from-workout-tree"
        class="uk-button uk-button-danger uk-width-1-3"
        *ngIf="!isWorkout()"
        (click)="removeFromWorkout(node)">
        <i class="fa fa-trash"></i>
      </button>
    </div>

    <div *ngIf="node?.hasChildren">
      <div *ngFor="let child of node?.children">
        <app-workout-tree
          [currentSelectionName]="currentSelectionName"
          [node]="child"
          (changeSelectionEvent)="this.changeSelectionEvent.emit($event);"
          (removeFromWorkoutEvent)="removeFromWorkout($event)">
        </app-workout-tree>
      </div>
    </div>
  `
})
export class WorkoutTreeComponent {
  @Input() node: TreeNode;
  @Input() currentSelectionName: string;
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
      if (TreeNode.LEVEL_CLASSES[this.node.type]) {
        classes.push(TreeNode.LEVEL_CLASSES[this.node.type]);
      }

      if (this.node.type === Type.Workout) {
        classes.push('uk-width-1-1');
      } else {
        classes.push('uk-width-2-3');
      }
    }

    return classes;
  }

  changeSelection() {
    if (!this.node) {
      return;
    }

    this.changeSelectionEvent.emit(this.node);
  }

  private isSelectedNode() {
    return this.currentSelectionName && this.node && this.currentSelectionName === this.node.name;
  }

  removeFromWorkout(node: TreeNode) {
    this.removeFromWorkoutEvent.emit(node);
  }

  isWorkout() {
    return this.node && this.node.type === Type.Workout;
  }
}
