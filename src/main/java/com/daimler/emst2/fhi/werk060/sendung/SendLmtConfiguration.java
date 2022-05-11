package com.daimler.emst2.fhi.werk060.sendung;

import java.util.List;

import com.daimler.emst2.fhi.sendung.action.SendActionEnum;
import com.daimler.emst2.fhi.sendung.check.SendCheckEnum;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.process.IProcessConfiguration;

/**
 * Knoten fuer die LMT-Sendung.
 */
public class SendLmtConfiguration implements IProcessConfiguration<SendTypeEnum, SendCheckEnum, SendActionEnum> {

    @Override
    public SendTypeEnum getType() {
        return SendTypeEnum.LMT;
    }

    @Override
    public void fillCheckEnumList(List<SendCheckEnum> pCheckList) {
        pCheckList.add(SendCheckEnum.AUFTRAG_UPTODATE);
        pCheckList.add(SendCheckEnum.AUFTRAG_ANKUENDIGUNG_VORHANDEN);
        pCheckList.add(SendCheckEnum.AUFTRAG_SPERRE_VORHANDEN);
        pCheckList.add(SendCheckEnum.LMT_SOLLABSTAND);
        pCheckList.add(SendCheckEnum.LMT_ORT_ERR);
    }

    @Override
    public void fillActionEnumList(List<SendActionEnum> pActionList) {
        // Actions, die unabhaengig von der Sendung immer durchgefuehrt werden muessen
        pActionList.add(SendActionEnum.ALL_LAUFENDE_NUMMER_AKTUALISIEREN);
        pActionList.add(SendActionEnum.ALL_MANUELLE_RF_INFO_LOESCHEN);
        pActionList.add(SendActionEnum.ALL_SENDBAR_KNZ_SETZEN);

        pActionList.add(SendActionEnum.LMT_HISTORISIERUNG_STERNENHIMMEL);
        pActionList.add(SendActionEnum.LMT_LAUFENDE_NUMMER_AKTUALISIEREN);
        pActionList.add(SendActionEnum.LMT_SENDESTATUS_SETZEN);
        pActionList.add(SendActionEnum.LMT_TAKT_TELEGRAMM);

        pActionList.add(SendActionEnum.ALL_HISTORIE_SCHREIBEN);
        pActionList.add(SendActionEnum.ALL_SAVE_AUFTRAG);

        pActionList.add(SendActionEnum.FHI_LMT_SOLLABSTAND_VORBERECHNEN);
    }
}