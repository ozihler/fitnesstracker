import {Component, EventEmitter, Input, Output} from '@angular/core';
import {TreeNode} from '../workout-tree/tree-node';
import {Type} from '../../shared/type';
import {ElementsToId} from '../../shared/elements-to-id';

@Component({
  selector: 'app-workout-tree',
  template: `
    <div class="uk-grid uk-grid-collapse" *ngIf="node?.isEnabled">
      <button
        id="select-{{nodeId}}-editable-node"
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

  get nodeId() {
    return (this.node?.isRoot ? 'root' : this.format(this.node?.name));
  }

  private format(name: any) {
    console.error('name is ', name);
    const nameAfterFormatting = ElementsToId.replace(name);

    console.error('Name after formatting is ', nameAfterFormatting);
    return nameAfterFormatting;
  }

  private formatParents(node: TreeNode) {
    let nameConcat = '';
    if (!node) {
      return nameConcat;
    }
    if (!node.parent) {
      return nameConcat;
    } else {
      return this.formatParents(node.parent) + '-' + this.format(node.name);
    }
  }
}
