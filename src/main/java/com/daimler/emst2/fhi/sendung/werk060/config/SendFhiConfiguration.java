package com.daimler.emst2.fhi.sendung.werk060.config;


import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.werk.action.SendActionEnum;
import com.daimler.emst2.fhi.sendung.werk.check.SendCheckEnum;
import com.daimler.emst2.fhi.sendung.werk.config.AbstractSendConfiguration;

/**
 * Knoten fuer die FHIsendung.
 */
public class SendFhiConfiguration extends AbstractSendConfiguration {

    public SendFhiConfiguration() {

        this.type = SendTypeEnum.FHI;
    }

    @Override
    protected void setupCheckEnumList() {

        // @see SendungenService.getSubProcessList Sendekennung und -status 
        // includes the Sendekennung und Sendestatus check as performed in PL/SQL PROCEDURE Pruefe_Sendekennung

        addVerifiedCheck(SendCheckEnum.AUFTRAG_ANZAHL_FREIE_060);
        addVerifiedCheck(SendCheckEnum.AUFTRAG_UPTODATE);
        addVerifiedCheck(SendCheckEnum.FHI_WARTESCHLANGE_060);
        addVerifiedCheck(SendCheckEnum.FHI_ORT_ERR);
        addVerifiedCheck(SendCheckEnum.FHI_SEQNR_OBERGRENZE_060);
        addVerifiedCheck(SendCheckEnum.FHI_VORSENDUNGEN_060);
        addVerifiedCheck(SendCheckEnum.FHI_ERREICHBARKEIT_060);
        addVerifiedCheck(SendCheckEnum.FHI_AUFTRAG_SPERREN_FUER_BEREICH_060);
        addVerifiedCheck(SendCheckEnum.GASSENSPERRE_060);

    }

    @Override
    protected void setupActionEnumList() {
        // Actions, die unabhaengig von der Sendung immer durchgefuehrt werden muessen
        addVerifiedAction(SendActionEnum.ALL_LAUFENDE_NUMMER_AKTUALISIEREN);
        addVerifiedAction(SendActionEnum.ALL_MANUELLE_RF_INFO_LOESCHEN);
        addVerifiedAction(SendActionEnum.ALL_SENDBAR_KNZ_SETZEN);

        addVerifiedAction(SendActionEnum.FHI_HISTORISIERUNG_STERNENHIMMEL);
        addVerifiedAction(SendActionEnum.FHI_LAUFENDE_NUMMER_AKTUALISIEREN);
        addVerifiedAction(SendActionEnum.FHI_SENDESTATUS_SETZEN);
        addVerifiedAction(SendActionEnum.FHI_TAKT_TELEGRAMM);

        addVerifiedAction(SendActionEnum.ALL_HISTORIE_SCHREIBEN);
        addVerifiedAction(SendActionEnum.ALL_SAVE_AUFTRAG);

        addVerifiedAction(SendActionEnum.FHI_LMT_SOLLABSTAND_VORBERECHNEN);
    }


}
