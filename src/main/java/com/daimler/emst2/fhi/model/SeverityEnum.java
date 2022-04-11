package com.daimler.emst2.fhi.model;

/**
 * SeverityEnum definiert 4 severities z.B. für Sende Prüfungen
 * 
 * @author thi
 */
public enum SeverityEnum implements ITuebObject {
    DEBUG("debug", 0, "D"),
    INFO("info", 1, "I"),
    WARNING("warn", 2, "W"),
    ERROR("error", 3, "E"),
    FATAL("fatal", 4, "F");

    public static final String DEFAULT_TUEB_PREFIX = "severity.";

    /**
     * Return the correct enum value. If no matching enum found - return enum level ERROR.
     */
    public static final SeverityEnum getEnumByString(String pSeverity) {
        for (SeverityEnum type : SeverityEnum.values()) {
            if (type.stringLevel.equals(pSeverity)) {
                return type;
            }
        }
        return ERROR;
    }

    private String tuebKey;

    private int intLevel;

    private String stringLevel;

    private SeverityEnum(String pTuebKey, int pLevel, String pStringLevel) {
        tuebKey = pTuebKey;
        intLevel = pLevel;
        stringLevel = pStringLevel;
    }

    public boolean isLower(SeverityEnum pEnum) {
        return this.getLevel() < pEnum.getLevel();
    }

    public boolean isHigherOrEqual(SeverityEnum pEnum) {
        return this.getLevel() >= pEnum.getLevel();
    }

    @Override
    public String getTuebKey() {
        return tuebKey;
    }

    public int getLevel() {
        return intLevel;
    }

    @Override
    public Object getId() {
        return Integer.valueOf(intLevel);
    }
}