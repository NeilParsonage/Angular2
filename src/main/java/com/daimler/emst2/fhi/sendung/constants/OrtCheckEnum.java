package com.daimler.emst2.fhi.sendung.constants;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.daimler.emst2.fhi.model.FhiMandantEnum;

public enum OrtCheckEnum implements IOrtCheckDiscriminator {
    LMT_AUSFALL_ERLAUBT_152("LMT_AUSFALL_ERLAUBT"),
    LMT_SENDUNG_ERLAUBT_152("LMT_SENDUNG_ERLAUBT"),
    LMT_STORNO_ERLAUBT_152("LMT_STORNO_ERLAUBT"),

    FHI_AUSFALL_ERLAUBT_152("FHI_AUSFALL_ERLAUBT"),
    FHI_SENDUNG_ERLAUBT_152("FHI_SENDUNG_ERLAUBT"),
    FHI_SENDUNG_ERLAUBT_060("AUFTRAG_FHISENDUNG_01"), // FHI_SENDUNG_ERLAUBT
    FHI_STORNO_ERLAUBT_152("FHI_STORNO_ERLAUBT"),

    RHM_AUSFALL_ERLAUBT_152("RHM_AUSFALL_ERLAUBT"),
    RHM_SENDUNG_ERLAUBT_152("RHM_SENDUNG_ERLAUBT"),
    RHM_SENDUNG_ERLAUBT_060("AUFTRAG_TEILSENDUNG_RHM_01"), // RHM_SENDUNG_ERLAUBT
    RHM_STORNO_ERLAUBT_152("RHM_STORNO_ERLAUBT"),

    UBM_AUSFALL_ERLAUBT_152("UBM_AUSFALL_ERLAUBT"),
    UBM_SENDUNG_ERLAUBT_152("UBM_SENDUNG_ERLAUBT"),
    UBM_STORNO_ERLAUBT_152("UBM_STORNO_ERLAUBT"),
    BANDWECHSEL_ERLAUBT_152("BANDWECHSEL_ERLAUBT"),

    SCHNELLLAEUFER_ERLAUBT_152("SCHNELLLAEUFER_ERLAUBT"),

    UNKNOWN("UNKNOWN");

    private static final Logger LOG = Logger.getLogger(OrtCheckEnum.class.getName());

    private static final String SUFFIX_POSITIV_AUSFALL = "_AUSFALL_ERLAUBT";

    private static final String SUFFIX_POSITIV_STORNO = "_STORNO_ERLAUBT";

    private static final String SUFFIX_POSITIV_SEND = "_SENDUNG_ERLAUBT";

    private static final List<String> sendOrStornoSufixesList = new ArrayList<String>();

    public static final List<String> getSendOrStornoSufixes(String werk) {
        if (FhiMandantEnum.WERK_152.getMandantString().equals(werk)) {
            return getSendOrStornoSufixes152();
        }
        if (FhiMandantEnum.WERK_060.getMandantString().equals(werk)) {
            return getSendOrStornoSufixes060();
        }
        return null;
    }

    public static final List<String> getSendOrStornoSufixes152() {
        if (sendOrStornoSufixesList.isEmpty()) {
            sendOrStornoSufixesList.add(SUFFIX_POSITIV_AUSFALL);
            sendOrStornoSufixesList.add(SUFFIX_POSITIV_STORNO);
            sendOrStornoSufixesList.add(SUFFIX_POSITIV_SEND);
        }
        return sendOrStornoSufixesList;
    }

    public static final List<String> getSendOrStornoSufixes060() {
        if (sendOrStornoSufixesList.isEmpty()) {
            sendOrStornoSufixesList.add("AUFTRAG_%SENDUNG%_01");
            //            sendOrStornoSufixesList.add(SUFFIX_POSITIV_STORNO);
            //            sendOrStornoSufixesList.add(SUFFIX_POSITIV_SEND);
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
            final boolean ignoreIrrelevantEntries = !ortCheckEnum.name().endsWith(filterSuffixOrtCheck);
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
