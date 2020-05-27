export class Button {
  constructor(private button: HTMLButtonElement) {
  }

  get label() {
    return this.button.textContent;
  }

  get hidden() {
    return this.button.hidden;
  }

  click() {
    this.button.click();
  }
}
