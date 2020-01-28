import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {SelectableElement} from "../shared/selectable-element";
import {Type} from "../workout-details-view/button-group/button-node";

@Component({
  selector: 'app-element-selection',
  template: `
    <div>
      <div class="uk-grid-small uk-child-width-1-2 uk-flex-center" uk-grid *ngFor="let element of selectableElements">
        <button class="uk-button uk-button-default" (click)="select(element)">{{element.name}}</button>
      </div>
      <div>
        <button routerLink="/create-{{formatLink(type)}}">Create {{format(type)}} </button>
      </div>
    </div>  `,
  styles: []
})
export class ElementSelection implements OnInit {

  private type: Type;
  @Input() selectableElements: SelectableElement[];
  @Output() selectedElement = new EventEmitter<SelectableElement>();

  constructor() {
  }

  ngOnInit() {
    if (this.selectableElements && this.selectableElements.length) {
      this.type = this.selectableElements[0].type;
    } else {
      this.type = Type.Muscle_Group;
    }
  }

  select(element: SelectableElement) {
    this.selectedElement.emit(element);
  }

  format(type: Type) {
    return Type[type].replace("_", " ");
  }

  formatLink(type: Type) {
    return Type[type].toLowerCase().replace("_", "-");
  }
}
