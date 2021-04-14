package com.daimler.emst2.fhi.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.util.ObjectUtils;

public class DateTimeUtil {

    private DateTimeUtil() {
    }

    public static final Date getDateTimeNow() {
        final LocalDateTime localDateTime = LocalDateTime.now();
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static final Long getDiffRelativeTimeOnlyInMinutes(LocalDateTime dateTimeA, LocalDateTime dateTimeB) {
        if (ObjectUtils.isEmpty(dateTimeA) || ObjectUtils.isEmpty(dateTimeB)) {
            return null;
        }
        LocalDateTime dtA = DateTimeUtil.normalizeTo1970_01_01(dateTimeA);
        LocalDateTime dtB = DateTimeUtil.normalizeTo1970_01_01(dateTimeB);

        if (dtB.isBefore(dtA)) {
            dtB = dtB.plusDays(1);
        }

        return ChronoUnit.MINUTES.between(dtA, dtB);
    }

    private static final LocalDateTime normalizeTo1970_01_01(LocalDateTime dateTime) {
        return dateTime.withYear(1970).withMonth(1).withDayOfMonth(1);
    }

}
