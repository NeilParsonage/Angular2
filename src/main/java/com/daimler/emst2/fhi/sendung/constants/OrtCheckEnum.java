package com.daimler.emst2.fhi.sendung.constants;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public enum OrtCheckEnum implements IOrtCheckDiscriminator {
    LMT_AUSFALL_ERLAUBT_ALL("LMT_AUSFALL_ERLAUBT"),
    LMT_SENDUNG_ERLAUBT_ALL("LMT_SENDUNG_ERLAUBT"),
    LMT_STORNO_ERLAUBT_ALL("LMT_STORNO_ERLAUBT"),

    FHI_AUSFALL_ERLAUBT_ALL("FHI_AUSFALL_ERLAUBT"),
    FHI_SENDUNG_ERLAUBT_ALL("FHI_SENDUNG_ERLAUBT"),
    FHI_STORNO_ERLAUBT_ALL("FHI_STORNO_ERLAUBT"),

    RHM_AUSFALL_ERLAUBT_ALL("RHM_AUSFALL_ERLAUBT"),
    RHM_SENDUNG_ERLAUBT_ALL("RHM_SENDUNG_ERLAUBT"),
    RHM_STORNO_ERLAUBT_ALL("RHM_STORNO_ERLAUBT"),

    UBM_AUSFALL_ERLAUBT_152("UBM_AUSFALL_ERLAUBT"),
    UBM_SENDUNG_ERLAUBT_152("UBM_SENDUNG_ERLAUBT"),
    UBM_STORNO_ERLAUBT_152("UBM_STORNO_ERLAUBT"),
    BANDWECHSEL_ERLAUBT_ALL("BANDWECHSEL_ERLAUBT"),

    SCHNELLLAEUFER_ERLAUBT_152("SCHNELLLAEUFER_ERLAUBT"),

    UNKNOWN("UNKNOWN");

    private static final Logger LOG = Logger.getLogger(OrtCheckEnum.class.getName());

    private static final String SUFFIX_POSITIV_AUSFALL = "_AUSFALL_ERLAUBT";

    private static final String SUFFIX_POSITIV_STORNO = "_STORNO_ERLAUBT";

    private static final String SUFFIX_POSITIV_SEND = "_SENDUNG_ERLAUBT";

    private static final List<String> sendOrStornoSufixesList = new ArrayList<String>();

    public static final List<String> getSendOrStornoSufixes(String werk) {
        return getSendOrStornoSufixes();
    }

    public static final List<String> getSendOrStornoSufixes() {
        if (sendOrStornoSufixesList.isEmpty()) {
            sendOrStornoSufixesList.add(SUFFIX_POSITIV_AUSFALL);
            sendOrStornoSufixesList.add(SUFFIX_POSITIV_STORNO);
            sendOrStornoSufixesList.add(SUFFIX_POSITIV_SEND);
        }
        return sendOrStornoSufixesList;
    }

    //    private static final Map<String, OrtCheckEnum> factoryMap = new HashMap<String, OrtCheckEnum>();
    //
    //    public static final OrtCheckEnum getOrtCheckEnum(String filterSuffixOrtCheck, String pOrtCheckString) {
    //        if (!factoryMap.containsKey(pOrtCheckString)) {
    //            OrtCheckEnum[] values = OrtCheckEnum.values();
    //            for (OrtCheckEnum ortCheckEnum : values) {
    //                final boolean ignoreIrrelevantEntries = !ortCheckEnum.name().endsWith(filterSuffixOrtCheck);
    //                if (ignoreIrrelevantEntries) {
    //                    continue;
    //                }
    //                if (ortCheckEnum.getDiscriminator().equals(pOrtCheckString)) {
    //                    factoryMap.put(pOrtCheckString, ortCheckEnum);
    //                    break;
    //                }
    //            }
    //        }
    //        if (!factoryMap.containsKey(pOrtCheckString)) {
    //            LOG.warning("No valid OrtCheckEnum found for value: " + pOrtCheckString);
    //        }
    //        return factoryMap.get(pOrtCheckString);
    //    }

    /**
     * 
     * @param filterSuffixOrtCheck TODO TD change to mandantEnum
     * @param pOrtCheckString
     * @return
     */
    public static final OrtCheckEnum getOrtCheckEnum(String filterSuffixOrtCheck, String pOrtCheckString) {

        for (OrtCheckEnum ortCheckEnum : OrtCheckEnum.values()) {
            final boolean ignoreIrrelevantEntries =
                    !ortCheckEnum.name().endsWith(filterSuffixOrtCheck) && !ortCheckEnum.name().endsWith("_ALL");
            if (ignoreIrrelevantEntries) {
                continue;
            }
            if (ortCheckEnum.getDiscriminator().equals(pOrtCheckString)) {
                return ortCheckEnum;
            }
        }
        LOG.warning("No valid OrtCheckEnum found for value: " + pOrtCheckString);
        return UNKNOWN;
    }

    private String discriminator;

    private OrtCheckEnum(String pDiscriminator) {
        discriminator = pDiscriminator;
    }

    /* (non-Javadoc)
     * @see com.dcx.common.fhi.constants.IOrtCheckDiscriminator#getDiscriminator()
     */
    @Override
    public String getDiscriminator() {
        return discriminator;
    }

}
