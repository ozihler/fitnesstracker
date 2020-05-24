import {ReplacePipe} from '../../app/workout/shared/pipes/replace.pipe';

export class ElementsToId {
  static replace(input: string, stringToReplace: string = ' ', replacement: string = '-') {
    return new ReplacePipe().transform(input, stringToReplace, replacement).toLowerCase();
  }
}
