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
}
