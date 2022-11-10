package com.daimler.emst2.fhi.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daimler.emst2.fhi.dto.AuftragHistorieDTO;
import com.daimler.emst2.fhi.services.AuftragHistorienService;
import com.daimler.emst2.frw.config.AdditionalSort;

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

    @GetMapping
    public Page<AuftragHistorieDTO> getAll(@RequestParam(value = "search", required = false) String search,
            @AdditionalSort("aufPnr") Pageable pageable) throws IOException {
        System.out.println("Hello from Tutorial");
        return auftragHistorienService.getAlleAuftragHistorieBySearchString(search, pageable);
    }

}
