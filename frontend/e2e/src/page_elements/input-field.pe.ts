import {by, element, ElementFinder} from 'protractor';

export class InputField {
  private inputField: ElementFinder;

  constructor(id: string) {
    this.inputField = element(by.id(id));
  }

  enterText(text: string) {
    return this.inputField.clear()
      .then(() => this.inputField.sendKeys(text));
  }
}
