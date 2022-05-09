package com.daimler.emst2.fhi.sendung.constants;

public enum CancelSendTypeEnum {

    STORNO("S"), AUSFALL("A");

    private String code;

    private CancelSendTypeEnum(String pCode) {
        code = pCode;
    }

    public String getCode() {
        return code;
    }

}
