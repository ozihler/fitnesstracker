import {by, element, ElementFinder} from 'protractor';

export class Button {
  private button: ElementFinder;

  constructor(private id: string) {
    this.button = element(by.id(this.id));
  }

  click() {
    return this.button.click();
  }

  visibleTitleText() {
    return this.button.getText();
  }
}
