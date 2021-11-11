package com.daimler.emst2.fhi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daimler.emst2.fhi.dto.AuftraegeDTO;
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

}
