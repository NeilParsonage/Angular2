package com.daimler.emst2.fhi.sendung.constants;

import java.util.logging.Logger;

public enum SendStatusEnum {

	// NEU (frueher NICHT_GESENDET) umbenannt um Missverstaendnisse mit dem
	// Status GESENDET zu vermeiden
    NEU("0", "0"),
    PLANSEQUENZIERT("P", "1"),
    STORNIERT("S", "3"),
    ISTSEQUENZIERT("I", "I"),
    UNDEFINED("U", "U");

	private final String uiValue;
	private final String dbValue;

    private static final Logger LOG = Logger.getLogger(SendStatusEnum.class.getName());

    private SendStatusEnum(String sValue, String dbValue) {
		this.uiValue = sValue;
        this.dbValue = dbValue;
	}

	public static final SendStatusEnum getSendStatus(String stringValue) {
		for (SendStatusEnum sendStatus : SendStatusEnum.values()) {
            if (sendStatus.getDBValue().equals(stringValue)) {
				return sendStatus;
			}
		}
		LOG.severe("Undefined SendStatus detected: " + stringValue
				+ " (continue with status UNDEFINED)");
		return SendStatusEnum.UNDEFINED;
	}

	public boolean isNeu() {
		return NEU.equals(this);
	}

	public boolean isGesendet() {
		return PLANSEQUENZIERT.equals(this) || ISTSEQUENZIERT.equals(this);
	}

	public boolean isPlansequenziert() {
		return PLANSEQUENZIERT.equals(this);
	}

	public boolean isIstsequenziert() {
		return ISTSEQUENZIERT.equals(this);
	}

	public boolean isStorniert() {
		return STORNIERT.equals(this);
	}

    public String getDBValue() {
        return dbValue;
    }

    @Deprecated
    public String getUiValue() {
		return uiValue;
	}
}
