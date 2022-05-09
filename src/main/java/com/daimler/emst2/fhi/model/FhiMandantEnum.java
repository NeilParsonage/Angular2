package com.daimler.emst2.fhi.model;

public enum FhiMandantEnum {

    UNDEFINED(""), WERK_060("060"), WERK_152("152");

    private String werkString;

    private FhiMandantEnum(String pWerkString) {
        werkString = pWerkString;
    }

    public static final FhiMandantEnum getMandant(String pWerkString) {
        FhiMandantEnum[] all = FhiMandantEnum.values();
        for (FhiMandantEnum fhiMandant: all) {
            if (fhiMandant.werkString.equals(pWerkString)) {
                return fhiMandant;
            }
        }
        return UNDEFINED;
    }

    public boolean isUndefined() {
        return UNDEFINED.equals(this);
    }

    public boolean isWerk060() {
        return WERK_060.equals(this);
    }

    public boolean isWerk152() {
        return WERK_152.equals(this);
    }

    public String getMandantString() {
        return werkString;
    }

}
