import {Pipe, PipeTransform} from '@angular/core';
import {Set} from '../set';

@Pipe({
  name: 'setFormat'
})
export class SetFormatPipe implements PipeTransform {

  transform(value: Set): string {
    return (value.weight ? value.weight + ' ' + (value.weightUnit ? value.weightUnit : 'kg') : '')
      + (value.weight && value.numberOfRepetitions ? ', ' : '') +
      (value.numberOfRepetitions ? value.numberOfRepetitions + ' #' : '')
      + ((value.weight && value.waitingTime) || (value.numberOfRepetitions && value.waitingTime) ? ', ' : '') +
      (value.waitingTime ? value.waitingTime + ' ' + (value.waitingTimeUnit ? value.waitingTimeUnit : 's') : '');
  }

}
