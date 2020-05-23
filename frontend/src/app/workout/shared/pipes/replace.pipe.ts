import {Pipe, PipeTransform} from '@angular/core';

@Pipe({name: 'replace'})
export class ReplacePipe implements PipeTransform {

  private static escapeString(value) {
    return value.replace(/[-\/\\^$*+?.()|[\]{}]/g, '\\$&');
  }

  transform(value: string, stringToReplace: string, replacement: string): string {
    if (!value) {
      return '';
    }

    if (!stringToReplace || !replacement) {
      return value;
    }

    return value.replace(new RegExp(ReplacePipe.escapeString(stringToReplace), 'g'), replacement);
  }
}
