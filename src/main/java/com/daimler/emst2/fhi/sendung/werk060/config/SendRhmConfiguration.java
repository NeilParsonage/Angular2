package com.daimler.emst2.fhi.sendung.werk060.config;

import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.werk.action.SendActionEnum;
import com.daimler.emst2.fhi.sendung.werk.check.SendCheckEnum;
import com.daimler.emst2.fhi.sendung.werk.config.AbstractSendConfiguration;

/**
 * Knoten fuer die RHMsendung.
 */
public class SendRhmConfiguration extends AbstractSendConfiguration {

    public SendRhmConfiguration() {
        this.type = SendTypeEnum.RHM;
    }

    @Override
    public SendTypeEnum getType() {
        return SendTypeEnum.RHM;
    }

    @Override
    protected void setupCheckEnumList() {
        addVerifiedCheck(SendCheckEnum.AUFTRAG_UPTODATE);
        // addVerifiedCheck(SendCheckEnum.RHM_ORT_ERR);
        addVerifiedCheck(SendCheckEnum.RHM_AUFTRAG_SPERREN_FUER_BEREICH_060);
        addVerifiedCheck(SendCheckEnum.WARTESCHLANGE_060);
        addVerifiedCheck(SendCheckEnum.AUFTRAG_ANZAHL_FREIE_060);
    }

    @Override
    protected void setupActionEnumList() {
        // Actions, die unabhaengig von der Sendung immer durchgefuehrt werden muessen
        addVerifiedAction(SendActionEnum.ALL_LAUFENDE_NUMMER_AKTUALISIEREN);
        addVerifiedAction(SendActionEnum.ALL_MANUELLE_RF_INFO_LOESCHEN);
        addVerifiedAction(SendActionEnum.ALL_SENDBAR_KNZ_SETZEN);

        addVerifiedAction(SendActionEnum.RHM_LAUFENDE_NUMMER_AKTUALISIEREN);
        addVerifiedAction(SendActionEnum.RHM_SENDESTATUS_SETZEN);
        addVerifiedAction(SendActionEnum.RHM_TAKT_TELEGRAMM);

        addVerifiedAction(SendActionEnum.ALL_HISTORIE_SCHREIBEN);
        addVerifiedAction(SendActionEnum.ALL_SAVE_AUFTRAG);
    }
}