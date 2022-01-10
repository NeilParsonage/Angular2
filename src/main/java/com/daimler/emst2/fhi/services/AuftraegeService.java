package com.daimler.emst2.fhi.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.daimler.emst2.fhi.dto.AuftraegeDTO;
import com.daimler.emst2.fhi.dto.AuftragTermineDTO;
import com.daimler.emst2.fhi.dto.AuftragTermineDetailsDTO;
import com.daimler.emst2.fhi.dto.FhiDtoFactory;
import com.daimler.emst2.fhi.jpa.dao.AuftraegeDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragTermineDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragTermineDetailsDao;
import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.jpa.model.AuftragTermine;
import com.daimler.emst2.fhi.jpa.model.AuftragTermineDetails;


@Service
public class AuftraegeService {

    private final FhiDtoFactory dtoFactory = new FhiDtoFactory();

    @Autowired
    AuftraegeDao auftraegeDao;

    @Autowired
    AuftragTermineDao auftragTermineDao;

    @Autowired
    AuftragTermineDetailsDao auftragTermineDetailsDao;

    public AuftraegeDTO getAuftragByPnr(String pnr) {
        Optional<Auftraege> result = auftraegeDao.findById(pnr);
        if (ObjectUtils.isEmpty(result)) {

            throw new RuntimeException(String.format("Auftrag %s nicht gefunden!", pnr));
        }
      
        return dtoFactory.createAuftragDTO(result.get());
    }

    public AuftragTermineDTO getAuftragTermineByPnr(String pnr) {
        Optional<AuftragTermine> result = auftragTermineDao.findById(pnr);
        if (ObjectUtils.isEmpty(result)) {

            throw new RuntimeException(String.format("Auftrag %s nicht gefunden!", pnr));
        }

        return dtoFactory.createAuftragTermineDTO(result.get());
    }

    public List<AuftragTermineDetailsDTO> getAuftragTermineDetailsByPnr(String pnr) {
        List<AuftragTermineDetails> result = auftragTermineDetailsDao.findAuftragTermineDetailsByPnr(pnr);

        return result instanceof List
                ? result.stream().map(x -> dtoFactory.createAuftragTermineDetailsDTO(x)).collect(Collectors.toList())
                : Collections.emptyList();
    }

}
