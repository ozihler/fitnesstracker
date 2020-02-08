import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {TreeNode} from "./tree/tree-node";

@Component({
  selector: 'app-selectable-element',
  template: `
    <div class="uk-grid-small uk-align-center" uk-grid>
      <button class="uk-button uk-button-default"
              (click)="select(element)">{{element?.name}}
      </button>
      <button class="uk-button uk-button-secondary">
        <fa name="pencil"></fa>
      </button>
      <button class="uk-button uk-button-danger"
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
