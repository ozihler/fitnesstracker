import {by, element, ElementFinder} from 'protractor';

export class InputField {
  private inputField: ElementFinder;

  constructor(id: string) {
    this.inputField = element(by.id(id));
  }

  enterText(text: string) {
    this.inputField.sendKeys(text);
  }
}
