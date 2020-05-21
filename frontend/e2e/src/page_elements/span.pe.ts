import {by, element, ElementFinder} from 'protractor';

export class Span {
  private span: ElementFinder;

  constructor(id: string) {
    this.span = element(by.id(id));
  }

  webElement() {
    return this.span.getWebElement();
  }
}
