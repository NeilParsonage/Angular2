package com.daimler.emst2.fhi.sendung.werk.check;

import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;

public enum SendCheckEnum implements IProcessId {
    AUFTRAG_UPTODATE("uptodate.auftrag", SendTypeEnum.UNDEFINED, 100),
    AUFTRAG_SPERRE_VORHANDEN("sperre.auftrag", SendTypeEnum.UNDEFINED, 100),
    AUFTRAG_ANKUENDIGUNG_VORHANDEN("ankuendigung.auftrag", SendTypeEnum.UNDEFINED, 100),

    FHI_IMPLIZITE_TEILSENDUNG("teilsend.implizit.fhi", SendTypeEnum.FHI, 200),
    UBM_IMPLIZITE_TEILSENDUNG("teilsend.implizit.ubm", SendTypeEnum.UBM, 200),
    RHM_IMPLIZITE_TEILSENDUNG("teilsend.implizit.rhm", SendTypeEnum.RHM, 200),

    LMT_SOLLABSTAND("sollabstand.lmt", SendTypeEnum.LMT, 300),
    FHI_SOLLABSTAND("sollabstand.fhi", SendTypeEnum.FHI, 300),
    RHM_SOLLABSTAND("sollabstand.rhm", SendTypeEnum.RHM, 300),
    UBM_SOLLABSTAND("sollabstand.ubm", SendTypeEnum.UBM, 300),

    LMT_ORT_ERR("ort.error.lmt", SendTypeEnum.LMT, 400),
    FHI_ORT_ERR("ort.error.fhi", SendTypeEnum.FHI,400),
    RHM_ORT_ERR("ort.error.rhm", SendTypeEnum.RHM, 400),
    UBM_ORT_ERR("ort.error.ubm", SendTypeEnum.UBM, 400),

    WARTESCHLANGE_060("warteschlange.error.fhi", SendTypeEnum.UNDEFINED, 500),

    FHI_SEQNR_OBERGRENZE_060("maxSeqNr.error.fhi", SendTypeEnum.FHI, 600),

    FHI_VORSENDUNGEN_060("maxVorsendungen.error.fhi", SendTypeEnum.FHI, 700),

    FHI_ERREICHBARKEIT_060("erreichbarkeit.warning.fhi", SendTypeEnum.FHI, 800),

    FHI_UMLAUFGRENZE_UEBERSCHRITTEN_060("erreichbarkeit.warning.fhi", SendTypeEnum.FHI, 900),
    RHM_UMLAUFGRENZE_UEBERSCHRITTEN_060("erreichbarkeit.warning.fhi", SendTypeEnum.RHM, 900),

    FHI_AUFTRAG_SPERREN_FUER_BEREICH_060("auftragSperrenFuerBereich.warning.fhi", SendTypeEnum.FHI, 1000),
    RHM_AUFTRAG_SPERREN_FUER_BEREICH_060("auftragSperrenFuerBereich.warning.rhm", SendTypeEnum.RHM, 1000),

    FHI_GASSENSPERRE_060("gassensperre.warning.fhi", SendTypeEnum.FHI, 1100),

    FHI_GASSE_4_060("gasse4.warning.fhi", SendTypeEnum.FHI, 1200),

    AUFTRAG_ANZAHL_FREIE_060("anzahlfreie.warning.fhi", SendTypeEnum.UNDEFINED, 1300),


    ;

    public static final String DEFAULT_TUEB_PREFIX = "send.check.";

    private String tuebKey;

    private SendTypeEnum sendTyp;

    private Integer orderNum;

    private SendCheckEnum(String pTuebKey, SendTypeEnum pSendTyp, Integer pOrderNum) {
        tuebKey = pTuebKey;
        sendTyp = pSendTyp;
        orderNum = pOrderNum;
    }

    public final static SendCheckEnum getByName(String name) {
        for (SendCheckEnum entry : values()) {
            if (entry.name().equals(name)) {
                return entry;
            }
        }
        return null;
    }

    @Override
    public String getTuebKey() {
        return DEFAULT_TUEB_PREFIX + tuebKey;
    }

    @Override
    public Object getId() {
        String result = String.valueOf(sendTyp.getOrderNum()) + "-" + String.valueOf(orderNum);
        return result;
    }

    @Override
    public SendTypeEnum getTyp() {
        return sendTyp;
    }

    @Override
    public Integer getOrderNum() {
        return orderNum;
    }
}
