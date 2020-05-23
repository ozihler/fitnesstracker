export class InputField {
  constructor(private inputField: HTMLInputElement) {
  }

  set text(value: string) {
    this.inputField.value = value;
    this.inputField.dispatchEvent(new Event('input'));
  }
}
