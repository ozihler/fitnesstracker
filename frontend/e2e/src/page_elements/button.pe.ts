import {by, element, ElementFinder} from 'protractor';

export class Button {
  private button: ElementFinder;

  constructor(id: string) {
    this.button = element(by.id(id));
  }

  click() {
    return this.button.click();
  }

  visibleTitleText() {
    return this.button.getText();
  }

  classes() {
    return this.button.getAttribute('class');
  }

  clickIfUnselected() {
    return this.classes()
      .then(c => {
        if (c && c.indexOf('uk-button-danger') < 0) {
          this.button.click();
        }
      });
  }
}
