import {ExerciseRaw} from "./exercise-raw";
import {Exercise} from "./exercise";

export class ExerciseFactory {
  static from(exerciseRaw: ExerciseRaw): Exercise {
    return {
      ...exerciseRaw
    };
  }
}
