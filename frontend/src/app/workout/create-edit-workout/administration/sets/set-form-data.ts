import {SetFormValues} from './set-form-values';
import {Set} from '../../../shared/set';

export class SetFormData {
  weight: SetFormValues;
  repetitions: SetFormValues;
  waitingTime: SetFormValues;

  constructor(weight: SetFormValues, repetitions: SetFormValues, waitingTime: SetFormValues) {
    this.weight = weight;
    this.repetitions = repetitions;
    this.waitingTime = waitingTime;
  }

  asMinimalSetWithoutParentOrIndex(multiplier: number): Set {
    return new Set(
      this.weight.currentValue,
      this.weight.unit,
      this.repetitions.currentValue,
      this.waitingTime.currentValue,
      this.waitingTime.unit,
      undefined,
      0,
      multiplier
    );
  }
}
