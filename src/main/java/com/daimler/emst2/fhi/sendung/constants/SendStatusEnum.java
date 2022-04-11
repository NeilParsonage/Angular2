package com.daimler.emst2.fhi.sendung.constants;

import java.util.logging.Logger;

public enum SendStatusEnum {

	// NEU (frueher NICHT_GESENDET) umbenannt um Missverstaendnisse mit dem
	// Status GESENDET zu vermeiden

	NEU("0"), PLANSEQUENZIERT("P"), ISTSEQUENZIERT("I"), STORNIERT(
            "S"),
    UNDEFINED("U");

	private final String stringValue;

    private static final Logger LOG = Logger.getLogger(SendStatusEnum.class.getName());

	private SendStatusEnum(String sValue) {
		this.stringValue = sValue;
	}

	public static final SendStatusEnum getSendStatus(String stringValue) {
		for (SendStatusEnum sendStatus : SendStatusEnum.values()) {
			if (sendStatus.getStringValue().equals(stringValue)) {
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

	public String getStringValue() {
		return stringValue;
	}
}
