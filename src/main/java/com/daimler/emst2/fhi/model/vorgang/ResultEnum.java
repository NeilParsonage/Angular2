package com.daimler.emst2.fhi.model.vorgang;

public enum ResultEnum {

    UNDEFINED(-1), OK(2), NOK(3), ASYNCH(1), CHECKFAILED(4);

    public static final ResultEnum getResultEnum(int pStatus) {
        for (ResultEnum type : ResultEnum.values()) {
            if (type.status == pStatus) {
                return type;
            }
        }
        return UNDEFINED;
    }

    private final int status;

    private ResultEnum(int pStatus) {
        status = pStatus;
    }

    public boolean isOk() {
        return this.equals(OK);
    }

    public boolean isNok() {
        return this.equals(NOK);
    }

    public boolean isAsynch() {
        return this.equals(ASYNCH);
    }

    public boolean isCheckFailed() {
        return this.equals(CHECKFAILED);
    }
}
