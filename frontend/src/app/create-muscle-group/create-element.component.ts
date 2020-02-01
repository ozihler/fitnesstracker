import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {WorkoutService} from "../shared/workout.service";
import {Location} from "@angular/common";
import {Type} from "../shared/type";

@Component({
  selector: 'app-create-element',
  template: `
    ===================================
    <div>
      <div *ngIf="!showButton">
        <div *ngIf="!isSet()">
          <form [formGroup]="createElement"
                (ngSubmit)="create()">
            <div class="uk-margin">
              <input class="uk-input"
                     formControlName="element"
                     type="text">
            </div>
            <button type="submit">Ok</button>
          </form>
        </div>

        <div *ngIf="isSet()">
          <input/> Reps
          <input/> Kg
          <input/> s
        </div>
      </div>
      <div *ngIf="showButton">
        <div>{{currentElementValue()}}</div>
        <button (click)="toggleButton()">Create {{format(type)}} </button>
      </div>
    </div>
  `,
  styles: []
})
export class CreateElementComponent implements OnInit {

  @Output() createElementsEvent = new EventEmitter<string>();
  @Input() type: string;

  showButton: boolean = true;
  createElement: FormGroup;

  constructor(private workoutService: WorkoutService,
              private location: Location,
              private formBuilder: FormBuilder) {

  }

  ngOnInit() {
    this.initForm();
  }

  private initForm() {
    if (this.createElement) {
      return;
    }
    this.createElement = this.formBuilder.group({
      element: ["", Validators.required]
    });
  }

  create() {
    if (this.hasEnteredAnything()) {
      this.emit(this.currentElementValue());
    }
  }

  private emit(createdElements: string) {
    this.showButton = true;
    this.createElement.reset();
    this.createElementsEvent.emit(createdElements);
  }

  private hasEnteredAnything() {
    return !!this.currentElementValue();
  }

  currentElementValue() {
    return this.createElement.get('element').value ? this.createElement.get('element').value.trim() : "";
  }

  toggleButton() {
    this.showButton = false;
  }

  format(type: string) {
    return type ? type.replace("_", " ") : "";
  }

  isSet() {
    return Type[this.type] === Type.Set;
  }
}
