export class Button {
  constructor(private button: HTMLButtonElement) {
  }

  get label() {
    return this.button.textContent;
  }

  click() {
    return this.button.click();
  }
}
