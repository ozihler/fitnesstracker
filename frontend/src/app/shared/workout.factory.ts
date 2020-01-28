import {WorkoutRaw} from "./workout-raw";
import {Workout} from "./workout";
import {GID} from "./gid";
import {MuscleGroupFactory} from "./muscle-group.factory";

export class WorkoutFactory {
  static fromRaw(workoutRaw: WorkoutRaw): Workout {
    return {
      gid: GID.from(workoutRaw.gid ),
      title: workoutRaw.title,
      creationDate: new Date(workoutRaw.creationDate),
      muscleGroups: MuscleGroupFactory.fromMultiple(workoutRaw.muscleGroups)
    }
  }
}
