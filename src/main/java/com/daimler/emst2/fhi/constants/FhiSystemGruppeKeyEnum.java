package com.daimler.emst2.fhi.constants;

public enum FhiSystemGruppeKeyEnum implements ISystemgruppeKey {
    AUDIT_STAMMDATEN("AUDIT_STAMMDATEN"), 
    AUFTRAG_LFD_NUMMERN("AUFTRAG.LFD.NUMMERN")
    ;

    private String systemgruppeKeyString;

    private FhiSystemGruppeKeyEnum(String pKeyString) {
        systemgruppeKeyString = pKeyString;
    }

    @Override
    public String getKey() {
        return systemgruppeKeyString;
    }

}
