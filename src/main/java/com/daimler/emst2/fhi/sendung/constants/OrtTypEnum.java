package com.daimler.emst2.fhi.sendung.constants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.daimler.emst2.fhi.model.ITuebObject;


public enum OrtTypEnum implements ITuebObject {

    FHS("FHS"), RHM("RHM");

    private static final Logger LOG = Logger.getLogger(OrtTypEnum.class.getName());

    public static final String DEFAULT_TUEB_PREFIX = "label.";

    private static final Map<String, OrtTypEnum> factoryMap = new HashMap<String, OrtTypEnum>();

    public static final OrtTypEnum getOrtTypEnum(String pOrtTypString) {
        if (!factoryMap.containsKey(pOrtTypString)) {
            OrtTypEnum[] values = OrtTypEnum.values();
            for (OrtTypEnum ortTypEnum : values) {
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

    public static List<OrtTypEnum> getOrderedListForGui() {
        OrtTypEnum[] values = OrtTypEnum.values();
        return Arrays.asList(values);
    }

    private String typ;

    private String tuebKey;

    private OrtTypEnum(String pTyp) {
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

    public boolean isFhs() {
        return this.equals(FHS);
    }

    public boolean isRhm() {
        return this.equals(RHM);
    }

    public OrtTypEnum toggledValue() {
        if (isFhs()) {
            return RHM;
        }
        return FHS;
    }
}
