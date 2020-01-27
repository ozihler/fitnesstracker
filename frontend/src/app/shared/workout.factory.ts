import {WorkoutRaw} from "./workout-raw";
import {Workout} from "./workout";
import {MuscleGroupFactory} from "./muscle-group.factory";
import {Id} from "./id";

export class WorkoutFactory {
  static fromRaw(workoutRaw: WorkoutRaw): Workout {
    return {
      id: Id.from(workoutRaw.id+""),
      title: workoutRaw.title,
      creationDate: new Date(workoutRaw.creationDate)
    }
  }
}
