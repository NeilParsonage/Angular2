package com.daimler.emst2.fhi.sendung.werk060.config;

import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.werk.action.SendActionEnum;
import com.daimler.emst2.fhi.sendung.werk.config.AbstractSendConfiguration;

/**
 * Knoten fuer die LMT-Sendung.
 */
public class SendLmtConfiguration extends AbstractSendConfiguration {

    public SendLmtConfiguration() {

        this.type = SendTypeEnum.LMT;
    }

    @Override
    public SendTypeEnum getType() {
        return SendTypeEnum.LMT;
    }

    @Override
    protected void setupCheckEnumList() {
        // pCheckList.add(SendCheckEnum.AUFTRAG_UPTODATE);
        //pCheckList.add(SendCheckEnum.LMT_ORT_ERR);
        //pCheckList.add(SendCheckEnum.AUFTRAG_ANZAHL_FREIE_060);
    }

    @Override
    protected void setupActionEnumList() {
        // Actions, die unabhaengig von der Sendung immer durchgefuehrt werden muessen
        addVerifiedAction(SendActionEnum.ALL_LAUFENDE_NUMMER_AKTUALISIEREN);
        addVerifiedAction(SendActionEnum.ALL_MANUELLE_RF_INFO_LOESCHEN);
        addVerifiedAction(SendActionEnum.ALL_SENDBAR_KNZ_SETZEN);

        addVerifiedAction(SendActionEnum.LMT_HISTORISIERUNG_STERNENHIMMEL);
        addVerifiedAction(SendActionEnum.LMT_LAUFENDE_NUMMER_AKTUALISIEREN);
        addVerifiedAction(SendActionEnum.LMT_SENDESTATUS_SETZEN);
        addVerifiedAction(SendActionEnum.LMT_TAKT_TELEGRAMM);

        addVerifiedAction(SendActionEnum.ALL_HISTORIE_SCHREIBEN);
        addVerifiedAction(SendActionEnum.ALL_SAVE_AUFTRAG);

        addVerifiedAction(SendActionEnum.FHI_LMT_SOLLABSTAND_VORBERECHNEN);
    }
}