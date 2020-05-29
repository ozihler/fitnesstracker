export class ElementsToId {
  static replace(input: string) {
    if (!input) {
      return 'root';
    }
    return input.replace(/#+/g, '')
      .replace(/[\s\,\.]+/g, '-')
      .toLowerCase();
  }
}
