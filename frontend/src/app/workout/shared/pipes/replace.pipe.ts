import {Pipe, PipeTransform} from "@angular/core";

@Pipe({name: 'replace'})
export class ReplacePipe implements PipeTransform {
  transform(value: string, stringToReplace: string, replacement: string): string {
    if (!value || !stringToReplace || !replacement) {
      return value;
    }

    return value.replace(new RegExp(this.escapeString(stringToReplace), 'g'), replacement);
  }

  private escapeString(value) {
    return value.replace(/[-\/\\^$*+?.()|[\]{}]/g, '\\$&');
  }
}
