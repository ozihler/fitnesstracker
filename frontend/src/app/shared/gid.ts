export class GID {
  constructor(private _gid: number) {

  }

  get value(): number {
    return this._gid;
  }

  static from(gid: number) {
    return new GID(gid);
  }
}
