package com.daimler.emst2.fhi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daimler.emst2.fhi.dto.AuftraegeDTO;
import com.daimler.emst2.fhi.dto.AuftragAggregateDTO;
import com.daimler.emst2.fhi.dto.AuftragKabelsaetzeDTO;
import com.daimler.emst2.fhi.dto.AuftragLackeDTO;
import com.daimler.emst2.fhi.dto.AuftragTermineDTO;
import com.daimler.emst2.fhi.dto.AuftragTermineDetailsDTO;
import com.daimler.emst2.fhi.dto.SendResponseDTO;
import com.daimler.emst2.fhi.dto.SendungDTO;
import com.daimler.emst2.fhi.services.AuftraegeService;
import com.daimler.emst2.fhi.services.SendungService;

@RestController
@RequestMapping(path = AuftragController.PATH)
public class AuftragController {
    public static final String PATH = "/pub/auftrag";

    @Autowired
    AuftraegeService auftraegeService;

    @Autowired
    SendungService sendeService;

    @GetMapping("/info")
    public AuftraegeDTO getAuftragbyPnr(@RequestParam String pnr) {
        return auftraegeService.getAuftragByPnr(pnr);
    }

    @GetMapping("/search")
    public AuftraegeDTO getAuftrag(@RequestParam String option, @RequestParam String key) {
        return auftraegeService.getAuftrag(option, key);
    }

    @GetMapping("/termine")
    public AuftragTermineDTO getAuftragTerminebyPnr(@RequestParam String pnr) {
        return auftraegeService.getAuftragTermineByPnr(pnr);
    }

    @GetMapping("/termineDetails")
    public List<AuftragTermineDetailsDTO> getAuftragTermineDetailsbyPnr(@RequestParam String pnr) {
        return auftraegeService.getAuftragTermineDetailsByPnr(pnr);
    }

    @PostMapping("/sendung")
    public SendResponseDTO sendeAuftrag(@RequestBody SendungDTO sendung) {
        return auftraegeService.sendeAuftrag(sendung);
    }

    @GetMapping("/kabelsaetze")
    public List<AuftragKabelsaetzeDTO> getAuftragKabelsaetzebyPnr(@RequestParam String pnr) {
        return auftraegeService.getAuftragKabelsaetzeByPnr(pnr);
    }

    @GetMapping("/aggregate")
    public List<AuftragAggregateDTO> getAuftragAggregatebyPnr(@RequestParam String pnr) {
        return auftraegeService.getAuftragAggregateByPnr(pnr);
    }

    @GetMapping("/fhsLacke")
    public List<AuftragLackeDTO> getAuftragFhsLackebyPnr(@RequestParam String pnr) {
        return auftraegeService.getAuftragFhsLackeByPnr(pnr);
    }

    @GetMapping("/fzgLack")
    public AuftragLackeDTO getAuftragRhmLackbyPnr(@RequestParam String pnr) {
        return auftraegeService.getAuftragRhmLackByPnr(pnr);
    }

    @GetMapping("/listAuftraegebyGesLfdNr")
    public List<AuftraegeDTO> getAuftraegby(@RequestParam int lfdNrGes) {
        long lfdNrGesL = lfdNrGes;
        return auftraegeService.getAuftraegebyLfdNrGes(lfdNrGes);
    }

}
