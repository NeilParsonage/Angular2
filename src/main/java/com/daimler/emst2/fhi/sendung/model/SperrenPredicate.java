package com.daimler.emst2.fhi.sendung.model;

import org.apache.commons.collections4.Predicate;

import com.daimler.emst2.fhi.jpa.model.AuftragSperrInformation;
import com.daimler.emst2.fhi.sendung.constants.SperrtypEnum;

public class SperrenPredicate implements Predicate {
    private final SperrtypEnum typ;

    private final Boolean materialSperre;

    private final String bereich;

    public SperrenPredicate(SperrtypEnum pTyp, Boolean pMaterialSperre, String pBereich) {
        typ = pTyp;
        materialSperre = pMaterialSperre;
        bereich = pBereich;
    }

    @Override
    public boolean evaluate(Object pArg0) {
        AuftragSperrInformation info = (AuftragSperrInformation)pArg0;
        return evaluateSperrInformation(info);
    }

    private boolean evaluateSperrInformation(AuftragSperrInformation pInfo) {
        if (typ != null && !typ.equals(pInfo.getSperrtypEnum())) {
            return false;
        }
        if (materialSperre != null && !materialSperre.equals(pInfo.getKnzMaterialSperre())) {
            return false;
        }
        if (bereich != null && !bereich.equalsIgnoreCase(pInfo.getBereichFhi())) {
            return false;
        }
        return true;
    }
}
