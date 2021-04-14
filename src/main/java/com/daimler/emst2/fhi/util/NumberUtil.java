package com.daimler.emst2.fhi.util;

import org.apache.commons.lang3.StringUtils;

public class NumberUtil {

    public final static Double strToDouble(String strNum) {
        if (StringUtils.isBlank(strNum)) {
            return null;
        }
        String strValue = normalizeDecimal(strNum);
        // TODO validate Number #.###
        return strNum == null ? null : Double.parseDouble(strValue);
    }

    public static Integer strToInteger(String strNum) {
        if (StringUtils.isBlank(strNum)) {
            return null;
        }
        return strNum instanceof String ? Integer.parseInt(strNum) : null;
    }

    public static String doubleToStr(Double num) {
        if (!(num instanceof Double)) {
            return StringUtils.EMPTY;
        }
        final String doubleStr = normalizeDecimal(Double.toString(num));
        return doubleStr.endsWith(".0") ? doubleStr.substring(0, doubleStr.length() - 2) : doubleStr;
    }

    private static String normalizeDecimal(String text) {
        if (!(text instanceof String)) {
            return text;
        }
        return text.replace(",", ".");
    }
}
