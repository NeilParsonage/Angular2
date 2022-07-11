package com.daimler.emst2.fhi.sendung.werk.action;

import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;

public enum SendActionEnum implements IProcessId {

    UNDEFINED("undefined", SendTypeEnum.UNDEFINED, 0),

    // Sternhimmelhistorie schreiben
    FHI_HISTORISIERUNG_STERNENHIMMEL("historisierung.sterne.fhi", SendTypeEnum.FHI, 100),
    LMT_HISTORISIERUNG_STERNENHIMMEL("historisierung.sterne.lmt", SendTypeEnum.LMT, 100),

    // Sendestatus IST aktualisieren
    LMT_SENDESTATUS_SETZEN("update.sendestatus.lmt", SendTypeEnum.LMT, 200),
    FHI_SENDESTATUS_SETZEN("update.sendestatus.fhi", SendTypeEnum.FHI, 200),
    UBM_SENDESTATUS_SETZEN("update.sendestatus.ubm", SendTypeEnum.UBM, 200),
    RHM_SENDESTATUS_SETZEN("update.sendestatus.rhm", SendTypeEnum.RHM, 200),

    // Setze sendbar nein
    ALL_SENDBAR_KNZ_SETZEN("update.sendbar.knz", SendTypeEnum.UNDEFINED, 300),

    // Auftrag aus manueller Reihenfolge löschen
    ALL_MANUELLE_RF_INFO_LOESCHEN("update.manuelle.reihenfolge", SendTypeEnum.UNDEFINED, 400),

    //    Laufende Nummern fortschreiben
    ALL_LAUFENDE_NUMMER_AKTUALISIEREN("update.lfdnr.gesamt", SendTypeEnum.UNDEFINED, 500),
    LMT_LAUFENDE_NUMMER_AKTUALISIEREN("update.lfdnr.lmt", SendTypeEnum.LMT, 500),
    FHI_LAUFENDE_NUMMER_AKTUALISIEREN("update.lfdnr.fhi", SendTypeEnum.FHI, 500),
    UBM_LAUFENDE_NUMMER_AKTUALISIEREN("update.lfdnr.ubm", SendTypeEnum.UBM, 500),
    RHM_LAUFENDE_NUMMER_AKTUALISIEREN("update.lfdnr.rhm", SendTypeEnum.RHM, 500),

    //    Telegramm an TAKT senden
    LMT_TAKT_TELEGRAMM("takt.telegramm.lmt", SendTypeEnum.LMT, 600),
    FHI_TAKT_TELEGRAMM("takt.telegramm.fhi", SendTypeEnum.FHI, 600),
    UBM_TAKT_TELEGRAMM("takt.telegramm.ubm", SendTypeEnum.UBM, 600),
    RHM_TAKT_TELEGRAMM("takt.telegramm.rhm", SendTypeEnum.RHM, 600),

    //    Historieneintrag schreiben
    ALL_HISTORIE_SCHREIBEN("auftrag.historie.all", SendTypeEnum.UNDEFINED, 700),

    ALL_SAVE_AUFTRAG("auftrag.save", SendTypeEnum.UNDEFINED, 800),

    //    Sollabstand vorberechnen
    FHI_LMT_SOLLABSTAND_VORBERECHNEN("sollabstand.vorberechnen", SendTypeEnum.UNDEFINED, 900);

    private static final int ORDER_NUM_FACTOR = 10000;

    private String tuebKey;

    private SendTypeEnum sendTyp;

    private Integer orderNum;

    private SendActionEnum(String pTuebKey, SendTypeEnum pSendTyp, Integer pOrderNum) {
        tuebKey = pTuebKey;
        sendTyp = pSendTyp;
        orderNum = pOrderNum * ORDER_NUM_FACTOR;
    }

    @Override
    public String getTuebKey() {
        return tuebKey;
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
