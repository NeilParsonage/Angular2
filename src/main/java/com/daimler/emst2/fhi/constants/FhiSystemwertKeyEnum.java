package com.daimler.emst2.fhi.constants;

import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;

public enum FhiSystemwertKeyEnum implements ISystemwertKey {

    UNDEFINED("undefined", null),

    ASP_TEILANTRAG_BEARBEITEN_URL("ASP_TEILANTRAG_BEARBEITEN_URL", String.class),
    ASP_OFFENE_ANTRAEGE_URL("ASP_OFFENE_ANTRAEGE_URL", String.class),
    ASP_ANTRAEGE_WIRK_SYSTEM("ASP_ANTRAEGE_WIRK_SYSTEM", String.class),
    ASP_ANZAHL_OFFENE_TEILANTRAEGE("ASP_ANZAHL_OFFENE_TEILANTRAEGE", Long.class),

    LFD_NR_LMT_0("AUFTRAG.LFD.NUMMERN.LMT.0", Long.class),
    LFD_NR_LMT_1("AUFTRAG.LFD.NUMMERN.LMT.1", Long.class),
    LFD_NR_LMT_2("AUFTRAG.LFD.NUMMERN.LMT.2", Long.class),
    LFD_NR_LMT_3("AUFTRAG.LFD.NUMMERN.LMT.3", Long.class),
    LFD_NR_LMT_4("AUFTRAG.LFD.NUMMERN.LMT.4", Long.class),
    LFD_NR_LMT_5("AUFTRAG.LFD.NUMMERN.LMT.5", Long.class),

    LFD_NR_RHM_0("AUFTRAG.LFD.NUMMERN.RHM.0", Long.class),
    LFD_NR_RHM_1("AUFTRAG.LFD.NUMMERN.RHM.1", Long.class),
    LFD_NR_RHM_2("AUFTRAG.LFD.NUMMERN.RHM.2", Long.class),
    LFD_NR_RHM_3("AUFTRAG.LFD.NUMMERN.RHM.3", Long.class),
    LFD_NR_RHM_4("AUFTRAG.LFD.NUMMERN.RHM.4", Long.class),
    LFD_NR_RHM_5("AUFTRAG.LFD.NUMMERN.RHM.5", Long.class),

    LFD_NR_UBM_0("AUFTRAG.LFD.NUMMERN.UBM.0", Long.class),
    LFD_NR_UBM_1("AUFTRAG.LFD.NUMMERN.UBM.1", Long.class),
    LFD_NR_UBM_2("AUFTRAG.LFD.NUMMERN.UBM.2", Long.class),
    LFD_NR_UBM_3("AUFTRAG.LFD.NUMMERN.UBM.3", Long.class),
    LFD_NR_UBM_4("AUFTRAG.LFD.NUMMERN.UBM.4", Long.class),
    LFD_NR_UBM_5("AUFTRAG.LFD.NUMMERN.UBM.5", Long.class),

    LFD_NR_FHI("AUFTRAG.LFD.NUMMERN.FHI", Long.class),
    LFD_NR_GESAMT("AUFTRAG.LFD.NUMMERN.GESAMT", Long.class),

    SHOW_DEBUG_PROTOCOL("DEBUG.SHOW.PROTOCOLENTRIES", Long.class),

    ABSTAND_UMLAUF_OBERGRENZE("ABSTAND_UMLAUF_OBERGRENZE", Long.class),
    MAX_WARTESCHLANGE("SYS_MAX_WARTESCHLANGE", Long.class),
    MAX_VORSENDUNGEN("MAX_VORSENDUNGEN", Long.class),
    MAX_SEQUENZNUMMER("MAX_SEQUENZNUMMER", Long.class),

    OG_LMT_BAND1("OG_LMT_BAND1", Long.class),
    OG_LMT_BAND2("OG_LMT_BAND2", Long.class),
    OG_LMT_BAND3("OG_LMT_BAND3", Long.class),
    UG_LMT_BAND1("UG_LMT_BAND1", Long.class),
    UG_LMT_BAND2("UG_LMT_BAND2", Long.class),
    UG_LMT_BAND3("UG_LMT_BAND3", Long.class),

    SYS_AKT_FHI_TAKT("SYS_AKT_FHI_TAKT", Long.class),
    AUFTRAG_ZUSENDEN_VORLAUFZEIT_FHI_LADISPO("AUFTRAG.ZUSENDEN.VORLAUFZEIT_FHI_LADISPO",Long.class);

    private String systemwertKeyString;

    private Class<?> valueType;

    public static final FhiSystemwertKeyEnum getLfdNrKeyFor(SendTypeEnum pSendType, int pBandNr) {
        switch (pSendType) {
            case FHI:
                return LFD_NR_FHI;
            case LMT:
                return getLmtKeyForBand(pBandNr);
            case RHM:
                return getRhmKeyForBand(pBandNr);
            case UBM:
                return getUbmKeyForBand(pBandNr);
            case UNDEFINED:
                return LFD_NR_GESAMT;
            default:
                return UNDEFINED;
        }
    }

    private static final FhiSystemwertKeyEnum getLmtKeyForBand(int pBandNr) {
        if (pBandNr == 0) {
            return LFD_NR_LMT_0;
        }
        else if (pBandNr == 1) {
            return LFD_NR_LMT_1;
        }
        else if (pBandNr == 2) {
            return LFD_NR_LMT_2;
        }
        else if (pBandNr == 3) {
            return LFD_NR_LMT_3;
        }
        else if (pBandNr == 4) {
            return LFD_NR_LMT_4;
        }
        else if (pBandNr == 5) {
            return LFD_NR_LMT_5;
        }
        return UNDEFINED;
    }

    private static final FhiSystemwertKeyEnum getRhmKeyForBand(int pBandNr) {
        if (pBandNr == 0) {
            return LFD_NR_RHM_0;
        }
        else if (pBandNr == 1) {
            return LFD_NR_RHM_1;
        }
        else if (pBandNr == 2) {
            return LFD_NR_RHM_2;
        }
        else if (pBandNr == 3) {
            return LFD_NR_RHM_3;
        }
        else if (pBandNr == 4) {
            return LFD_NR_RHM_4;
        }
        else if (pBandNr == 5) {
            return LFD_NR_RHM_5;
        }
        return UNDEFINED;
    }

    private static final FhiSystemwertKeyEnum getUbmKeyForBand(int pBandNr) {
        if (pBandNr == 0) {
            return LFD_NR_UBM_0;
        }
        else if (pBandNr == 1) {
            return LFD_NR_UBM_1;
        }
        else if (pBandNr == 2) {
            return LFD_NR_UBM_2;
        }
        else if (pBandNr == 3) {
            return LFD_NR_UBM_3;
        }
        else if (pBandNr == 4) {
            return LFD_NR_UBM_4;
        }
        else if (pBandNr == 5) {
            return LFD_NR_UBM_5;
        }
        return UNDEFINED;
    }

    private FhiSystemwertKeyEnum(String pKeyString, Class<?> pValueType) {
        systemwertKeyString = pKeyString;
        valueType = pValueType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getKey() {
        return systemwertKeyString;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getValueType() {
        return valueType;
    }
}
