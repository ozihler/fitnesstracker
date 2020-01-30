import {WorkoutRaw} from "./workout-raw";
import {Workout} from "./workout";
import {GID} from "./gid";
import {MuscleGroupFactory} from "./muscle-group.factory";

export class WorkoutFactory {
  static fromRaw(workoutRaw: WorkoutRaw): Workout {
    return new Workout(
      GID.from(workoutRaw.gid),
      new Date(workoutRaw.creationDate),
      workoutRaw.title,
      MuscleGroupFactory.fromMultiple(workoutRaw.muscleGroups));
  }
}
