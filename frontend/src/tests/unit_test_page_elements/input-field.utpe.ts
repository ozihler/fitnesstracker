export class InputField {
  constructor(private _inputField: HTMLInputElement) {
  }

  get input(): string {
    return this._inputField.value;
  }

  set input(value: string) {
    this._inputField.value = value;
    this._inputField.dispatchEvent(new Event('input'));
  }

  set change(value: string) {
    this._inputField.value = value;
    this._inputField.dispatchEvent(new Event('change'));
  }

}
