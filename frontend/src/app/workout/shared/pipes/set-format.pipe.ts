import { Pipe, PipeTransform } from '@angular/core';
import {Set} from '../set';
@Pipe({
  name: 'setFormat'
})
export class SetFormatPipe implements PipeTransform {

  transform(value: Set): string {
    return `${value.weight} ${value.weightUnit}, ${value.numberOfRepetitions} #, ${value.waitingTime} ${value.waitingTimeUnit}`;
  }

}
