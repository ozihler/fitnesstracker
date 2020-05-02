import {WorkoutRaw} from "./workout-raw";
import {Workout} from "./workout";
import {WorkoutId} from "./workoutId";
import {MuscleGroupFactory} from "./muscle-group.factory";

export class WorkoutFactory {
  static fromRaw(workoutRaw: WorkoutRaw): Workout {
    let muscleGroups = MuscleGroupFactory.fromMultiple(workoutRaw.muscleGroups);

    return new Workout(
      WorkoutId.from(workoutRaw.workoutId),
      new Date(workoutRaw.creationDate),
      workoutRaw.title,
      muscleGroups);
  }
}
