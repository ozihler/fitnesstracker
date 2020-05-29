import {Set} from './set';
import {ReplacePipe} from './pipes/replace.pipe';

export class ElementsToId {
  static replace(input: string) {
    return input.replace(/#+/g, '')
      .replace(/[\s\,\.]+/g, '-')
      .toLowerCase();
  }

  static formatSet(set: Set) {
    return new ReplacePipe().transform(set.weight.toString(), '.', '-')
      + '-kg-'
      + set.numberOfRepetitions
      + '-'
      + set.waitingTime
      + '-s';
  }

}
