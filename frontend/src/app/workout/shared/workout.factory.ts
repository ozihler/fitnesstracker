import {WorkoutRaw} from './workout-raw';
import {Workout} from './workout';
import {WorkoutId} from './workout-id';
import {MuscleGroupFactory} from './muscle-group.factory';
import {MuscleGroup} from "./muscle-group";

export class WorkoutFactory {
  static fromRaw(workoutRaw: WorkoutRaw): Workout {
    const muscleGroups = MuscleGroupFactory.fromMultiple(workoutRaw.muscleGroups);

    let workoutId = WorkoutId.from(workoutRaw.workoutId);
    return new Workout(
      workoutId,
      new Date(workoutRaw.creationDate),
      this.createTitle(muscleGroups),
      muscleGroups);
  }

  private static createTitle(muscleGroups: MuscleGroup[]) {
    if (muscleGroups && muscleGroups.length > 0) {
      return muscleGroups.map(m => m.name).join(' ');
    }
    return Workout.WORKOUT_INITIAL_TITLE;
  }
}
