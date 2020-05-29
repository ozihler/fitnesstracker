export class ElementsToId {

  // todo get rid of this and use treeNode.id consistently to avoid duplication
  static replace(input: string) {
    if (!input) {
      return 'root';
    }
    return input.replace(/#+/g, '')
      .replace(/[\s\,\.]+/g, '-')
      .toLowerCase();
  }
}
