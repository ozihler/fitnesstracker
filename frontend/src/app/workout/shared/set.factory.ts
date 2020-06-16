import {SetRaw} from './set-raw';
import {Set} from './set';

export class SetFactory {
  static from(setRaw: SetRaw, index: number, multiplier: number): Set {
    const set = new Set(
      setRaw.weight,
      setRaw.weightUnit,
      setRaw.numberOfRepetitions,
      setRaw.waitingTime,
      setRaw.waitingTimeUnit,
      undefined,
      index);
    set.multiplier = multiplier;
    return set;
  }
}
