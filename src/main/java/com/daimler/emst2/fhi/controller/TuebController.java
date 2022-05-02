package com.daimler.emst2.fhi.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.daimler.emst2.fhi.services.TuebService;


@RestController
@RequestMapping(path = "/priv/tueb")
public class TuebController {

    @Autowired
    TuebService tuebService;

    @GetMapping("/{sprache}")
    public Map<String, String> getAll(@PathVariable String sprache) {
        return tuebService.getTuebs(sprache);
    }

}
