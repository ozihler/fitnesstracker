import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ChangeValue} from "./form/change-value";
import {SetFormValues} from "./form/set-form-values";

@Component({
  selector: 'app-set-values',
  template: `
    <div *ngIf="!formValues?.isDisabled" class="uk-flex-center" uk-grid>
      <form [formGroup]="form">
        <button *ngFor="let v of formValues?.negativeValues"
                class="uk-button uk-width-1-3"
                (click)="changeValue(v)">-
        </button>

        <div class="uk-width-1-1">
          <input class="uk-form-width-small uk-input"
                 formControlName="value"
                 type="number"> {{formValues?.unit}}
        </div>

        <button *ngFor="let v of formValues?.positiveValues"
                class="uk-button uk-width-1-3"
                (click)="changeValue(v)">+
        </button>
      </form>
    </div>

    <div *ngIf="formValues?.isDisabled">
      <button
        class="uk-button uk-width-1-1 uk-text-truncate"
        (click)="toggleButton()">
        Change {{formValues?.valueName}} </button>
    </div>
  `
})
export class SetValuesComponent implements OnInit {

  @Input() formValues: SetFormValues;
  @Output() updateEvent = new EventEmitter<ChangeValue>();
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
    })
  }

  changeValue(changeValue: number) {
    let control = this.form.get('value');
    let newValue = parseFloat(control.value) + changeValue;
    if (newValue < 0) {
      newValue = 0;
    }
    control.setValue(newValue);
    this.updateEvent.emit(new ChangeValue(this.formValues.valueName, newValue));
  }


  toggleButton() {
    this.disableOtherUpdatesEvent.emit(this.formValues.valueName);
  }
}
