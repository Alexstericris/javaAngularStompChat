import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class DateHelper {

  constructor() {
  }

  format(date: string, options?: object): string {
    return (new Date(date)).toLocaleString('default', options);
  }

  formatArr(date: Array<number>) {
    return '' + date[2] + '.' + date[1] + '.' + date[0]
  }
}
