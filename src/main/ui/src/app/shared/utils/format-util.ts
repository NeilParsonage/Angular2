import { DatePipe } from '@angular/common';

export class FormatUtil {
  constructor(public datePipe: DatePipe) {}

  public static formatBand(band: string) {
    if (!band) {
      return '';
    }

    if (band.charAt(0) === '0') {
      return band.substring(1, band.length);
    }

    return band;
  }

  public static formatDate(date) {
    return new DatePipe('en_DE').transform(date, 'dd.MM.yyyy HH:mm:ss');
  }

  public static formatDateWithFormat(date, format) {
    return new DatePipe('en_DE').transform(date, format);
  }
}
