package com.daimler.emst2.fhi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daimler.emst2.fhi.dto.AuftragAenderungstexteDTO;
import com.daimler.emst2.fhi.dto.AuftragAggregateDTO;
import com.daimler.emst2.fhi.dto.AuftragCodesDTO;
import com.daimler.emst2.fhi.dto.AuftragDTO;
import com.daimler.emst2.fhi.dto.AuftragHeberhausDTO;
import com.daimler.emst2.fhi.dto.AuftragKabelsaetzeDTO;
import com.daimler.emst2.fhi.dto.AuftragKriterienDTO;
import com.daimler.emst2.fhi.dto.AuftragLackeDTO;
import com.daimler.emst2.fhi.dto.AuftragStoredProcedureResultDTO;
import com.daimler.emst2.fhi.dto.AuftragTermineDTO;
import com.daimler.emst2.fhi.dto.AuftragTermineDetailsDTO;
import com.daimler.emst2.fhi.dto.SendResponseDTO;
import com.daimler.emst2.fhi.dto.SendungDTO;
import com.daimler.emst2.fhi.dto.SendungsprotokollDTO;
import com.daimler.emst2.fhi.services.AuftragService;
import com.daimler.emst2.fhi.services.SendungService;
import com.daimler.emst2.frw.model.Privileges;

@RestController
@RequestMapping(path = AuftragController.PATH)
public class AuftragController {
    public static final String PATH = "/pub/auftrag";

    @Autowired
    AuftragService auftragService;

    @Autowired
    SendungService sendeService;

    @GetMapping("/info")
    public AuftragDTO getAuftragbyPnr(@RequestParam String pnr) {
        return auftragService.getAuftragByPnr(pnr);
    }

    @PreAuthorize("hasAnyAuthority('"
                  + Privileges.FHI_ADMIN
                  + "','"
                  + Privileges.FHI_LEITWARTE
                  + "','"
                  + Privileges.FHI_READER
                  + "')")
    @GetMapping("/search")
    public AuftragDTO getAuftrag(@RequestParam String option, @RequestParam String key) {
        return auftragService.getAuftrag(option, key);
    }

    @GetMapping("/termine")
    public AuftragTermineDTO getAuftragTerminebyPnr(@RequestParam String pnr) {
        return auftragService.getAuftragTermineByPnr(pnr);
    }

    @GetMapping("/termineDetails")
    public List<AuftragTermineDetailsDTO> getAuftragTermineDetailsbyPnr(@RequestParam String pnr) {
        return auftragService.getAuftragTermineDetailsByPnr(pnr);
    }

    @PostMapping("/sendung")
    public SendResponseDTO sendeAuftrag(@RequestBody SendungDTO sendung) {
        return auftragService.sendeAuftrag(sendung);
    }

    @PutMapping("/sendung")
    public SendResponseDTO sendeAuftrag(@RequestBody SendungsprotokollDTO sendungProtokoll) {
        return auftragService.sendeAuftragWithProtokoll(sendungProtokoll);
    }

    @GetMapping("/kabelsaetze")
    public List<AuftragKabelsaetzeDTO> getAuftragKabelsaetzebyPnr(@RequestParam String pnr) {
        return auftragService.getAuftragKabelsaetzeByPnr(pnr);
    }

    @GetMapping("/aggregate")
    public List<AuftragAggregateDTO> getAuftragAggregatebyPnr(@RequestParam String pnr) {
        return auftragService.getAuftragAggregateByPnr(pnr);
    }

    @GetMapping("/fhsLacke")
    public List<AuftragLackeDTO> getAuftragFhsLackebyPnr(@RequestParam String pnr) {
        return auftragService.getAuftragFhsLackeByPnr(pnr);
    }

    @GetMapping("/fzgLack")
    public AuftragLackeDTO getAuftragRhmLackbyPnr(@RequestParam String pnr) {
        return auftragService.getAuftragRhmLackByPnr(pnr);
    }

    @GetMapping("/heberhaus")
    public AuftragHeberhausDTO getAuftragHeberhausbyPnr(@RequestParam String pnr) {
        return auftragService.getAuftragHeberhausByPnr(pnr);
    }


    @GetMapping("/aenderungstexte")
    public List<AuftragAenderungstexteDTO> getAuftragAenderungstextebyPnr(@RequestParam String pnr) {
        return auftragService.getAuftragAenderungstexteByPnr(pnr);
    }

    @GetMapping("/listAuftraegebyGesLfdNr")
    public List<AuftragDTO> getAuftraegby(@RequestParam int lfdNrGes) {
        return auftragService.getAuftraegebyLfdNrGes(lfdNrGes);
    }

    @GetMapping("/codes")
    public List<AuftragCodesDTO> getAuftragCodePnr(@RequestParam String pnr) {
        return auftragService.getAuftragCodes(pnr);
    }

    @GetMapping("/kriterien")
    public List<AuftragKriterienDTO> getAuftragKriterienPnr(@RequestParam String pnr) {
        return auftragService.getAuftragKriterien(pnr);
    }

    @PreAuthorize("hasAnyAuthority('" + Privileges.FHI_ADMIN + "','" + Privileges.FHI_LEITWARTE + "')")
    @PostMapping("/aendBemerkung")
    public AuftragStoredProcedureResultDTO editBemerkung(@RequestBody AuftragDTO auftrag) {
        AuftragStoredProcedureResultDTO result = auftragService.editBemerkungAuftrag(auftrag);
        return result;
    }

    @PreAuthorize("hasAnyAuthority('" + Privileges.FHI_ADMIN + "','" + Privileges.FHI_LEITWARTE + "')")
    @PostMapping("/bandwechseln")
    public AuftragStoredProcedureResultDTO Bandwechseln(@RequestBody AuftragDTO auftrag) {
        AuftragStoredProcedureResultDTO result = auftragService.BandwechselnAuftrag(auftrag);
        return result;
    }


}
