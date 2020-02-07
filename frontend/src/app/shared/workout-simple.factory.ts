import {WorkoutId} from "./workoutId";
import {WorkoutSimpleRaw} from "./workout-simple-raw";
import {WorkoutsSimpleRaw} from "./workouts-simple-raw";
import {WorkoutSimple} from "./workout-simple";

export class WorkoutSimpleFactory {
  static fromRaw(workoutSimpleRaw: WorkoutSimpleRaw): WorkoutSimple {
    return new WorkoutSimple(
      WorkoutId.from(workoutSimpleRaw.gid),
      workoutSimpleRaw.title,
      new Date(workoutSimpleRaw.creationDate)
    );
  }

  static fromMultiple(workoutsSimpleRaw: WorkoutsSimpleRaw): WorkoutSimple[] {
    return workoutsSimpleRaw.workouts.map(w => WorkoutSimpleFactory.fromRaw(w));
  }
}
