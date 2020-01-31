import {Component, EventEmitter, Input, Output} from '@angular/core';
import {TreeNode} from "../shared/tree-node";

@Component({
  selector: 'app-button-tree',
  template: `
    <div class="uk-grid uk-grid-collapse">
      <button class="uk-button uk-width-1-1 uk-text-truncate" [ngClass]="getLevelClass()" (click)="toggleNode()">
        <span *ngIf="node && !node?.isLeaf && !node?.isEnabled">({{node?.numberOfChildren}}) </span>
        <span>{{node?.name}}</span>
      </button>
    </div>

    <div *ngIf="this.node?.isEnabled && this.node?.hasChildren">
      <div *ngFor="let child of this.node?.children">
        <app-button-tree
          [node]="child"
          (changeSelectionEvent)="changeSelection($event)">
        </app-button-tree>
      </div>

    </div>
  `
})
export class TreeViewComponent {
  @Input() private node: TreeNode;
  @Output() private changeSelectionEvent = new EventEmitter<TreeNode>();

  constructor() {
  }


  toggleNode() {
    if (!this.node) {
      return;
    }
    this.node.isEnabled = !this.node.isEnabled;
    this.changeSelection(this.node);
  }

  getLevelClass() {
    return this.node ? TreeNode.LEVEL_CLASSES[this.node.type] : "";
  }

  changeSelection(node: TreeNode) {
    this.changeSelectionEvent.emit(node);
  }
}
