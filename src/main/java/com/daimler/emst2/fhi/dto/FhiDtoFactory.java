package com.daimler.emst2.fhi.dto;

import org.springframework.stereotype.Component;

import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.jpa.model.AuftragAggregate;
import com.daimler.emst2.fhi.jpa.model.AuftragDetails;
import com.daimler.emst2.fhi.jpa.model.AuftragKabelsaetze;
import com.daimler.emst2.fhi.jpa.model.AuftragLacke;
import com.daimler.emst2.fhi.jpa.model.AuftragSendestatus;
import com.daimler.emst2.fhi.jpa.model.AuftragTermine;
import com.daimler.emst2.fhi.jpa.model.AuftragTermineDetails;

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
        neu.fzgBaumuster = auftraege.getFzgBaumuster();
        neu.fzgTaktklasse = auftraege.getFzgTaktklasse();
        neu.fhsTaktklasse = auftraege.getFhsTaktklasse();
        neu.anr = auftraege.getAnr();
        neu.verkBez = auftraege.getVerkBez();
        neu.bandNrRt2 = auftraege.getBandNrRt2();
        neu.bandNrRt5 = auftraege.getBandNrRt5();
        neu.fin = auftraege.getFin();
        neu.vin = auftraege.getVin();
        return neu;
    }

    public AuftraegeDTO createAuftragDTO(Auftraege auftraege, AuftragDetails details, AuftragSendestatus sendestatus) {
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
        /*
         * Details
         */
        neu.aufaenText = details.getAufaenText();
        neu.bemerkung = details.getBemerkung();
        neu.autor = details.getAutor();
        neu.bemerkungAlt = details.getBemerkungAlt();
        neu.landesCode = details.getLandesCode();
        neu.land = details.getLand();
        neu.gesamtLaenge = details.getGesamtLaenge();
        neu.radStand = details.getRadStand();
        neu.alleCodes = details.getAlleCodes();
        neu.fhiRelCodes = details.getFhiRelCodes();
        neu.bandRelCodes = details.getBandRelCodes();
        neu.alleKrits = details.getAlleKrits();
        neu.fhiRelKrits = details.getFhiRelKrits();
        neu.bandRelKrits = details.getBandRelKrits();

        /*
         * Sendestatus
         */
        neu.zielLapu = sendestatus.getZielLapu();
        neu.zielSepu= sendestatus.getZielSepu();
        neu.skidNr = sendestatus.getSkidNr();
        neu.ort = sendestatus.getOrt();
        neu.hrknr = sendestatus.getHrknr();
        neu.fhiSendStatus = sendestatus.getFhiSendStatus();
        neu.fhisendung = sendestatus.getFhisendung();
        neu.rhmSendStatus = sendestatus.getRhmSendStatus();
        neu.rhmsendung = sendestatus.getFhisendung();
        neu.lmtSendStatus = sendestatus.getLmtSendStatus();
        neu.lmtsendung = sendestatus.getLmtsendung();
        neu.ubmSendStatus = sendestatus.getUbmSendStatus();
        neu.ubmsendung = sendestatus.getUbmSendStatus();
        /*   
         neu.inWarteschlange01 = sendestatus.getInWarteschlange01();
        
        neu.inWarteschlangeTyp = sendestatus.getInWarteschlangeTyp();
        
        neu.inWarteschlangePos= sendestatus.getInWarteschlangePos();
        */
        
        neu.anzahlAnkuendigungen = sendestatus.getAnzahlAnkuendigungen();
        
        neu.anzahlSperren = sendestatus.getAnzahlSperren();
        
        

        neu.fpLmt = sendestatus.getFpLmt();
        /*Fp_Lmt_Datum   */
        neu.fpLmtBenennung = sendestatus.getFpLmtBenennung();
        neu.fpFhs = sendestatus.getFpFhs();
        /*Fp_Fhs_Datum  */
        neu.fpfhsBenennung = sendestatus.getFpFhsBenennung();
        neu.fpRhm = sendestatus.getFpRhm();
        /*Fp_Rhm_Datum  */
        neu.fpRhmBenennung = sendestatus.getFpRhmBenennung();
        neu.sendbar = sendestatus.getSendbar();
        neu.zugebunden = sendestatus.getZugebunden();
        /*
         * Audit 
         */
        neu.fhiAudit = auftraege.getFhiAudit();
        neu.hrkAudit = auftraege.getHrkAudit();
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

    public AuftragLackeDTO createAuftragLackeDTO(AuftragLacke auftragFhsLack) {
        AuftragLackeDTO neu = new AuftragLackeDTO();
        neu.pnr = auftragFhsLack.getPnr();
        neu.lackschl = auftragFhsLack.getLackschl();
        neu.lackLangText = auftragFhsLack.getLackLangText();
        neu.lackSchlNr = auftragFhsLack.getLackSchlNr();
        neu.lackLangText = auftragFhsLack.getLackLangText();
        neu.lackzLangText = auftragFhsLack.getLackzLangText();
        neu.lackzus = auftragFhsLack.getLackzus();

        return neu;
    }

    public AuftragLackeDTO createRhmDefaultLackeDTO(String pnr) {
        AuftragLackeDTO neu = new AuftragLackeDTO();
        neu.pnr = pnr;
        neu.lackschl = "07350";
        neu.lackLangText = "novagrau";

        return neu;
    }

    public AuftragAggregateDTO createAuftragAggregateDTO(AuftragAggregate auftragAggregat) {
        AuftragAggregateDTO neu = new AuftragAggregateDTO();
        neu.aggregat = auftragAggregat.getAggregat();
        neu.pnr = auftragAggregat.getPnr();
        return neu;
    }

    public AuftragKabelsaetzeDTO createAuftragKabelsaetzeDTO(AuftragKabelsaetze auftragKabelsatz) {
        AuftragKabelsaetzeDTO neu = new AuftragKabelsaetzeDTO();
        neu.kabelsatz = auftragKabelsatz.getKabelsatz();
        neu.pnr = auftragKabelsatz.getPnr();
        return neu;
    }

}
