import {Component, EventEmitter, Input, Output} from '@angular/core';
import {TreeNode} from "./tree/tree-node";

@Component({
  selector: 'app-button-tree',
  template: `
    <div class="uk-grid uk-grid-collapse" *ngIf="node?.isEnabled">
      <button class="uk-button uk-width-1-1 uk-text-truncate"
              [ngClass]="getLevelClass"
              [disabled]="node.isLeaf"
              (click)="changeSelection()">
        <span *ngIf="node && !node?.isLeaf">({{node?.numberOfChildren}}) </span>
        <span>{{node?.name}}</span>
      </button>
      <button (click)="removeFromWorkout(node)">DEL</button>
    </div>

    <div *ngIf="node?.hasChildren">
      <div *ngFor="let child of node?.children">
        <app-button-tree
          [currentSelectionName]="currentSelectionName"
          [node]="child"
          (changeSelectionEvent)="this.changeSelectionEvent.emit($event);"
          (removeFromWorkoutEvent)="removeFromWorkout($event);"
        >
        </app-button-tree>
      </div>
    </div>
  `
})
export class TreeViewComponent {
  @Input() node: TreeNode;
  @Input() currentSelectionName: string;
  @Output() changeSelectionEvent = new EventEmitter<TreeNode>();
  @Output() removeFromWorkoutEvent = new EventEmitter<TreeNode>();

  constructor() {
  }

  get getLevelClass() {
    if (this.isSelectedNode()) {
      return "uk-button-danger";
    }
    return this.node ? TreeNode.LEVEL_CLASSES[this.node.type] : "";
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
}
