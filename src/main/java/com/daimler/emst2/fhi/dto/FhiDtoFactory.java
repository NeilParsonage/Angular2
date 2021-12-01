package com.daimler.emst2.fhi.dto;

import org.springframework.stereotype.Component;

import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.jpa.model.AuftragTermine;

@Component
public class FhiDtoFactory {

    public AuftraegeDTO createAuftragDTO(Auftraege auftraege) {
        AuftraegeDTO neu = new AuftraegeDTO();
        neu.pnr = auftraege.getPnr();
        neu.lfdNrGes = auftraege.getLfdNrGes();
        neu.lfdNrFhi = auftraege.getLfdNrFhi();
        neu.lfdNrLmt = auftraege.getLfdNrLmt();
        neu.lfdNrUbm = auftraege.getLfdNrUbm();
        neu.bandNr = auftraege.getBandNr();
        neu.fzgArt = auftraege.getFzgArt();
        neu.fhsBaumuster = auftraege.getFhsBaumuster();
        neu.fzgbaumuster = auftraege.getFzgbaumuster();
        neu.anr = auftraege.getAnr();
        neu.verkBez = auftraege.getVerkBez();

        return neu;
    }

    public AuftragTermineDTO createAuftragTermineDTO(AuftragTermine auftragTermine) {
        AuftragTermineDTO neu = new AuftragTermineDTO();
        neu.pnr = auftragTermine.getPnr();
        neu.pat = auftragTermine.getPat();
        neu.sat = auftragTermine.getSat();
        neu.patJul = auftragTermine.getPatJul();
        neu.satJul = auftragTermine.getSatJul();
        neu.ibSperre = auftragTermine.getIbsperre();
        return neu;
    }

}
