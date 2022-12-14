package com.daimler.emst2.fhi.sendung.werk152.config;

import java.util.List;

import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.process.IProcessConfiguration;
import com.daimler.emst2.fhi.sendung.werk.action.SendActionEnum;
import com.daimler.emst2.fhi.sendung.werk.check.SendCheckEnum;

/**
 * Knoten fuer die UBMsendung.
 */
public class SendUbmConfiguration implements IProcessConfiguration<SendTypeEnum, SendCheckEnum, SendActionEnum> {

    @Override
    public SendTypeEnum getType() {
        return SendTypeEnum.UBM;
    }

    @Override
    public void fillCheckEnumList(List<SendCheckEnum> pCheckList) {
        pCheckList.add(SendCheckEnum.AUFTRAG_UPTODATE);
        pCheckList.add(SendCheckEnum.AUFTRAG_ANKUENDIGUNG_VORHANDEN);
        pCheckList.add(SendCheckEnum.AUFTRAG_SPERRE_VORHANDEN);
        pCheckList.add(SendCheckEnum.UBM_IMPLIZITE_TEILSENDUNG);
        pCheckList.add(SendCheckEnum.UBM_SOLLABSTAND);
        pCheckList.add(SendCheckEnum.UBM_ORT_ERR);
    }

    @Override
    public void fillActionEnumList(List<SendActionEnum> pActionList) {
        // Actions, die unabhaengig von der Sendung immer durchgefuehrt werden muessen
        pActionList.add(SendActionEnum.ALL_LAUFENDE_NUMMER_AKTUALISIEREN);
        pActionList.add(SendActionEnum.ALL_MANUELLE_RF_INFO_LOESCHEN);
        pActionList.add(SendActionEnum.ALL_SENDBAR_KNZ_SETZEN);

        pActionList.add(SendActionEnum.UBM_LAUFENDE_NUMMER_AKTUALISIEREN);
        pActionList.add(SendActionEnum.UBM_SENDESTATUS_SETZEN);
        pActionList.add(SendActionEnum.UBM_TAKT_TELEGRAMM);

        pActionList.add(SendActionEnum.ALL_HISTORIE_SCHREIBEN);
        pActionList.add(SendActionEnum.ALL_SAVE_AUFTRAG);
    }
}