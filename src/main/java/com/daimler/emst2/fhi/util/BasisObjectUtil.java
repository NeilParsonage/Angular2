package com.daimler.emst2.fhi.util;

import java.util.Collection;
import java.util.Map;

public final class BasisObjectUtil {
    private BasisObjectUtil() {
        //
    }

    public static final boolean areEqual(Object a, Object b) {
        return a == null ? b == null : a.equals(b);
    }

    /**
     * @see com.dcx.common.basis.frw.util.BasisCollectionUtils#isEmptyOrNull(Collection)
     * @see com.dcx.common.basis.frw.util.BasisCollectionUtils#isEmptyOrNull(Map)
     * @see com.dcx.common.basis.frw.util.BasisStringUtils#isEmptyOrNull(String)
     */

    @SuppressWarnings("rawtypes")
    public static final boolean isEmptyOrNull(Object pObj) {
        if (pObj == null) {
            return true;
        }
        if (pObj instanceof String) {
            return BasisStringUtils.isEmptyOrNull((String)pObj);
        }
        if (pObj instanceof Collection) {
            return BasisCollectionUtils.isEmptyOrNull((Collection)pObj);
        }
        if (pObj instanceof Map) {
            return BasisCollectionUtils.isEmptyOrNull((Map)pObj);
        }
        return false;
    }

    public static final boolean isNotEmptyOrNull(Object pObj) {
        return !isEmptyOrNull(pObj);
    }

}