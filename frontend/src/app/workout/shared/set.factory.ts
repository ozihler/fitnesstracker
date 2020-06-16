import {SetRaw} from './set-raw';
import {Set} from './set';

export class SetFactory {
  static from(setRaw: SetRaw, index: number, multiplier: number): Set {
    // todo why is parent undefined? I know already who it is.
    return new Set(
      setRaw.weight,
      setRaw.weightUnit,
      setRaw.numberOfRepetitions,
      setRaw.waitingTime,
      setRaw.waitingTimeUnit,
      undefined,
      index,
      multiplier);
  }
}
