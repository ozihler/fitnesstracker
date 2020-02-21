import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {TreeNode} from "./tree/tree-node";

@Component({
  selector: 'app-selectable-element',
  template: `
    <div class="uk-grid uk-grid-collapse">
      <button class="uk-button uk-button-default uk-width-2-3 uk-text-truncate"
              (click)="select(element)">{{element?.name}}
      </button>
     <!-- <button class="uk-button uk-button-secondary uk-width-1-5 ">
        <fa name="pencil"></fa>
      </button>-->
      <button class="uk-button uk-button-danger uk-width-1-3"
              (click)="delete(element)">
        <fa name="trash"></fa>
      </button>
    </div>`
})
export class SelectableElementComponent implements OnInit {

  @Input() element: TreeNode;
  @Output() selectElementEvent = new EventEmitter<TreeNode>()
  @Output() deleteElementEvent = new EventEmitter<TreeNode>()

  constructor() {
  }

  ngOnInit() {
  }

  select(element: TreeNode) {
    if (element) {
      this.selectElementEvent.emit(element);
    }
  }

  delete(element: TreeNode) {
    this.deleteElementEvent.emit(element);
  }
}
