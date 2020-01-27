export class Id {
  constructor(private _id: string) {

  }

  get value(): string {
    return this._id;
  }

  static from(id: string) {
    return new Id(id);
  }

  parts() {
    return this.value.split("_")
  }
}
