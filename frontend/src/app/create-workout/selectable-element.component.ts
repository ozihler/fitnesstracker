import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {TreeNode} from "./tree/tree-node";

@Component({
  selector: 'app-selectable-element',
  template: `
    <div>
      <button class="uk-button uk-button-default"
              (click)="select(element)">{{element?.name}}
      </button>
      <button class="uk-button uk-button-secondary">
        <fa name="pencil"></fa>
      </button>
      <button class="uk-button uk-button-danger">
        <fa name="trash"></fa>
      </button>
    </div>`
})
export class SelectableElementComponent implements OnInit {

  @Input() element: TreeNode;
  @Output() selectElementEvent = new EventEmitter<TreeNode>()

  constructor() {
  }

  ngOnInit() {
  }

  select(element: TreeNode) {
    if (element) {
      this.selectElementEvent.emit(element);
    }
  }
}
