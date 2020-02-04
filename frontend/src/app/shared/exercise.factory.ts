import {ExerciseRaw} from "./exercise-raw";
import {Exercise} from "./exercise";
import {MuscleGroupFactory} from "./muscle-group.factory";
import {SetFactory} from "./set.factory";

export class ExerciseFactory {
  static from(exerciseRaw: ExerciseRaw): Exercise {
    let muscleGroup = MuscleGroupFactory.from(exerciseRaw.muscleGroup);

    let exercise = new Exercise(exerciseRaw.name, muscleGroup, []);

    exercise.children = exerciseRaw.sets.map(set => SetFactory.from(set, exercise));

    return exercise;
  }

  static fromMultiple(exercises: ExerciseRaw[]) {
    return exercises ? exercises.map(exercise => ExerciseFactory.from(exercise)) : [];
  }
}
