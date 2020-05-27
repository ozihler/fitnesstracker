import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {SetChangeValue} from './set-change-value';
import {SetFormValues} from './set-form-values';

@Component({
  selector: 'app-set-values',
  template: `
    <div *ngIf="!formValues?.isDisabled" class="uk-flex-center" uk-grid>
      <form [formGroup]="form">
        <button *ngFor="let v of formValues?.negativeValues"
                id="{{titlePrefix}}-subtract{{formatDecimalValues(v)}}-button"
                class="uk-button uk-width-1-3"
                (click)="changeValue(v)">-
        </button>

        <div class="uk-width-1-1">
          <input
            id="{{titlePrefix}}-input"
            class="uk-form-width-small uk-input"
            formControlName="value"
            (change)="changeValueDirectly()"
            (keyup)="changeValueDirectly()"
            type="number"> {{formValues?.unit}}
        </div>

        <button *ngFor="let v of formValues?.positiveValues"
                id="{{titlePrefix}}-add-{{formatDecimalValues(v)}}-button"
                class="uk-button uk-width-1-3"
                (click)="changeValue(v)">+
        </button>
      </form>
    </div>

    <div *ngIf="formValues?.isDisabled">
      <button
        id="change-{{titlePrefix}}-button"
        class="uk-button uk-width-1-1 uk-text-truncate"
        (click)="toggleButton()">
        Change {{formValues?.valueName}} </button>
    </div>
  `
})
export class SetValuesComponent implements OnInit {
  @Input() titlePrefix: string;
  @Input() formValues: SetFormValues;
  @Output() updateEvent = new EventEmitter<SetChangeValue>();
  @Output() disableOtherUpdatesEvent = new EventEmitter<string>();

  form: FormGroup;

  constructor(private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    if (this.form) {
      return;
    }
    this.form = this.formBuilder.group({
      value: ['0', [Validators.required, Validators.min(0)]],
    });
  }

  changeValue(changeValue: number) {
    const control = this.form.get('value');
    let newValue = parseFloat(control.value) + changeValue;
    if (newValue < 0) {
      newValue = 0;
    }
    control.setValue(newValue);
    this.updateEvent.emit(new SetChangeValue(this.formValues.valueName, newValue));
  }


  toggleButton() {
    this.disableOtherUpdatesEvent.emit(this.formValues.valueName);
  }

  changeValueDirectly() {
    this.changeValue(0);
  }

  formatDecimalValues(v: number) {
    // todo replace with pipe
    return v.toString().replace('.', '-');
  }
}
