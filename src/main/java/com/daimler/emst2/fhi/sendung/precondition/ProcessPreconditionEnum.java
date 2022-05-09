package com.daimler.emst2.fhi.sendung.precondition;

import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.model.ITuebObject;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;

public enum ProcessPreconditionEnum implements IProcessId {
    UNDEFINED("undefined", 1),
    AUFTRAG_UPTODATE("auftrag.uptodate", 100),
    ORTSDATEN_FETCHED("ortsdaten.geladen", 200),
    AUFTRAG_LFD_NR_FETCHED("lfd.nr.current.geladen", 300), 
    HIERARCHY_INFOS_FOR_HISTORY("history.infos.prepared", 400)
    ;

    public static final String DEFAULT_TUEB_PREFIX = "common.precondition.";

    private String tuebKey;

    private Integer orderNum;

    private ProcessPreconditionEnum(String pTuebKey, Integer pOrderNume) {
        tuebKey = pTuebKey;
        orderNum = pOrderNume;
    }

    @Override
    public String getTuebKey() {
        return tuebKey;
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
