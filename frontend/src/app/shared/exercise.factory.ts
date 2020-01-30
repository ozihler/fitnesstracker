import {ExerciseRaw} from "./exercise-raw";
import {Exercise} from "./exercise";
import {MuscleGroupFactory} from "./muscle-group.factory";
import {SetFactory} from "./set.factory";

export class ExerciseFactory {
  static from(exerciseRaw: ExerciseRaw): Exercise {
    return new Exercise(exerciseRaw.name, MuscleGroupFactory.from(exerciseRaw.muscleGroup), exerciseRaw.sets.map(set => SetFactory.from(set)));
  }

  static fromMultiple(exercises: ExerciseRaw[]) {
    return exercises.map(exercise => ExerciseFactory.from(exercise));
  }
}
