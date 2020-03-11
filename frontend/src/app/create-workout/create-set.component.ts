import {Component, EventEmitter, Output} from '@angular/core';
import {SetFormValues} from "./set-form-values";
import {ChangeValue} from "./change-value";

@Component({
  selector: 'app-create-set',
  template: `
    <div class="uk-flex-center">
      <mark>{{currentValues}}</mark>
      <button class="uk-button uk-button-primary uk-width-1-1"
              *ngIf="showButton"
              (click)="submit()">
        <fa name="plus"></fa>
      </button>
    </div>
    <hr/>

    <div>
      <app-set-values
        [formValues]="formValues.weight"
        (updateEvent)="update($event)"
        (disableOtherUpdatesEvent)="disableOtherUpdates($event)">
      </app-set-values>
      <app-set-values
        [formValues]="formValues.repetitions"
        (updateEvent)="update($event)"
        (disableOtherUpdatesEvent)="disableOtherUpdates($event)">
      </app-set-values>
      <app-set-values
        [formValues]="formValues.waitingTime"
        (updateEvent)="update($event)"
        (disableOtherUpdatesEvent)="disableOtherUpdates($event)">
      </app-set-values>
    </div>
    <div>
      <button class="uk-button uk-width-1-1 uk-text-truncate"
              (click)="disableOtherUpdates('NONE')">Toggle Set Parts
      </button>
    </div>
  `
})
export class CreateSetComponent {

  formValues = {
    weight: SetFormValues.of(
      [-.5, -1, -10],
      [.5, 1, 10],
      true,
      "kg",
      "weight"),
    repetitions: SetFormValues.of(
      [-1, -2, -5],
      [1, 2, 5],
      true,
      "#",
      "repetitions"),
    waitingTime: SetFormValues.of(
      [-1, -5, -10],
      [1, 5, 10],
      true,
      "s",
      "waitingTime")
  };

  @Output() createSet = new EventEmitter<string>();

  showButton: boolean = true;

  constructor() {

  }

  submit() {
    this.emitNewSetEvent(this.concatenateValues);
  }


  get concatenateValues() {
    return `${this.formValues.weight.currentValue}_${this.formValues.repetitions.currentValue}_${this.formValues.waitingTime.currentValue}`;
  }

  get currentValues() {
    return CreateSetComponent.format(this.formValues.weight)
      + CreateSetComponent.format(this.formValues.repetitions)
      + CreateSetComponent.format(this.formValues.waitingTime);

  }

  private static format(values: SetFormValues) {
    return values.currentValue ? values.currentValue + " " + values.unit + ", " : "";
  }

  private emitNewSetEvent(createdElements: string) {
    this.showButton = true;
    this.createSet.emit(createdElements);
  }

  update(changedValue: ChangeValue) {
    this.formValues[changedValue.formControlName].currentValue = changedValue.changeValue;
  }

  disableOtherUpdates(otherValuesName: string) {
    Object.keys(this.formValues)
      .forEach(name => this.formValues[name].isDisabled = name !== otherValuesName);
  }
}
