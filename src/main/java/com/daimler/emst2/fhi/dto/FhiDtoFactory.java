package com.daimler.emst2.fhi.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.daimler.emst2.fhi.jpa.model.Auftrag;
import com.daimler.emst2.fhi.jpa.model.AuftragAenderungstexte;
import com.daimler.emst2.fhi.jpa.model.AuftragAggregate;
import com.daimler.emst2.fhi.jpa.model.AuftragCodes;
import com.daimler.emst2.fhi.jpa.model.AuftragDetails;
import com.daimler.emst2.fhi.jpa.model.AuftragHeberhaus;
import com.daimler.emst2.fhi.jpa.model.AuftragHistorieReadOnly;
import com.daimler.emst2.fhi.jpa.model.AuftragKabelsaetze;
import com.daimler.emst2.fhi.jpa.model.AuftragKriterien;
import com.daimler.emst2.fhi.jpa.model.AuftragLacke;
import com.daimler.emst2.fhi.jpa.model.AuftragSendestatus;
import com.daimler.emst2.fhi.jpa.model.AuftragTermine;
import com.daimler.emst2.fhi.jpa.model.AuftragTermineDetails;
import com.daimler.emst2.fhi.jpa.model.VorgaengeMeldungen;
import com.daimler.emst2.fhi.model.Protocol;

@Component
public class FhiDtoFactory {

    public AuftragDTO createAuftragDTO(Auftrag auftrag) {
        AuftragDTO neu = new AuftragDTO();
        neu.pnr = auftrag.getPnr();
        neu.lfdNrGes = auftrag.getLfdNrGes();
        neu.lfdNrFhi = auftrag.getLfdNrFhi();
        neu.lfdNrLmt = auftrag.getLfdNrLmt();
        neu.lfdNrUbm = auftrag.getLfdNrUbm();
        neu.bandNr = Long.valueOf(auftrag.getBandNr());
        neu.fzgArt = auftrag.getFzgArt();
        neu.fhsBaumuster = auftrag.getFhsBaumuster();
        neu.fzgBaumuster = auftrag.getFzgBaumuster();
        neu.fzgTaktklasse = auftrag.getFzgTaktklasse();
        neu.fhsTaktklasse = auftrag.getFhsTaktklasse();
        neu.anr = auftrag.getAnr();
        neu.verkBez = auftrag.getVerkBez();
        neu.bandNrRt2 = auftrag.getBandNrRt2();
        neu.bandNrRt5 = auftrag.getBandNrRt5();
        neu.fin = auftrag.getFin();
        neu.vin = auftrag.getVin();
        neu.version = auftrag.getVersion();

        return neu;
    }

