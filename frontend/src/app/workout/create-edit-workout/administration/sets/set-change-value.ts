export class SetChangeValue {

  constructor(private _formControlName: string,
              private _changeValue: number) {

  }

  get formControlName(): string {
    return this._formControlName;
  }

  get changeValue(): number {
    return this._changeValue;
  }
}
