export class Link {
  constructor(private link: HTMLLinkElement) {
  }

  follow() {
    this.link.click();
  }
}
