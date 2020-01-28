import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ButtonNode} from "./button-node";

@Component({
  selector: 'app-button-tree',
  template: `
    <div *ngIf="node">
      <div class="uk-grid uk-grid-collapse">

        <div class="uk-width-3-5">
          <button class="uk-button uk-width-1-1 uk-text-truncate"
                  [ngClass]="getLevelClass()"
                  (click)="toggleNode()">{{node?.name}}
            <span *ngIf="!node.isLeaf()">({{node?.numberOfChildren()}})</span>
          </button>
        </div>

        <div class="uk-width-2-5">
          <button class="">[x]</button>
          <button class="" (click)="changeSelection(node)">[+]
          </button>
          <button class=" ">[E]</button>
        </div>

      </div>

      <div *ngIf="shouldShowChildren()">
        <div *ngFor="let child of this.node.children">
          <app-button-tree
            [node]="child"
            (changeSelectionEvent)="changeSelection($event)">
          </app-button-tree>
        </div>

      </div>
    </div>
  `
})
export class ButtonTreeComponent implements OnInit {
  @Input() private node: ButtonNode;
  @Output() private changeSelectionEvent = new EventEmitter<ButtonNode>();

  private toggles = [];

  constructor() {
  }

  ngOnInit() {
    if (this.node) {
      this.toggles[this.node.name] = false;
    }
  }

  shouldShowChildren() {
    return this.isEnabled() && this.node.hasChildren();
  }

  private isEnabled() {
    return this.toggles[this.node.name];
  }

  toggleNode() {
    this.toggles[this.node.name] = !this.toggles[this.node.name];
    if (this.isEnabled()) {
      this.changeSelection(this.node);
    }
  }

  getLevelClass() {
    return ButtonNode.LEVEL_CLASSES[this.node.type];
  }

  changeSelection(node: ButtonNode) {
    this.changeSelectionEvent.emit(node);
  }

}
