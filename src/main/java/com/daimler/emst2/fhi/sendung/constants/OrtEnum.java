package com.daimler.emst2.fhi.sendung.constants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.daimler.emst2.fhi.model.ITuebObject;


public enum OrtEnum implements ITuebObject {

    QUER("QUER");

    private static final Logger LOG = Logger.getLogger(OrtEnum.class.getName());

    public static final String DEFAULT_TUEB_PREFIX = "label.";

    private static final Map<String, OrtEnum> factoryMap = new HashMap<String, OrtEnum>();

    public static final OrtEnum getOrtTypEnum(String pOrtTypString) {
        if (!factoryMap.containsKey(pOrtTypString)) {
            OrtEnum[] values = OrtEnum.values();
            for (OrtEnum ortTypEnum : values) {
                if (ortTypEnum.getTyp().equals(pOrtTypString)) {
                    factoryMap.put(pOrtTypString, ortTypEnum);
                    break;
                }
            }
        }
        if (!factoryMap.containsKey(pOrtTypString)) {
            LOG.warning("No valid OrtTypEnum found for value: " + pOrtTypString);
        }
        return factoryMap.get(pOrtTypString);
    }

    public static List<OrtEnum> getOrderedListForGui() {
        OrtEnum[] values = OrtEnum.values();
        return Arrays.asList(values);
    }

    private String typ;

    private String tuebKey;

    private OrtEnum(String pTyp) {
        typ = pTyp;
        tuebKey = pTyp.toLowerCase();
    }

    public String getTyp() {
        return typ;
    }

    @Override
    public Object getId() {
        return getTuebKey();
    }

    @Override
    public String getTuebKey() {
        return tuebKey;
    }

    public boolean isQuer() {
        return this.equals(QUER);
    }

    public OrtEnum toggledValue() {
        if (isQuer()) {
            return QUER;
        }
        return null;
    }
}
