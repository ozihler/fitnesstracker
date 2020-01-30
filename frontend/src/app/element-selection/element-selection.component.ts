import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {SelectableElement} from "../shared/selectable-element";
import {Type} from "../shared/type";

@Component({
  selector: 'app-element-selection',
  template: `
    <div>
      <div class="uk-grid-small uk-child-width-1-2 uk-flex-center" uk-grid *ngFor="let element of selectableElements">
        <button class="uk-button uk-button-default" (click)="select(element)">{{element.name}}</button>
      </div>
      <app-create-element (createElementsEvent)="createElements($event)" [type]="type"></app-create-element>
    </div>  `,
  styles: []
})
export class ElementSelection implements OnInit, OnChanges {

  @Input() type: Type;
  @Input() selectableElements: SelectableElement[];
  @Output() selectedElement = new EventEmitter<SelectableElement>();
  @Output() createsElementEvent = new EventEmitter<string>();


  constructor() {
  }

  ngOnInit() {
    //this.init();
  }

  private init() {
    if (this.selectableElements && this.selectableElements.length) {
      this.type = this.selectableElements[0].type;
    } else {
      this.type = Type.Muscle_Group;
    }
  }

  select(element: SelectableElement) {
    this.selectedElement.emit(element);
  }

  ngOnChanges(changes: SimpleChanges): void {
    //this.init();
  }

  createElements(elements: string) {
    this.createsElementEvent.emit(elements);
  }
}
