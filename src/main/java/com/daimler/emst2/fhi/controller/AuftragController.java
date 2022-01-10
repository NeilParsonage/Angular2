package com.daimler.emst2.fhi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daimler.emst2.fhi.dto.AuftraegeDTO;
import com.daimler.emst2.fhi.dto.AuftragTermineDTO;
import com.daimler.emst2.fhi.dto.AuftragTermineDetailsDTO;
import com.daimler.emst2.fhi.services.AuftraegeService;

@RestController
@RequestMapping(path = AuftragController.PATH)
public class AuftragController {
    public static final String PATH = "/pub/auftrag";

    @Autowired
    AuftraegeService auftraegeService;

    @GetMapping("/info")
    public AuftraegeDTO getAuftragbyPnr(@RequestParam String pnr) {
        return auftraegeService.getAuftragByPnr(pnr);
    }

    @GetMapping("/termine")
    public AuftragTermineDTO getAuftragTerminebyPnr(@RequestParam String pnr) {
        return auftraegeService.getAuftragTermineByPnr(pnr);
    }

    @GetMapping("/termineDetails")
    public List<AuftragTermineDetailsDTO> getAuftragTermineDetailsbyPnr(@RequestParam String pnr) {
        return auftraegeService.getAuftragTermineDetailsByPnr(pnr);
    }

}
