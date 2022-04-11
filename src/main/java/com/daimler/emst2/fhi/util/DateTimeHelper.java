package com.daimler.emst2.fhi.util;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.time.FastDateFormat;

public final class DateTimeHelper {

    public static final String DEFAULT_JAVA_DATE_TIME_FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";

    private static final String DB_DATE_FORMAT_STRING = "YYYY-MM-DD HH24:MI:SS";

    private static final String JAVA_DATE_FORMAT_STRING = DEFAULT_JAVA_DATE_TIME_FORMAT_STRING;

    public static final int MILLIS_PER_SEK = 1000;

    public static final int SEKS_PER_MINUTE = 60;

    public static final int MINUTES_PER_HOUR = 60;

    private static final int HOURS_PER_DAY = 24;

    public static final int MILLIS_PER_MINUTE = SEKS_PER_MINUTE * MILLIS_PER_SEK;

    public static final int MILLIS_PER_HOUR = MILLIS_PER_MINUTE * MINUTES_PER_HOUR;

    public static final int MILLIS_PER_DAY = MILLIS_PER_HOUR * HOURS_PER_DAY;

    private static final String TF_8_NULL = "--:--:--";

    private static final String DF_LONG_NULL = "....-..-.. --:--:--";

    private static final String DF_LONG_NULL_DEF = "..-..-.... --:--";

    private static final String DF_6_TF_5_NULL = " - "; // "dd.MM. HH:mm";

    private static final Format DF_6_TF_5 = FastDateFormat.getInstance("dd.MM. HH:mm");

    private static final String DF_8_TF_5_NULL = " - "; // "dd.MM.yy HH:mm";

    private static final Format TF_8 = FastDateFormat.getInstance("HH:mm:ss");

    private static final Format DF_8_TF_5 = FastDateFormat.getInstance("dd.MM.yy HH:mm");

    private static final Format DF_10 = FastDateFormat.getInstance("yyyy-MM-dd");

    private static final Format DF_LONG = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    private static final Format DF_DB_SQL_LONG = FastDateFormat.getInstance(JAVA_DATE_FORMAT_STRING);

    private static final Format DF_TELEGRAMME = FastDateFormat.getInstance("dd.MM.yyyy.HH.mm.ss");

    private static final String DEFAULT_PATTERN = "dd.MM.yy HH:mm";

    private static Date mMAXDATE;

    private static final int TIMEONLY_WITHOUT_SECONDS = 1;

    private static final int TIMEONLY_COMPLETE = 0;

    static {
        try {
            mMAXDATE = new SimpleDateFormat("yyyy-MM-dd").parse("9999-12-31");
        } catch (ParseException e) {
            // kann nicht auftreten
            e.printStackTrace();
        }

    }

    public DateTimeHelper() {
    }

    public static final Date getAktuellesDatumOhneUhrzeit() {
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        Date currentDate = cal.getTime();

        return currentDate;
    }

    public static final Date getAktuellesDatum() {
        return new Date();
    }

    public static final String getAktuellesDatumAlsString() {
        return DF_10.format(getAktuellesDatum());
    }

    public static final Date getMaxDate() {
        // new Date Instanz, da Date nicht final
        Date d = new Date();
        d.setTime(mMAXDATE.getTime());
        return d;
    }

    /**
     * Addiert die übergebenen Millis auf das übergebene Datum drauf und
     * verändert es damit!
     * 
     * @param d1
     * @param pMillis
     */
    public static final void addMillisToDate(Date d1, long pMillis) {
        if ((d1 != null) && (pMillis != 0)) {
            long time = d1.getTime();
            time += pMillis;
            d1.setTime(time);
        }
    }

