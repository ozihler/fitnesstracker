export class SetFormValues {
  constructor(private _negativeValues: number[],
              private _positiveValues: number[],
              private _isDisabled: boolean,
              private _unit: string,
              private _valueName: string,
              private _currentValue: number) {
  }


  set negativeValues(value: number[]) {
    this._negativeValues = value;
  }

  set positiveValues(value: number[]) {
    this._positiveValues = value;
  }

  set unit(value: string) {
    this._unit = value;
  }

  set valueName(value: string) {
    this._valueName = value;
  }

  set currentValue(value: number) {
    this._currentValue = value;
  }

  get unit(): string {
    return this._unit;
  }

  get valueName(): string {
    return this._valueName;
  }

  get negativeValues(): number[] {
    return this._negativeValues;
  }

  get positiveValues(): number[] {
    return this._positiveValues;
  }

  get isDisabled(): boolean {
    return this._isDisabled;
  }

  set isDisabled(value: boolean) {
    this._isDisabled = value;
  }

  get currentValue(): number {
    return this._currentValue;
  }

  static of(negativeValues: number[],
            positiveValues: number[],
            isDisabled: boolean,
            unit: string,
            valueName: string) {
    return new SetFormValues(
      negativeValues,
      positiveValues,
      isDisabled,
      unit,
      valueName,
      0.0);
  }
}
