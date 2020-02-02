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
      <app-create-element *ngIf="!isSet()"
                          (createElementsEvent)="createChild($event)"
                          [typename]="formattedTypeName()"></app-create-element>

      <app-create-set *ngIf="isSet()" (createSet)="createChild($event)"
                      [typename]="formattedTypeName()"></app-create-set>
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
  }

  createChild(elementsString: string) {
    this.createsChildEvent.emit(elementsString);
  }

  typeName() {
    return Type[this.type];
  }

  isSet() {
    return this.type === Type.Set;
  }

  formattedTypeName() {
    return this.typeName() ? this.typeName().replace("_", " ") : "";
  }
}
