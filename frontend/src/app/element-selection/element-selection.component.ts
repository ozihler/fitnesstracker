import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {Type} from "../shared/type";
import {TreeNode} from "../shared/tree-node";

@Component({
  selector: 'app-element-selection',
  template: `
    <div>
      <div class="uk-grid-small uk-child-width-1-2 uk-flex-center" uk-grid
           *ngFor="let element of selectableElements">
        <button class="uk-button uk-button-default"
                (click)="select(element)">{{element.name}}</button>
      </div>
      <app-create-element (createElementsEvent)="createChild($event)" [type]="typeName()"></app-create-element>
    </div>  `,
  styles: []
})
export class ElementSelection implements OnInit, OnChanges {

  @Input() type: Type;
  @Input() selectableElements: TreeNode[];
  @Output() addNodeEvent = new EventEmitter<TreeNode>();
  @Output() createsChildEvent = new EventEmitter<string>();


  constructor() {
  }

  ngOnInit() {
  }

  select(element: TreeNode) {
    this.addNodeEvent.emit(element);
  }

  ngOnChanges(changes: SimpleChanges): void {
    //this.init();
  }

  createChild(elementsString: string) {
    this.createsChildEvent.emit(elementsString);
  }

  typeName() {
    return Type[this.type];
  }
}
