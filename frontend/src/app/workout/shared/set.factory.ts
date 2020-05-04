import {SetRaw} from "./set-raw";
import {Set} from "./set";

export class SetFactory {
  static from(setRaw: SetRaw): Set {
    return new Set(
      setRaw.weight,
      setRaw.weightUnit,
      setRaw.numberOfRepetitions,
      setRaw.waitingTime,
      setRaw.waitingTimeUnit,
      undefined);
  }
}