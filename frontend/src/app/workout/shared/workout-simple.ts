import {WorkoutId} from "./workoutId";

export class WorkoutSimple {
  constructor(private _workoutId: WorkoutId, private  _title: string, private _creationDate: Date) {

  }


  get workoutId(): WorkoutId {
    return this._workoutId;
  }

  get title(): string {
    return this._title;
  }

  get creationDate(): Date {
    return this._creationDate;
  }
}
