package com.daimler.emst2.fhi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daimler.emst2.fhi.dto.AuftragHistorieDTO;
import com.daimler.emst2.fhi.services.AuftragHistorienService;

@RestController
@RequestMapping(path = AuftragHistorieController.PATH)
public class AuftragHistorieController {
    public static final String PATH = "/priv/auftragshistorie";

    @Autowired
    AuftragHistorienService auftragHistorienService;

    @GetMapping("/listAllHistorieAuftraege")
    public List<AuftragHistorieDTO> AlleAuftragHistorie() {
        return auftragHistorienService.getAlleAuftragHistorie();
    }

}
