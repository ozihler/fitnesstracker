import {WorkoutId} from "./workoutId";

export class WorkoutSimple {
  constructor(private _gid: WorkoutId, private  _title: string, private _creationDate: Date) {

  }


  get gid(): WorkoutId {
    return this._gid;
  }

  get title(): string {
    return this._title;
  }

  get creationDate(): Date {
    return this._creationDate;
  }
}
