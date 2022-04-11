package com.daimler.emst2.fhi.util;

public final class SimpleDatatypeHelper {

    private SimpleDatatypeHelper() {
        // do not instantiate
    }

    public static int getIntFromInteger(Integer pObj, int pDefault) {
        return (pObj != null) ? pObj.intValue() : pDefault;
    }

    public static int getIntFromInteger(Integer pObj) {
        return getIntFromInteger(pObj, -1);
    }

    public static long getLongFromWrapperObject(Long pObj, long pDefault) {
        return (pObj != null) ? pObj.longValue() : pDefault;
    }

    public static long add(Long pValue1, Long pValue2) {
        long value1 = getLongFromWrapperObject(pValue1, 0);
        long value2 = getLongFromWrapperObject(pValue2, 0);
        return value1 + value2;
    }

    public static int add(Integer pValue1, Integer pValue2) {
        int value1 = getIntFromInteger(pValue1, 0);
        int value2 = getIntFromInteger(pValue2, 0);
        return value1 + value2;
    }
}
