package com.daimler.emst2.fhi.constants;


public enum BooleanEnum {
    UNDEFINED("-1", -1), SOLL_TRUE("1", 1), SOLL_FALSE("0", 0);

    private int statusInt;

    private String status;

    private BooleanEnum(String pStatus, int pStatusInt) {
        statusInt = pStatusInt;
        status = pStatus;
    }

    public static BooleanEnum createFromString(String pStatus) {
        BooleanEnum[] values = BooleanEnum.values();
        for (BooleanEnum sendStatusSollEnum : values) {
            if (sendStatusSollEnum.status.equals(pStatus)) {
                return sendStatusSollEnum;
            }
        }
        return UNDEFINED;
    }

    public boolean booleanValue() {
        switch (this) {
            case SOLL_TRUE:
                return true;
            default:
                return false;
        }
    }
}
