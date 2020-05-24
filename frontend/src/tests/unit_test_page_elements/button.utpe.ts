export class Button {
  constructor(private button: HTMLButtonElement) {
  }

  get label() {
    return this.button.textContent;
  }

  click() {
    this.button.click();
  }
}
