import {SetRaw} from "./set-raw";
import {Set} from "./set";

export class SetFactory {
  static from(setRaw: SetRaw): Set {
    return {
      ...setRaw
    };
  }
}
