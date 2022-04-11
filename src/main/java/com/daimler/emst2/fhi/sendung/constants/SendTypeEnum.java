package com.daimler.emst2.fhi.sendung.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.model.ITuebObject;

public enum SendTypeEnum implements IProcessId {
    UNDEFINED("undefined", -1),
    KOMPLETT("komplett", 1),
    FHI("fhi", 2),
    LMT("lmt", 3),
    UBM("ubm", 4),
    RHM("rhm", 5),
    LMT_WITHOUT_RHM("lmt.without.rhm", 6),
    //
    WARTESCHLANGE_ALL("wall", 7);

    public static final String DEFAULT_TUEB_PREFIX = "sendekennung.";

    private String tuebKey;

    private Integer orderNum;

    public static final List<SendTypeEnum> getSendTypesForLfdNr() {
        SendTypeEnum[] sendTypeEnums = SendTypeEnum.values();
        List<SendTypeEnum> sendTypeEnumsList = new ArrayList<SendTypeEnum>(Arrays.asList(sendTypeEnums));
        sendTypeEnumsList.remove(KOMPLETT);
        sendTypeEnumsList.remove(LMT_WITHOUT_RHM);
        return sendTypeEnumsList;
    }

    public static final List<SendTypeEnum> getAllTeilSendTypes() {
        List<SendTypeEnum> sendTypeEnumsList = new ArrayList<SendTypeEnum>();
        sendTypeEnumsList.add(LMT);
        sendTypeEnumsList.add(FHI);
        sendTypeEnumsList.add(RHM);
        sendTypeEnumsList.add(UBM);
        return sendTypeEnumsList;

    }

    private SendTypeEnum(String pTuebKey, Integer pId) {
        tuebKey = pTuebKey;
        orderNum = pId;
    }

    public String getTuebKeyForHist() {
        return DEFAULT_TUEB_PREFIX + tuebKey + ".hist";
    }

    public String getTuebKey() {
        return DEFAULT_TUEB_PREFIX + tuebKey;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public Object getId() {
        return orderNum;
    }

    public ITuebObject getTyp() {
        return this;
    }

    public boolean isKomplett() {
        return KOMPLETT.equals(this);
    }

}
