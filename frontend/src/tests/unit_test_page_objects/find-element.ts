import {ComponentFixture} from '@angular/core/testing';

export class FindElement {
  constructor(private fixture: ComponentFixture<any>) {
  }

  by(selector: string) {
    return this.fixture.nativeElement.querySelector(selector);
  }

}
