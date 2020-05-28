import {Set} from '../../app/workout/shared/set';
import {ReplacePipe} from '../../app/workout/shared/pipes/replace.pipe';

export class ConvertToId {

  static set(set: Set) {
    return new ReplacePipe().transform(set.weight.toString(), '.', '-')
      + '-kg-'
      + set.numberOfRepetitions
      + '--'
      + set.waitingTime
      + '-s';
  }
}
