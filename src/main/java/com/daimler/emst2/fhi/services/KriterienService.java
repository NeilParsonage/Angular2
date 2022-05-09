package com.daimler.emst2.fhi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daimler.emst2.fhi.jpa.dao.AuftragKriteriumRelevantDao;
import com.daimler.emst2.fhi.jpa.model.KriteriumRelevant;

@Service
public class KriterienService {
    @Autowired
    AuftragKriteriumRelevantDao kriteriumReleavantDao;

    public List<KriteriumRelevant> getKriterienRelevant(String pnr) {
        return this.kriteriumReleavantDao.findAuftragKriteriumRelevantByPnr(pnr);
    }
}
