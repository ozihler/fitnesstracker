import {ExerciseRaw} from "./exercise-raw";
import {Exercise} from "./exercise";
import {SetFactory} from "./set.factory";

export class ExerciseFactory {
  static from(exerciseRaw: ExerciseRaw): Exercise {
    console.log("Received exercise raw: ", exerciseRaw);
    let sets = exerciseRaw.sets ? exerciseRaw.sets.map(set => SetFactory.from(set)) : [];
    let exercise = new Exercise(undefined, exerciseRaw.name, sets);
    exercise.multiplier = exerciseRaw.multiplier;
    console.log("Exercise X1: ", exercise);
    return exercise;
  }

  static fromMultiple(exercises: ExerciseRaw[]) {
    return exercises ? exercises.map(exercise => ExerciseFactory.from(exercise)) : [];
  }
}
