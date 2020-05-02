export class WorkoutId {
  constructor(private _workoutId: string) {

  }

  get value(): string {
    return this._workoutId;
  }

  static from(workoutId: string) {
    return new WorkoutId(workoutId);
  }
}
