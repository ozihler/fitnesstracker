import {ExerciseRaw} from "./exercise-raw";
import {Exercise} from "./exercise";
import {SetFactory} from "./set.factory";

export class ExerciseFactory {
  static from(exerciseRaw: ExerciseRaw): Exercise {
    let sets = exerciseRaw.sets.map(set => SetFactory.from(set));
    return new Exercise(undefined, exerciseRaw.name, sets);
  }

  static fromMultiple(exercises: ExerciseRaw[]) {
    return exercises ? exercises.map(exercise => ExerciseFactory.from(exercise)) : [];
  }
}
