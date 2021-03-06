import {ExerciseRaw} from './exercise-raw';
import {Exercise} from './exercise';
import {SetFactory} from './set.factory';

export class ExerciseFactory {
  static from(exerciseRaw: ExerciseRaw): Exercise {
    const sets = [];
    const setRaws = exerciseRaw.sets;
    if (setRaws) {
      for (let i = 0; i < setRaws.length; ++i) {
        sets.push(SetFactory.from(setRaws[i], i, exerciseRaw.multiplier));
      }
    }
    return new Exercise(undefined, exerciseRaw.name, sets, exerciseRaw.multiplier);
  }

  static fromMultiple(exercises: ExerciseRaw[]): Exercise[] {
    return exercises ? exercises.map(exercise => ExerciseFactory.from(exercise)) : [];
  }
}
