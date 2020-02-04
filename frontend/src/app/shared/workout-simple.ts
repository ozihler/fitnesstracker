import {GID} from "./gid";

export class WorkoutSimple {
  constructor(private _gid: GID, private  _title: string, private _creationDate: Date) {

  }


  get gid(): GID {
    return this._gid;
  }

  get title(): string {
    return this._title;
  }

  get creationDate(): Date {
    return this._creationDate;
  }
}
