package com.daimler.emst2.fhi.sendung.constants;

import com.daimler.emst2.fhi.model.ITuebObject;

public enum SperrHerkunftEnum implements ITuebObject {
    UNDEFINED("", "undefined"),
    ASP("ASP", "asp"),
    FHI("FHI", "fhi"),
    OLD("OLD", "old"),
    HOST("HOST", "host");

    public static final String DEFAULT_TUEB_PREFIX = "label.sperrherkunft.";

    private String code;

    private String tuebKey;

    private SperrHerkunftEnum(String pCode, String pTuebKey) {
        code = pCode;
        tuebKey = pTuebKey;
    }

    // alle ausser FHI sind ausserhalb des Systems zu pflegen
    // FHI-798: die Sperren (FHI, HOST, ASP) dürfen gelöscht werden
    public boolean isReadonly() {
        if (FHI.equals(this) || HOST.equals(this) || ASP.equals(this)) {
            return false;
        }
        return true;
    }

    public Object getId() {
        return code;
    }

    public String getDbRepresentation() {
        return code;
    }

    public String getTuebKey() {
        return tuebKey;
    }

    public String getDefaultTuebKey() {
        return DEFAULT_TUEB_PREFIX + tuebKey;
    }

    public static SperrHerkunftEnum getSperrHerkunftEnum(String pSperreHerkunft) {
        SperrHerkunftEnum[] values = SperrHerkunftEnum.values();
        for (SperrHerkunftEnum sperrHerkunftEnum : values) {
            if (sperrHerkunftEnum.getId().equals(pSperreHerkunft)) {
                return sperrHerkunftEnum;
            }
        }
        return UNDEFINED;
    }
}
