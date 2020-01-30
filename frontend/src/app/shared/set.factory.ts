import {SetRaw} from "./set-raw";
import {Set} from "./set";
import {ExerciseFactory} from "./exercise.factory";

export class SetFactory {
  static from(setRaw: SetRaw): Set {
    return new Set(setRaw.repetitions, setRaw.weight, setRaw.waitingTime, ExerciseFactory.from(setRaw.exercise));
  }
}