    /**
     * <B>ACHTUNG: Ist nur geeignet, für ganzzahlige Teiler einer Minute - also
     * z.B. 15000 Millisekunden. (4 Cache-Dates pro Minute: 00s, 15s, 30s, 45s)</B><br>
     * Liefert den nächsten Zeitpunkt in der Zukunft, der auf die entsprechende
     * gerundete Zeit fällt. Kann verwendet werden, um einen Cache-Mechanismus
     * zu implementieren. Damit verlieren alle Elemente im Cache immer
     * gleichzeitig ihre Gültigkeit. (Evtl. besser als immer eine feste
     * Lebensdauer pro gespeichertem Element...). <BR>
     * Notwendig für die Synchronisierung mehrerer Caches und der enthaltenen
     * Elemente.
     * 
     * @param pAktuelleZeit
     * @param pCacheRefreshRateInMillis
     * @return
     */
    public static final Date getNextSekundenCacheGueltigkeitEnde(Date pAktuelleZeit, long pCacheRefreshRateInMillis) {
        Date result = new Date();
        long cacheZeit = 0;
        long aktZeitLong = pAktuelleZeit.getTime();
        long base = aktZeitLong / MILLIS_PER_MINUTE;
        long offset = aktZeitLong % MILLIS_PER_MINUTE;

        offset = offset / pCacheRefreshRateInMillis;
        offset = (offset + 1) * pCacheRefreshRateInMillis;

        cacheZeit = base * MILLIS_PER_MINUTE;
        cacheZeit += offset;

        result.setTime(cacheZeit);
        return result;
    }

    /**
     * Liefert true, wenn das uebergebene innerhalb einer Toleranzgrenze von einer Minute zum
     * aktuellen Zeitpunkt liegt.
     * @param d1
     * @return
     */
    public static final boolean isNearlyNow(Date d1) {
        return DateTimeHelper.diffDates(getAktuellesDatum(), d1) < (MILLIS_PER_SEK * SEKS_PER_MINUTE);
    }

    /**
     * Liefert die absolute Differenz der beiden übergebenen Datumswerte in
     * Millisekunden.
     * 
     * @param d1
     * @param d2
     * @return
     */
    public static final long diffDates(Date d1, Date d2) {
        if ((d1 == null) && (d2 == null)) {
            return 0;
        }
        if (d1 == null) {
            return d2.getTime();
        }
        if (d2 == null) {
            return d1.getTime();
        }
        return Math.abs(d1.getTime() - d2.getTime());
    }

    /**
     * True, wenn d1 > d2 oder <br>
     * d1 != null && d2 == null
     * 
     * @param d1
     * @param d2
     * @return
     */
    public static final boolean greaterThan(Date d1, Date d2) {
        if (d1 == null) {
            return false;
        }
        if (d2 == null) {
            return true;
        }
        return ((d1.getTime() - d2.getTime()) > 0);
    }

    /**
     * True, wenn d1 < d2 oder <br>
     * d2 != null && d1 == null
     * 
     * @param d1
     * @param d2
     * @return
     */
    public static final boolean lessThan(Date d1, Date d2) {
        if (d2 == null) {
            return false;
        }
        if (d1 == null) {
            return true;
        }
        return ((d1.getTime() - d2.getTime()) < 0);
    }

    public static final String getTimeOnlyString(long timeInMillis) {
        return getTimeOnlyString(timeInMillis, TIMEONLY_COMPLETE);
    }

    public static final String getTimeOnlyStringWithoutSeconds(long timeInMillis) {
        return getTimeOnlyString(timeInMillis, TIMEONLY_WITHOUT_SECONDS);
    }

    /**
     * 
     * @param timeInMillis
     * @return time part of the passed Date in seconds. Millis will be cut off.
     */
    public static final long getTimeOnlyInSeconds(long timeInMillis) {

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timeInMillis);

        int hours = cal.get(Calendar.HOUR_OF_DAY);
        int minutes = cal.get(Calendar.MINUTE);
        int seconds = cal.get(Calendar.SECOND);

