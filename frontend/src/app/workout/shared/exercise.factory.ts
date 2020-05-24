import {ExerciseRaw} from './exercise-raw';
import {Exercise} from './exercise';
import {SetFactory} from './set.factory';

export class ExerciseFactory {
  static from(exerciseRaw: ExerciseRaw): Exercise {
    const sets = exerciseRaw.sets ? exerciseRaw.sets.map(set => SetFactory.from(set)) : [];
    const exercise = new Exercise(undefined, exerciseRaw.name, sets);
    exercise.multiplier = exerciseRaw.multiplier;
    return exercise;
  }

  static fromMultiple(exercises: ExerciseRaw[]): Exercise[] {
    return exercises ? exercises.map(exercise => ExerciseFactory.from(exercise)) : [];
  }
}
