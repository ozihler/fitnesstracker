export class Span {
  constructor(private span: HTMLSpanElement) {
  }

  get text() {
    return this.span.textContent;
  }
}
