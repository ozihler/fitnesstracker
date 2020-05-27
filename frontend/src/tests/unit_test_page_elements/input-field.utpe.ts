export class InputField {
  constructor(private _inputField: HTMLInputElement) {
  }

  get text(): string {
    return this._inputField.value;
  }

  set text(value: string) {
    this._inputField.value = value;
    this._inputField.dispatchEvent(new Event('input'));
  }
}
