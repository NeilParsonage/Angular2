package com.daimler.emst2.fhi.sendung.constants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


public enum BereichEnum {

    LMT("LMT"),
    RHM("RHM");

    private static final Logger LOG = Logger.getLogger(BereichEnum.class.getName());

    public static final String DEFAULT_TUEB_PREFIX = "label.";

    private static final Map<String, BereichEnum> factoryMap = new HashMap<String, BereichEnum>();

    public static final BereichEnum getOrtTypEnum(String bereichTypString) {
        if (!factoryMap.containsKey(bereichTypString)) {
            BereichEnum[] values = BereichEnum.values();
            for (BereichEnum bereichEnum : values) {
                if (bereichEnum.getTyp().equals(bereichTypString)) {
                    factoryMap.put(bereichTypString, bereichEnum);
                    break;
                }
            }
        }
        if (!factoryMap.containsKey(bereichTypString)) {
            LOG.warning("No valid BereichEnum found for value: " + bereichTypString);
        }
        return factoryMap.get(bereichTypString);
    }

    public static List<BereichEnum> getOrderedListForGui() {
        BereichEnum[] values = BereichEnum.values();
        return Arrays.asList(values);
    }

    private String typ;



    private BereichEnum(String pTyp) {
        typ = pTyp;
    }

    public String getTyp() {
        return typ;
    }


    public boolean isLMT() {
        return this.equals(LMT);
    }

    public boolean isRMT() {
        return this.equals(RHM);
    }

}
