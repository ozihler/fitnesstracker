export class By {
  static descendingCreationTime = () => (a, b) => {
    if (a.creationDate > b.creationDate) {
      return -1;
    } else if (b.creationDate > a.creationDate) {
      return 1;
    } else {
      return 0;
    }
  }
}
