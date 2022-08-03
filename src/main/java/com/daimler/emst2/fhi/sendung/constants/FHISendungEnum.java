package com.daimler.emst2.fhi.sendung.constants;

import java.util.logging.Logger;

/*
 * In der DB so definiert
 * FHI-Sendungskennung (0: keine Sendung FHI, 1: Sendung FHI). Kommt aus W6310
 * Satzart AFHI - Sendekennung FHI';
 */

public enum FHISendungEnum {

    KEINE_SENDUNG("0"), SENDUNG("1"), UNDEFINED("9");

    private static final Logger LOG = Logger.getLogger(SendStatusEnum.class.getName());

    private final String dbValue;

    private FHISendungEnum(String dbValue) {
        this.dbValue = dbValue;
    }

    public static final FHISendungEnum getSendStatus(String stringValue) {
        for (FHISendungEnum sendungStatus : FHISendungEnum.values()) {
            if (sendungStatus.getDBValue().equals(stringValue)) {
                return sendungStatus;
            }
        }
        LOG.severe("Undefined Sendung Status detected: "
                   + stringValue
                   + " (continue with status UNDEFINED)");
        return FHISendungEnum.UNDEFINED;
    }

    public String getDBValue() {
        return dbValue;
    }

}