        return (hours * MINUTES_PER_HOUR + minutes) * SEKS_PER_MINUTE + seconds;
    }

    private static final String getTimeOnlyString(long timeInMillis, int renderMode) {
        boolean hideSeconds = renderMode == TIMEONLY_WITHOUT_SECONDS;
        // no millis...
        long base = timeInMillis / MILLIS_PER_SEK;
        long seks = base % SEKS_PER_MINUTE;
        base = base / SEKS_PER_MINUTE;

        long min = base % MINUTES_PER_HOUR;
        base = base / MINUTES_PER_HOUR;

        long hours = base % HOURS_PER_DAY;
        base = base / HOURS_PER_DAY;

        long days = base;

        StringBuffer sb = new StringBuffer();
        if (days > 0) {
            sb.append(days);
            sb.append("d ");
        }
        if (hours > 0) {
            sb.append(hours);
            sb.append("h ");
        }
        if (min > 0) {
            sb.append(min);
            sb.append("m");
            if (!hideSeconds) {
                sb.append(" ");
            }
        }
        if (!hideSeconds && seks > 0) {
            sb.append(seks);
            sb.append("s");
        }
        return sb.toString();
    }

    public static final String getTimeTF8(Date d) {
        if (d == null) {
            return TF_8_NULL;
        }
        return TF_8.format(d);
    }

    public static final String getDateTimeLong(Date d) {
        if (d == null) {
            return DF_LONG_NULL;
        }
        return DF_LONG.format(d);
    }

    public static final String getDateTimeLongDef(Date d, String pattern) {
        if (d == null) {
            return DF_LONG_NULL_DEF;
        }
        if ("".equals(pattern) || (pattern == null)) {
            pattern = DEFAULT_PATTERN;
        }
        DateFormat df = new SimpleDateFormat(pattern);
        String date = df.format(d);
        return date;
    }

    public static final String getTimeDF8TF5(Date d) {
        if (d == null) {
            return DF_8_TF_5_NULL;
        }
        return DF_8_TF_5.format(d);
    }

    public static final String getTimeDF6TF5(Date d) {
        if (d == null) {
            return DF_6_TF_5_NULL;
        }
        return DF_6_TF_5.format(d);
    }

    public static final String getDateTimeStringInSqlFormat(Date d) {
        if (d == null) {
            throw new NullPointerException("No conversion to SQL-pattern defined for <null> date. ");
        }
        return DF_DB_SQL_LONG.format(d);
    }

    public static final String getDateTimeStringForTelegram(Date pDate) {
        return DF_TELEGRAMME.format(pDate);
    }

    public static final String getDbSqlDateTimeStringFormat() {
        return DB_DATE_FORMAT_STRING;
    }

    public static String getAktuellenSat() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyDDD");
        return sdf.format(getAktuellesDatum());
    }

    /**
     * Konvertiert einen SAT-String in ein Datum als String. Bsp: "2007001" =>
     * "01.01.2007" "2007365" => "31.12.2007"
     */
    public static String convertSatToDatum(String sat) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyDDD");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd.MM.yyyy");
        return sdf2.format(sdf1.parse(sat));
    }

    /**
     * Konvertiert ein Datum-String in einen SAT. Umkehrfunktion zu
     * convertSatToDatum()
     */
    public static String convertDatumToSat(String datum) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyDDD");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd.MM.yyyy");
        return sdf1.format(sdf2.parse(datum));
    }

    /**
     * Konvertiert ein Datum-String in einen SAT. Umkehrfunktion zu
     * convertSatToDatum()
     */
    public static String convertDatumToSatString(Date datum) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyDDD");
        return sdf1.format(datum);
    }

    public static Date convertSatToDatumDate(String sat) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyDDD");
        return sdf1.parse(sat);
    }

    public static Date min(Date date1, Date date2) {
        long d1 = date1 != null ? date1.getTime() : mMAXDATE.getTime();
        long d2 = date2 != null ? date2.getTime() : mMAXDATE.getTime();
        if (d1 < d2) {
            return date1;
        }
        else {
            return date2;
        }
    }

    public static Date max(Date date1, Date date2) {
        long d1 = date1 != null ? date1.getTime() : 0;
        long d2 = date2 != null ? date2.getTime() : 0;
        if (d1 > d2) {
            return date1;
        }
        else {
            return date2;
        }
    }

    public Date completeDateWithCurrentYear(Date pDate) {
        if (pDate == null) {
            return null;
        }
        Calendar auxCalendar = new GregorianCalendar();
        int currentYear = auxCalendar.get(Calendar.YEAR);
        auxCalendar.setTime(pDate);
        auxCalendar.set(Calendar.YEAR, currentYear);
        return auxCalendar.getTime();
    }

    /**
     * Vergleicht zwei Datumswerte. Beim Vergleich wird der zeitliche
     * Anteil ausgeblendet und nur das Datum berücksichtig.
     * <p>
     * Der Vergleich ist nicht schön, doch da Date einen Zeitanteil hat
     * und hier nur der Tag vergliechen werden soll habe ich
     * diese unschöne Art gewählt.
     * <p>
     * Mein Vorschlag hier wäre aus dem Projekt jfreechart die Klassen
     * aus dem package org.jfree.data.time, dann könnte es so aussehen:
     * 
     * <pre>
     *     private int compareDay(Date d1, Date d2) {
     *        Day day1 = new Day(d1);
     *        Day day2 = new Day(d2);
     * 
     *        return day1.compareTo(day2);
     *    }
     * </pre>
     * @return wie Comparable.compareTo(...)
     * @see Comparable#compareTo(Object)
     * @see #clearTime(Calendar)
     * @author bjb
     */
    public int compareDay(Date d1, Date d2) {
        final Calendar d1Calendar = new GregorianCalendar();
        d1Calendar.setTime(d1);
        clearTime(d1Calendar);

        final Calendar d2Calendar = new GregorianCalendar();
        d2Calendar.setTime(d2);
        clearTime(d2Calendar);

        return d1Calendar.compareTo(d2Calendar);
    }

    /**
     * nur intern verwenden
     * @param d1Calendar
     */
    private void clearTime(final Calendar d1Calendar) {
        d1Calendar.set(Calendar.HOUR, 0);
        d1Calendar.set(Calendar.MINUTE, 0);
        d1Calendar.set(Calendar.SECOND, 0);
        d1Calendar.set(Calendar.MILLISECOND, 0);
    }

    /**
     * Merges date and time part of two different date objects to a new date object.
     * @param dateOnly  source of date part - if null current Date will be used
     * @param timeOnly  source of time part - if null 0:00:00 will be used
     */
    public Date mergeDateTime(Date dateOnly, Date timeOnly) {

        // HINT: Avoid using milli calculations as it not considers Timezones

        Calendar result = new GregorianCalendar();

        // prepare date
        Calendar date = new GregorianCalendar();
        if (dateOnly != null) {
            date.setTime(dateOnly);
        }

        // prepare time
        Calendar time = new GregorianCalendar();
        if (timeOnly == null) {
            time.set(Calendar.HOUR_OF_DAY, 0);
            time.set(Calendar.MINUTE, 0);
            time.set(Calendar.SECOND, 0);
            time.set(Calendar.MILLISECOND, 0);
        }
        else {
            time.setTime(timeOnly);
        }

        result.set(Calendar.YEAR, date.get(Calendar.YEAR));
        result.set(Calendar.MONTH, date.get(Calendar.MONTH));
        result.set(Calendar.DATE, date.get(Calendar.DATE));
        result.set(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY));
        result.set(Calendar.MINUTE, time.get(Calendar.MINUTE));
        result.set(Calendar.SECOND, time.get(Calendar.SECOND));
        result.set(Calendar.MILLISECOND, time.get(Calendar.MILLISECOND));

        return result.getTime();
    }

    /**
     * returns true is passed date is older than the current date (including time)
     * @param pDate date to check
     * @return true if expired
     */
    public static boolean isDateExpired(Date pDate) {
        if (BasisObjectUtil.isEmptyOrNull(pDate)) {
            return false;
        }
        return greaterThan(getAktuellesDatum(), pDate);
    }

    /**
     * compares two dates based by its time (millis)
     * @param date1
     * @param date2
     * @return true if both dates are null or both time values are equal
     */
    public static boolean dateEquals(Date date1, Date date2) {
        if (date1 == null && date2 == null) {
            return true;
        }

        if (date1 != null && date2 != null) {
            return date1.getTime() == date2.getTime();
        }

        return false;
    }

    /**
     * removes the time -> will be set to 0:00:00:000
     * @param date
     * @return new date without time
     */
    public static Date truncateTime(Date date) {

        if (date == null) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();

    }

    /**
     * increases time to end of day -> will be set to 23:59:59:999
     * @param date to change
     * @return new date with time set to end of day
     */
    public static Date fillTime(Date date) {

        if (date == null) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();

    }

    public static boolean dateBetween(Date dateToCheck, Date leftBoundary, Date rightBoundary,
            boolean includeRightBoundary, boolean includeLeftBoundary) {

        if (dateToCheck == null || leftBoundary == null || rightBoundary == null) {
            return false;
        }

        Calendar von = Calendar.getInstance();
        von.setTime(leftBoundary);

        Calendar bis = Calendar.getInstance();
        bis.setTime(rightBoundary);

        Calendar date = Calendar.getInstance();
        date.setTime(dateToCheck);

        // compare left
        boolean leftCorrect = false;
        if (includeLeftBoundary) {
            leftCorrect = von.before(date) || von.equals(date);
        }
        else {
            leftCorrect = von.before(date);
        }

        // exit if not correct
        if (!leftCorrect) {
            return false;
        }

        // compare right
        boolean rightCorrect = false;
        if (includeRightBoundary) {
            rightCorrect = bis.after(date) || bis.equals(date);
        }
        else {
            rightCorrect = bis.after(date);
        }

        return rightCorrect;
    }

}
