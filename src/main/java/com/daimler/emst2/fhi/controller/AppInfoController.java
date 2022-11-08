package com.daimler.emst2.fhi.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daimler.emst2.fhi.services.KonfigurationService;

@RestController
@RequestMapping(path = AppInfoController.PATH)
public class AppInfoController {
    public static final String PATH = "/pub/app";

    @Autowired
    KonfigurationService konfigurationService;

    @GetMapping("/info")
    public Map<String,String> getAppInfo() {
        Locale defaultLocale = Locale.getDefault();

        Map<String, String> data = new HashMap<>();
        data.put("language", defaultLocale.getLanguage());
        data.put("region", defaultLocale.getCountry());
        data.put("werk", konfigurationService.getWerksId(false));
        data.put("umgebung", konfigurationService.getUmgebung(false));

        return data;
    }

}
