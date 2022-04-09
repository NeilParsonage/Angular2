package com.daimler.emst2.fhi.services;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daimler.emst2.fhi.jpa.dao.TuebDao;
import com.daimler.emst2.fhi.jpa.model.Tueb;

@Service
public class TuebService {

    private static final Logger LOG = Logger.getLogger(TuebService.class.getName());

    @Autowired
    public TuebDao tuebDao;

    @Transactional
    public Map<String, String> getTuebs(String sprache) {
        LOG.info("Start getTuebs()");

        Map<String, String> map = new HashMap<String, String>();

        for (Tueb tueb : tuebDao.findAllBySystemAndPrognameAndTsprache("FHI", "E2", sprache)) {
            map.put(tueb.getTname(), tueb.getTtext());
        }

        return map;
    }

}