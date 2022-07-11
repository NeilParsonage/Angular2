package com.daimler.emst2.fhi.sendung.werk.precondition;

import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.model.ITuebObject;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;

public enum SendPreconditionEnum implements IProcessId {
    UNDEFINED("undefined", 1),
    AUFTRAG_UPTODATE(ProcessPreconditionEnum.AUFTRAG_UPTODATE, 100),
    RESTRIKTIONEN_UPTODATE("uptodate.restriktionen", 200),
    ORTSDATEN_FETCHED(ProcessPreconditionEnum.ORTSDATEN_FETCHED, 300),
    AUFTRAG_LFD_NR_FETCHED(ProcessPreconditionEnum.AUFTRAG_LFD_NR_FETCHED, 400), 
    AUFTRAG_SPERREN_ANKUENDIGUNGEN_UPTODATE("uptodate.sperrenankuendigungen.auftrag", 500), 
    SEND_HIERARCHY_INFOS_FOR_HISTORY_LMT(ProcessPreconditionEnum.HIERARCHY_INFOS_FOR_HISTORY, 600), 
    SEND_HIERARCHY_INFOS_FOR_HISTORY_FHI(ProcessPreconditionEnum.HIERARCHY_INFOS_FOR_HISTORY, 601), 
    SEND_HIERARCHY_INFOS_FOR_HISTORY_RHM(ProcessPreconditionEnum.HIERARCHY_INFOS_FOR_HISTORY, 602), 
    SEND_HIERARCHY_INFOS_FOR_HISTORY_UBM(ProcessPreconditionEnum.HIERARCHY_INFOS_FOR_HISTORY, 603),
    ANZAHL_FREIE_FETCHED(ProcessPreconditionEnum.ANZAHL_FREIE_FETCHED, 700),
    AUFTRAG_SPERREN_FETCHED(ProcessPreconditionEnum.AUFTRAG_SPERREN_FETCHED, 800),
    ;

    public static final String DEFAULT_TUEB_PREFIX = "send.precondition.";

    private String tuebKey;

    private Integer orderNum;

    private SendPreconditionEnum(ProcessPreconditionEnum pCommonEnum, Integer pOrderNum) {
        tuebKey = pCommonEnum.getTuebKey();
        orderNum = pOrderNum;
    }

    private SendPreconditionEnum(String pTuebKey, Integer pOrderNum) {
        tuebKey = pTuebKey;
        orderNum = pOrderNum;
    }

    @Override
    public String getTuebKey() {
        return DEFAULT_TUEB_PREFIX + tuebKey;
    }

    @Override
    public Object getId() {
        return orderNum;
    }

    @Override
    public ITuebObject getTyp() {
        return SendTypeEnum.UNDEFINED;
    }

    @Override
    public Integer getOrderNum() {
        return orderNum;
    }
}
