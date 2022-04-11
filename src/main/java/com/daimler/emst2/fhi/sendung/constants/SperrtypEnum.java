package com.daimler.emst2.fhi.sendung.constants;

import java.util.Arrays;
import java.util.List;

import com.daimler.emst2.fhi.model.ITuebObject;

public enum SperrtypEnum implements ITuebObject {
    UNDEFINED(" ", "--", "undefined"),
    SPERRE("S", "SP", "sperre"),
    ANKUENDIGUNG("A", "AN", "ankuendigung");

    public static final String DEFAULT_TUEB_PREFIX = "label.sperrtyp.";

    private String typ;

    private String code2Char2;

    private String tuebKey;

    public static final SperrtypEnum getSperrtypEnum(String pSperrtypString) {
        SperrtypEnum[] values = SperrtypEnum.values();
        for (SperrtypEnum sperrtypEnum : values) {
            if (sperrtypEnum.getTyp().equals(pSperrtypString)) {
                return sperrtypEnum;
            }
        }
        return UNDEFINED;
    }

    public static List<SperrtypEnum> getOrderedListForGui() {
        SperrtypEnum[] values = SperrtypEnum.values();
        return Arrays.asList(values);
    }

    private SperrtypEnum(String pTyp, String pCode2Chars, String pTuebKey) {
        typ = pTyp;
        code2Char2 = pCode2Chars;
        tuebKey = pTuebKey;
    }

    public String getTyp() {
        return typ;
    }

    public Object getId() {
        return typ;
    }

    public String getTuebKey() {
        return tuebKey;
    }

    public static SperrtypEnum getDefaultSelection() {
        return UNDEFINED;
    }
    public String getCode2char2() {
        return code2Char2;
    }
}