    public AuftragDTO createAuftragDTO(Auftrag auftrag, AuftragDetails details, AuftragSendestatus sendestatus) {
        AuftragDTO neu = new AuftragDTO();
        neu.pnr = auftrag.getPnr();
        neu.lfdNrGes = auftrag.getLfdNrGes();
        neu.lfdNrFhi = auftrag.getLfdNrFhi();
        neu.lfdNrLmt = auftrag.getLfdNrLmt();
        neu.lfdNrUbm = auftrag.getLfdNrUbm();
        neu.bandNr = auftrag.getBandNr();
        neu.fzgArt = auftrag.getFzgArt();
        neu.fhsBaumuster = auftrag.getFhsBaumuster();
        neu.fzgBaumuster = auftrag.getFzgBaumuster();
        neu.fzgTaktklasse = auftrag.getFzgTaktklasse();
        neu.fhsTaktklasse = auftrag.getFhsTaktklasse();
        neu.anr = auftrag.getAnr();
        neu.verkBez = auftrag.getVerkBez();
        neu.bandNrRt2 = auftrag.getBandNrRt2();
        neu.bandNrRt5 = auftrag.getBandNrRt5();
        neu.fin = auftrag.getFin();
        neu.vin = auftrag.getVin();
        neu.version = auftrag.getVersion();
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
        neu.seqnrSepu = auftrag.getSeqnrSepu();
        neu.seqnrLapu = auftrag.getSeqnrLapu();
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
        
        if (auftrag.getFhiAudit() != null)
            neu.fhiAudit = false;
        else {
            neu.fhiAudit = (auftrag.getFhiAudit() == 1);
        }
        if (auftrag.getHrkAudit() != null)
            neu.fhiAudit = false;
        else {
            neu.hrkAudit = (auftrag.getHrkAudit() == 1);
        }  */
        neu.fhiAudit = auftrag.getFhiAudit();
        neu.hrkAudit = auftrag.getHrkAudit();
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

    public SendResponseDTO createSendResponseDTO(SendungDTO sendung,
            List<String> errorMsgs, Protocol protocol) {
        SendResponseDTO neu = new SendResponseDTO();
        neu.sendung = sendung;
        neu.errorMsgs = errorMsgs;
        neu.protocol = protocol;
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

    public AuftragCodesDTO createAuftragCodesDTO(AuftragCodes auftragCode) {
        AuftragCodesDTO neu = new AuftragCodesDTO();
     
        neu.pnr = auftragCode.getPnr();
        neu.code = auftragCode.getCode();
        neu.text = auftragCode.getText();
        neu.relevant = auftragCode.getRelevant();
        
        
        
        return neu;
    }

    public AuftragKriterienDTO createAuftragKriterienDTO(AuftragKriterien auftragKriterium) {
        AuftragKriterienDTO neu = new AuftragKriterienDTO();

        neu.pnr = auftragKriterium.getPnr();
        neu.kriterium = auftragKriterium.getKriterium();
        neu.text = auftragKriterium.getText();
        neu.relevant = auftragKriterium.getRelevant();
        neu.intervall = auftragKriterium.getIntervall();
        neu.dichte = auftragKriterium.getDichte();

        return neu;
    }

    public AuftragHeberhausDTO createAuftragHeberhausDTO(AuftragHeberhaus auftragHeberhaus) {
        AuftragHeberhausDTO neu = new AuftragHeberhausDTO();

        neu.pnr = auftragHeberhaus.getPnr();
        neu.bandNr = auftragHeberhaus.getBandNr();
        neu.terminHeberhaus = auftragHeberhaus.getTerminHeberhaus();
        neu.terminHeberhausTyp = auftragHeberhaus.getTerminHeberhausTyp();
        neu.istPnr = auftragHeberhaus.getIstPnr();
        neu.fertigungspunkt = auftragHeberhaus.getFertigungspunkt();
        neu.istLfdNrLmt = auftragHeberhaus.getIstLfdNrLmt();
        neu.istTermin = auftragHeberhaus.getIstTermin();
        neu.istTerminTyp = auftragHeberhaus.getIstTerminTyp();
        return neu;
    }

    public AuftragAenderungstexteDTO createAuftragAenderungstexteDTO(AuftragAenderungstexte auftragAenderungstext) {
        AuftragAenderungstexteDTO neu = new AuftragAenderungstexteDTO();
        neu.pnr = auftragAenderungstext.getPnr();
        neu.quelle = auftragAenderungstext.getQuelle();
        neu.text = auftragAenderungstext.getText();
        return neu;
    }


    public AuftragHistorieDTO createAuftragHistorieDTO(AuftragHistorieReadOnly auftragHistorieReadOnly) {
        AuftragHistorieDTO neu = new AuftragHistorieDTO();
        neu.pnr = auftragHistorieReadOnly.getAufPnr();
        neu.quelle = auftragHistorieReadOnly.getMelder();
        neu.meldkenn = auftragHistorieReadOnly.getMeldkenn();
        neu.aktion = auftragHistorieReadOnly.getMeldung();
        neu.sendetermin = auftragHistorieReadOnly.getSendetermin();
        neu.zeit = auftragHistorieReadOnly.getZeit();
        neu.bandnr = auftragHistorieReadOnly.getBandnr();
        neu.fzgbm = auftragHistorieReadOnly.getFzgbm();
        neu.ort = auftragHistorieReadOnly.getOrt();
        neu.lfdNrGes = auftragHistorieReadOnly.getLfdNrGes();
        neu.lfdNrFhi = auftragHistorieReadOnly.getLfdNrFhi();
        //neu.lfdNrLmt = auftragHistorieReadOnly.getLfdNrLmt();
        neu.lfdNrRhm = auftragHistorieReadOnly.getLfdNrRhm();
        neu.lfdNrUbm = auftragHistorieReadOnly.getLfdNrUbm();
        neu.pat = auftragHistorieReadOnly.getPat();
        neu.gesLfdSoll = auftragHistorieReadOnly.getLfdNrGes();
        neu.bdLfdSoll = auftragHistorieReadOnly.getBdLfdSoll();
        return neu;
    }

    public StoredProcedureResultDTO createStoredProcecdureResultDTO(Map<String, Long> result) {
        StoredProcedureResultDTO neu = new StoredProcedureResultDTO();
        neu.vorgangId = result.getOrDefault("Po_Vorgang_Id", null);
        neu.status = result.getOrDefault("Po_Status", null);
        return neu;
    }

    public AuftragStoredProcedureResultDTO createAuftragStoredProcecdureResultDTO(
            AuftragDTO auftrag, List<MessageDTO> failMessages) {
        AuftragStoredProcedureResultDTO neu = new AuftragStoredProcedureResultDTO();
        neu.auftrag = auftrag;
        neu.messages = failMessages;
        return neu;
    }

    public MessageDTO createMessageDTO(VorgaengeMeldungen x) {
        MessageDTO neu = new MessageDTO();
        neu.typ = x.getMeldungTyp();
        neu.tuebKey = x.getTuebTname();
        neu.meldung = x.getMeldung();
        if (StringUtils.isNoneBlank(x.getParamstring())) {
            List<String> params = Arrays.stream(x.getParamstring().split("\\,"))
                    .map(str -> str)
                    .collect(Collectors.toList());
            neu.parameter = params;
        }

        return neu;
    }

    public ResponseMessagesDTO createResponseMessages(List<MessageDTO> failMessages) {
        ResponseMessagesDTO neu = new ResponseMessagesDTO();
        neu.messages = failMessages;
        return neu;
    }

}
