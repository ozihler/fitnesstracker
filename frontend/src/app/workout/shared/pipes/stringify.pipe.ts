import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'stringify'
})
export class StringifyPipePipe implements PipeTransform {
  transform(value: any): string {
    return (value ? value.toString() : '');
  }
}
