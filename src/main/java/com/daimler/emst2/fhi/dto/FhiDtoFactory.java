package com.daimler.emst2.fhi.dto;

import org.springframework.stereotype.Component;

import com.daimler.emst2.fhi.jpa.model.Auftraege;

@Component
public class FhiDtoFactory {

    public AuftraegeDTO createAuftragDTO(Auftraege auftraege) {
        AuftraegeDTO neu = new AuftraegeDTO();
        neu.pnr = auftraege.getPnr();
        neu.lfdNrGes = auftraege.getLfdNrGes();
        neu.lfdNrFhi = auftraege.getLfdNrFhi();
        neu.lfdNrLmt = auftraege.getLfdNrLmt();
        return neu;
    }
}
