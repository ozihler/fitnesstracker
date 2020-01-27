import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {NodeType} from '../workout-details-view/button-group/button-node';

@Component({
  selector: 'app-element-selection',
  template: `
    <div>
      <div>
        <button
          (click)="select(element)"
          *ngFor="let element of elements">{{element}}</button>
      </div>
      <div>
        <button routerLink="/create-{{getNodeType()  }}">Create {{getTitle() }}</button>
      </div>
    </div>  `,
  styles: []
})
export class ElementSelection implements OnInit {

  @Input() elements: string[];
  @Input() type: NodeType;
  @Output() selectedElement = new EventEmitter<any>();

  constructor() {
  }

  ngOnInit() {
  }

  select(element: string) {
    this.selectedElement.emit({element: element, nodeType: this.type});
  }

  getNodeType() {
    return NodeType[this.type].toString().toLowerCase().replace("_", "-");
  }

  getTitle() {
    return NodeType[this.type].toString().toLowerCase().replace("_", " ");
  }
}
