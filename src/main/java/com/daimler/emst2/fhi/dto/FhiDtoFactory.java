package com.daimler.emst2.fhi.dto;

import org.springframework.stereotype.Component;

import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.jpa.model.AuftragDetails;
import com.daimler.emst2.fhi.jpa.model.AuftragTermine;
import com.daimler.emst2.fhi.jpa.model.AuftragTermineDetails;

@Component
public class FhiDtoFactory {

    public AuftraegeDTO createAuftragDTO(Auftraege auftraege, AuftragDetails details) {
        AuftraegeDTO neu = new AuftraegeDTO();
        neu.pnr = auftraege.getPnr();
        neu.lfdNrGes = auftraege.getLfdNrGes();
        neu.lfdNrFhi = auftraege.getLfdNrFhi();
        neu.lfdNrLmt = auftraege.getLfdNrLmt();
        neu.lfdNrUbm = auftraege.getLfdNrUbm();
        neu.bandNr = auftraege.getBandNr();
        neu.fzgArt = auftraege.getFzgArt();
        neu.fhsBaumuster = auftraege.getFhsBaumuster();
        neu.fzgBaumuster = auftraege.getFzgBaumuster();
        neu.fzgTaktklasse = auftraege.getFzgTaktklasse();
        neu.fhsTaktklasse = auftraege.getFhsTaktklasse();
        neu.anr = auftraege.getAnr();
        neu.verkBez = auftraege.getVerkBez();
        neu.bandNrRt2 = auftraege.getBandNrRt2();
        neu.bandNrRt5 = auftraege.getBandNrRt5();
        neu.aufaenText = details.getAufaenText();
        neu.bemerkung = details.getBemerkung();
        neu.bemerkungAlt = details.getBemerkungAlt();
        neu.landesCode = details.getLandesCode();
        neu.land = details.getLand();
        neu.gesamtLaenge = details.getGesamtLaenge();
        neu.radStand = details.getRadStand();

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

    public AuftragTermineDetailsDTO createAuftragTermineDetailsDTO(AuftragTermineDetails auftragTermineDetails) {
        AuftragTermineDetailsDTO neu = new AuftragTermineDetailsDTO();
        neu.pnr = auftragTermineDetails.getPnr();
        neu.reihenfolge = auftragTermineDetails.getReihenfolge();
        neu.gewerk = auftragTermineDetails.getGewerk();
        neu.beginnTermin = auftragTermineDetails.getBeginnTermin();
        neu.beginnTyp = auftragTermineDetails.getBeginnTyp();
        neu.istSequenzTermin = auftragTermineDetails.getIstSequenzTermin();
        neu.istSequenzTyp = auftragTermineDetails.getIstSequenzTyp();
        neu.teilsendungTermin = auftragTermineDetails.getTeilsendungTermin();
        neu.stornoTermin = auftragTermineDetails.getStornoTermin();
        return neu;
    }

}
