export class Mark {
  constructor(private mark: HTMLSpanElement) {
  }

  get text() {
    return this.mark.textContent;
  }
}
