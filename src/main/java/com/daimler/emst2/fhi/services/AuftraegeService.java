package com.daimler.emst2.fhi.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.daimler.emst2.fhi.dto.AuftraegeDTO;
import com.daimler.emst2.fhi.dto.AuftragAggregateDTO;
import com.daimler.emst2.fhi.dto.AuftragKabelsaetzeDTO;
import com.daimler.emst2.fhi.dto.AuftragLackeDTO;
import com.daimler.emst2.fhi.dto.AuftragTermineDTO;
import com.daimler.emst2.fhi.dto.AuftragTermineDetailsDTO;
import com.daimler.emst2.fhi.dto.FhiDtoFactory;
import com.daimler.emst2.fhi.jpa.dao.AuftraegeDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragAggregateDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragDetailsDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragKabelsaetzeDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragLackeDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragSendestatusDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragTermineDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragTermineDetailsDao;
import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.jpa.model.AuftragAggregate;
import com.daimler.emst2.fhi.jpa.model.AuftragDetails;
import com.daimler.emst2.fhi.jpa.model.AuftragKabelsaetze;
import com.daimler.emst2.fhi.jpa.model.AuftragLacke;
import com.daimler.emst2.fhi.jpa.model.AuftragSendestatus;
import com.daimler.emst2.fhi.jpa.model.AuftragTermine;
import com.daimler.emst2.fhi.jpa.model.AuftragTermineDetails;


@Service
public class AuftraegeService {

    private final FhiDtoFactory dtoFactory = new FhiDtoFactory();

    @Autowired
    AuftraegeDao auftraegeDao;

    @Autowired
    AuftragDetailsDao auftragDetailsDao;

    @Autowired
    AuftragTermineDao auftragTermineDao;

    @Autowired
    AuftragTermineDetailsDao auftragTermineDetailsDao;

    @Autowired
    AuftragKabelsaetzeDao auftragKabelsaetzeDao;

    @Autowired
    AuftragLackeDao auftragLackeDao;

    @Autowired
    AuftragAggregateDao auftragAggregateDao;

    @Autowired
    AuftragSendestatusDao auftragSendestatusDao;


    public AuftraegeDTO getAuftragByPnr(String pnr) {
        Optional<Auftraege> result = auftraegeDao.findById(pnr);
        if (ObjectUtils.isEmpty(result)) {

            throw new RuntimeException(String.format("Auftrag mit PNR %s nicht gefunden!", pnr));
        }

        Optional<AuftragDetails> resultDetails = auftragDetailsDao.findById(pnr);

        Optional<AuftragSendestatus> resultSendestatus = auftragSendestatusDao.findById(pnr);
      
        return dtoFactory.createAuftragDTO(result.get(), resultDetails.get(), resultSendestatus.get());
    }

    public AuftraegeDTO getAuftragByGesamtLfdNummer(String lfdNummer) {

        Auftraege result = auftraegeDao.findbyLfdNrGes(Integer.parseInt(lfdNummer));
        if (ObjectUtils.isEmpty(result)) {
    
            throw new RuntimeException(String.format("Auftrag mit Gesamt Lfd Nummer %s nicht gefunden!", lfdNummer));
        }
    
        Optional<AuftragDetails> resultDetails = auftragDetailsDao.findById(result.getPnr());
    
        Optional<AuftragSendestatus> resultSendestatus = auftragSendestatusDao.findById(result.getPnr());
    
           return dtoFactory.createAuftragDTO(result, resultDetails.get(), resultSendestatus.get());
    }

    public AuftraegeDTO getAuftragByLfdNrLmt(String lfdNummer) {

        Auftraege result = auftraegeDao.findbyLfdNrLmt(Integer.parseInt(lfdNummer));
        if (ObjectUtils.isEmpty(result)) {

            throw new RuntimeException(String.format("Auftrag mit Lfd Nummer Lmt %s nicht gefunden!", lfdNummer));
        }

        Optional<AuftragDetails> resultDetails = auftragDetailsDao.findById(result.getPnr());

        Optional<AuftragSendestatus> resultSendestatus = auftragSendestatusDao.findById(result.getPnr());

        return dtoFactory.createAuftragDTO(result, resultDetails.get(), resultSendestatus.get());
    }

    public AuftraegeDTO getAuftragByLfdNrFhi(String lfdNummer) {

        Auftraege result = auftraegeDao.findbyLfdNrFhi(Integer.parseInt(lfdNummer));
        if (ObjectUtils.isEmpty(result)) {

            throw new RuntimeException(String.format("Auftrag mit Lfd Nummer Fhi %s nicht gefunden!", lfdNummer));
        }

        Optional<AuftragDetails> resultDetails = auftragDetailsDao.findById(result.getPnr());

        Optional<AuftragSendestatus> resultSendestatus = auftragSendestatusDao.findById(result.getPnr());

        return dtoFactory.createAuftragDTO(result, resultDetails.get(), resultSendestatus.get());
    }

    public AuftraegeDTO getAuftrag(String option, String key) {
        AuftraegeDTO auftrag = new AuftraegeDTO();

        switch (option) {
            case "pnr":
                return getAuftragByPnr(key);
            case "gesamt":
                return getAuftragByGesamtLfdNummer(key);
            case "fhi":
                return getAuftragByLfdNrFhi(key);
            case "lmt":
                return getAuftragByLfdNrLmt(key);
            default:
                return auftrag;
        }

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

    public List<AuftragKabelsaetzeDTO> getAuftragKabelsaetzeByPnr(String pnr) {
        List<AuftragKabelsaetze> result = auftragKabelsaetzeDao.findKabelsaetzeByPnr(pnr);

        return result instanceof List
                ? result.stream().map(x -> dtoFactory.createAuftragKabelsaetzeDTO(x)).collect(Collectors.toList())
                : Collections.emptyList();
    }

    public List<AuftragLackeDTO> getAuftragFhsLackeByPnr(String pnr) {
        List<AuftragLacke> result = auftragLackeDao.findFhsLackeByPnr(pnr);

        return result instanceof List
                ? result.stream().map(x -> dtoFactory.createAuftragLackeDTO(x)).collect(Collectors.toList())
                : Collections.emptyList();
    }

    public AuftragLackeDTO getAuftragRhmLackByPnr(String pnr) {
        AuftragLackeDTO rhmLack;
        Optional<AuftragLacke> result = auftragLackeDao.findRhmLackeByPnr(pnr);

        if (ObjectUtils.isEmpty(result)) {

            rhmLack = dtoFactory.createRhmDefaultLackeDTO(pnr);
        }
        else {
            rhmLack = dtoFactory.createAuftragLackeDTO(result.get());
        }

        return rhmLack;
    }

    public List<AuftragAggregateDTO> getAuftragAggregateByPnr(String pnr) {
        List<AuftragAggregate> result = auftragAggregateDao.findAuftragAggregateByPnr(pnr);

        return result instanceof List
                ? result.stream().map(x -> dtoFactory.createAuftragAggregateDTO(x)).collect(Collectors.toList())
                : Collections.emptyList();
    }

    public List<AuftraegeDTO> getAuftraegebyLfdNrGes(int lfdNrGes) {
        List<Auftraege> result = auftraegeDao.findListAuftraegebyLfdNrGes(lfdNrGes);

        return result instanceof List
                ? result.stream().map(x -> dtoFactory.createAuftragDTO(x)).collect(Collectors.toList())
                : Collections.emptyList();
    }

}
