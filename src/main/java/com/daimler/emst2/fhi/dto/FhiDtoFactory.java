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
        neu.bandNr = auftraege.getBandNr();
        neu.fzgArt = auftraege.getFzgArt();
        neu.fhsBaumuster = auftraege.getFhsBaumuster();
        neu.fzgbaumuster = auftraege.getFzgbaumuster();
        neu.anr = auftraege.getAnr();
        neu.verkBez = auftraege.getVerkBez();

        return neu;
    }
}
