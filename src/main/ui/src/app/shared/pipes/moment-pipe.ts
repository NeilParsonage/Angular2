import { Pipe, PipeTransform } from '@angular/core';
import * as moment from 'moment';

@Pipe({ name: 'dateFormat' })
export class MomentPipe implements PipeTransform {
    transform(value: string | Date | moment.Moment, dateFormat: string): any {
        if (!value) {
          return '';
        }
        // fixme - get locale from app ctx
        return moment(value).locale('de-DE').format(dateFormat);
    }
}
