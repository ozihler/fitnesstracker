import {SetRaw} from "./set-raw";
import {Set} from "./set";
import {Exercise} from "./exercise";
import {GID} from "./gid";

export class SetFactory {
  static from(setRaw: SetRaw, exercise: Exercise): Set {
    return new Set(GID.from(setRaw.gid),
      setRaw.weight,
      setRaw.weightUnit,
      setRaw.numberOfRepetitions,
      setRaw.waitingTime,
      setRaw.waitingTimeUnit,
      exercise);
  }
}
