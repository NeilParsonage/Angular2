package com.daimler.emst2.fhi.sendung.werk152.config;


import java.util.List;

import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.process.IProcessConfiguration;
import com.daimler.emst2.fhi.sendung.werk.action.SendActionEnum;
import com.daimler.emst2.fhi.sendung.werk.check.SendCheckEnum;

/**
 * Knoten fuer die FHIsendung.
 */
public class SendFhiConfiguration implements IProcessConfiguration<SendTypeEnum, SendCheckEnum, SendActionEnum> {

    @Override
    public SendTypeEnum getType() {
        return SendTypeEnum.FHI;
    }

    @Override
    public void fillCheckEnumList(List<SendCheckEnum> pCheckList) {

        // @see SendungenService.getSubProcessList Sendekennung und -status 
        // includes the Sendekennung und Sendestatus check as performed in PL/SQL PROCEDURE Pruefe_Sendekennung

        pCheckList.add(SendCheckEnum.AUFTRAG_UPTODATE);
        pCheckList.add(SendCheckEnum.AUFTRAG_ANKUENDIGUNG_VORHANDEN);
        pCheckList.add(SendCheckEnum.AUFTRAG_SPERRE_VORHANDEN);
        pCheckList.add(SendCheckEnum.FHI_IMPLIZITE_TEILSENDUNG);
        pCheckList.add(SendCheckEnum.FHI_SOLLABSTAND);
    }

    @Override
    public void fillActionEnumList(List<SendActionEnum> pActionList) {
        // Actions, die unabhaengig von der Sendung immer durchgefuehrt werden muessen
        pActionList.add(SendActionEnum.ALL_LAUFENDE_NUMMER_AKTUALISIEREN);
        pActionList.add(SendActionEnum.ALL_MANUELLE_RF_INFO_LOESCHEN);
        pActionList.add(SendActionEnum.ALL_SENDBAR_KNZ_SETZEN);

        pActionList.add(SendActionEnum.FHI_HISTORISIERUNG_STERNENHIMMEL);
        pActionList.add(SendActionEnum.FHI_LAUFENDE_NUMMER_AKTUALISIEREN);
        pActionList.add(SendActionEnum.FHI_SENDESTATUS_SETZEN);
        pActionList.add(SendActionEnum.FHI_TAKT_TELEGRAMM);

        pActionList.add(SendActionEnum.ALL_HISTORIE_SCHREIBEN);
        pActionList.add(SendActionEnum.ALL_SAVE_AUFTRAG);

        pActionList.add(SendActionEnum.FHI_LMT_SOLLABSTAND_VORBERECHNEN);
    }

}
