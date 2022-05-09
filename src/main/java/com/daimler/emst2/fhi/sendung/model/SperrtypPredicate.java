package com.daimler.emst2.fhi.sendung.model;

import org.apache.commons.collections4.Predicate;

import com.daimler.emst2.fhi.jpa.model.AuftragSperrInformation;
import com.daimler.emst2.fhi.sendung.constants.SperrtypEnum;

public class SperrtypPredicate implements Predicate {
    private final SperrtypEnum typ;

    public SperrtypPredicate(SperrtypEnum pTyp) {
        typ = pTyp;
    }

    @Override
    public boolean evaluate(Object pArg0) {
        AuftragSperrInformation info = (AuftragSperrInformation)pArg0;
        return evaluateSperrInformation(info);
    }

    private boolean evaluateSperrInformation(AuftragSperrInformation pInfo) {
        return pInfo.getSperrtypEnum().equals(typ);
    }
}
