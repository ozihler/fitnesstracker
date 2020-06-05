import {Component, EventEmitter, Output} from '@angular/core';
import {SetFormValues} from './set-form-values';
import {SetChangeValue} from './set-change-value';
import {SetFormData} from './set-form-data';
import {SetFormatPipe} from '../../../shared/pipes/set-format.pipe';
import {Set} from '../../../shared/set';

@Component({
  selector: 'app-create-set',
  template: `
    <div class="uk-flex-center">
      <mark id="current-set-input-mark">{{currentValues}}</mark>
      <button id="ft-add-set-to-exercise"
              class="uk-button uk-button-primary uk-width-1-1"
              *ngIf="showButton"
              (click)="submit()">
        <i class="fa fa-plus"></i>
      </button>
    </div>
    <hr/>

    <div>
      <app-set-values
        titlePrefix="weight"
        [formValues]="formValues.weight"
        (updateEvent)="update($event)"
        (disableOtherUpdatesEvent)="disableOtherUpdates($event)">
      </app-set-values>
      <app-set-values
        titlePrefix="repetitions"
        [formValues]="formValues.repetitions"
        (updateEvent)="update($event)"
        (disableOtherUpdatesEvent)="disableOtherUpdates($event)">
      </app-set-values>
      <app-set-values
        titlePrefix="waitingTime"
        [formValues]="formValues.waitingTime"
        (updateEvent)="update($event)"
        (disableOtherUpdatesEvent)="disableOtherUpdates($event)">
      </app-set-values>
    </div>
    <div>
      <button id="toggle-set-parts-button"
              class="uk-button uk-width-1-1 uk-text-truncate"
              (click)="disableOtherUpdates('NONE')">Toggle Set Parts
      </button>
    </div>
  `
})
export class CreateSetComponent {

  showButton = true;

  formValues = new SetFormData(
    SetFormValues.of([-.5, -1, -10], [.5, 1, 10], true, 'kg', 'weight'),
    SetFormValues.of([-1, -2, -5], [1, 2, 5], true, '#', 'repetitions'),
    SetFormValues.of([-1, -5, -10], [1, 5, 10], true, 's', 'waitingTime'));


  @Output() createSet = new EventEmitter<Set>();


  constructor(private setFormatPipe: SetFormatPipe) {

  }

  get currentValues() {
    return this.setFormatPipe.transform(this.formValues.asMinimalSetWithoutParentOrIndex());
  }

  submit() {
    this.showButton = true;
    this.createSet.emit(this.formValues.asMinimalSetWithoutParentOrIndex());
  }

  update(changedValue: SetChangeValue) {
    this.formValues[changedValue.formControlName].currentValue = changedValue.changeValue;
  }

  disableOtherUpdates(otherValuesName: string) {
    Object.keys(this.formValues)
      .forEach(name => this.formValues[name].isDisabled = name !== otherValuesName);
  }
}

