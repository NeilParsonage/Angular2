import { Moment, MomentCreationData } from 'moment';
import * as moment from 'moment';



export class DateHelper {

  static addMinutesToDate(date: string | Date | Moment, minutes: number): string | Date | Moment {
    const dateA = DateHelper.normalizeDate(date).clone();
    return dateA.add(minutes, "m");
  }

  static formatDateOnlyByLocale(date: string | Date | Moment) {
    if (!date) {
      return '';
    }
    const _date = DateHelper.normalizeDate(date)
    const localeFormat: string = _date.localeData().longDateFormat('L');
    return _date.format(localeFormat);
  }

  static toMinutes(date: string | Date | Moment): number {
    const _date = DateHelper.normalizeDate(date);
    return _date.minutes() + _date.hours()*60;
  }

  static isSameDay(day: (string|Date|Moment), day2: (string|Date|Moment)): boolean {
    if (!day || !day2) {
      return false;
    }
    const dayA = DateHelper.removeTime(DateHelper.normalizeDate(day));
    const dayB = DateHelper.removeTime(DateHelper.normalizeDate(day2));
    return dayA.isSame(dayB);
  }

  static getIsoDateYYYYMMDDAsString(datum: string | Date | Moment): string {
    const myDate = this.normalizeDate(datum);
    return myDate.format('YYYYMMDD');
  }

  static normalizeDate(myDate:(string|Date|Moment)): Moment {
    if (moment.isDate(myDate)) {
      return moment(myDate);
    }
    if (moment.isMoment(myDate)) {
      return myDate;
    }
    return moment(myDate); // .utcOffset(0, true);
  }

  static clone(myDate:(string|Date|Moment)): Moment {
    const date: Moment = DateHelper.normalizeDate(myDate);
    return moment(date.format('YYYY-MM-DDTHH:mm:ss'));
    //return moment(DateHelper.normalizeDate(myDate).format('YYYY-MM-DDTHH:mm:ss')); // .utcOffset(0, true);
  }

  static nowWithoutSeconds(): Moment {
    let newMoment = moment();
    newMoment.set("s",0);
    newMoment.set("ms",0);
    return newMoment;
  }

  static nowWithoutTime(): Moment {
    return DateHelper.removeTime(moment());
  }

  static removeTime(myDate:(string|Date|Moment)): Moment {
    let modDate = DateHelper.clone(DateHelper.normalizeDate(myDate));
    modDate.set("h", 0);
    modDate.set("m", 0);
    modDate.set("s", 0);
    modDate.set("ms", 0);
    return modDate;
  }

  static toStrDateOnly(myDate:(string|Date|Moment)) {
    if (!myDate)  {
      return "";
    }
    const strDate = DateHelper.normalizeDate(myDate).toISOString();
    if (!strDate) {
      console.warn("DateHelper::strDateOnly not supported date detected ", myDate);
      return "";
    }
    return strDate.substr(0,10);
  }

  static toStrYearMonthOnly(myDate:(string|Date|Moment)) {
    const date: Moment = DateHelper.normalizeDate(myDate);
    return date.format('YYYY') + date.format('MM') + '';
  }

  static toStrDateAndTime(myDate:(string|Date|Moment)) {
    const date: Moment = DateHelper.normalizeDate(myDate);
    return date.format('YYYY-MM-DDTHH:mm:ss');
  }

  static diffByRelativeDateTimeInMinutes(dateA:(string|Date|Moment), dateB:(string|Date|Moment)): number {
    if (!dateA || !dateB) {
      return 0;
    }
    const fullDateA: Moment = this.toFixedDate1980_01_01(this.clone(DateHelper.normalizeDate(dateA)));
    let fullDateB: Moment = this.toFixedDate1980_01_01(this.clone(DateHelper.normalizeDate(dateB)));
    if (fullDateB.isBefore(fullDateA)) {
      fullDateB = this.toNextDay(fullDateB);
    }
    console.log('diffByRelativeDateTimeInMinutes', fullDateA, fullDateB);
    return fullDateB.diff(fullDateA, "minutes");
  }

  static diffAbsolutDateTimeInMinutes(from:(string|Date|Moment), to:(string|Date|Moment)): number {
    if (!from || !to) {
      return 0;
    }
    const fullDateA: Moment = this.toFixedDate1980_01_01(this.clone(DateHelper.normalizeDate(from)));
    let fullDateB: Moment = this.toFixedDate1980_01_01(this.clone(DateHelper.normalizeDate(to)));
    console.log('diffAbsolutDateTimeInMinutes', fullDateA, fullDateB);
    return fullDateB.diff(fullDateA, "minutes");
  }


  static diffDateTimeInMinutes(from:(string|Date|Moment), to:(string|Date|Moment)): number {
    if (!from || !to) {
      return 0;
    }
    const fullDateA: Moment = this.clone(DateHelper.normalizeDate(from));
    let fullDateB: Moment = this.clone(DateHelper.normalizeDate(to));
    console.log('diffDateTimeInMinutes', fullDateA, fullDateB);
    return fullDateB.diff(fullDateA, "minutes");
  }

  static toTimeStrHHMM(pDate: (string|Date|Moment)): string {
    const date = this.normalizeDate(pDate);
    return date.format('HH:mm');
  }

  static setTimeStrHHMM(pDate: (string|Date|Moment), setTime: string): Moment {
    let newDate = this.normalizeDate(pDate);

    const dateTime = moment(setTime,'HH:mm');
    console.debug('parse HH:mm src=' + setTime + ' result=',  dateTime);
    const hours = dateTime.get('hours');
    const minutes = dateTime.get('minutes');

    newDate.set('h', hours);
    newDate.set('m', minutes);
    newDate.set('s',0);
    newDate.set('ms',0);
    return newDate;
  }

  public static toFixedDate1980_01_01(pDate: Moment): Moment {
    let date: Moment = pDate;
    console.log('toFixedDate1980_01_01 before', date.toISOString());
    date = date.set("year", 1980);
    date = date.set("month",1);
    date = date.set("D", 1);
    console.log('toFixedDate1980_01_01 after', date.toISOString());
    return date;
  }

  public static toNextDay(pDate: Moment): Moment {
    let date: Moment = pDate;
    console.log('toNextDay before', date.toISOString());
    date = date.add(1,"days");
    console.log('toNextDay after', date.toISOString());
    return date;
  }

  static primengLocale(locale:string): any {

    moment.locale(locale);

    var primeLocale = {
      firstDayOfWeek: moment().weekday(0).day(), // 1,
      dayNames: moment.weekdays(), // ['Sonntag', 'Montag', 'Dienstag', 'Mittwoch', 'Donnerstag', 'Freitag', 'Samstag'],
      dayNamesShort: moment.weekdaysShort(), //['Son','Mon','Die','Mit','Don','Fre','Sam'],
      dayNamesMin: moment.weekdaysMin(), // ['S','M','D','M','D','F','S'],
      monthNames: moment.months(),
      monthNamesShort: moment.monthsShort(),
      // today: 'Heute',
      // clear: 'Clear',
      weekHeader: 'KW'
    }
    console.log(primeLocale);
    return primeLocale;

  }

}
