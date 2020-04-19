export class WorkoutId {
  constructor(private _gid: string) {

  }

  get value(): string {
    return this._gid;
  }

  static from(gid: string) {
    return new WorkoutId(gid);
  }
}
