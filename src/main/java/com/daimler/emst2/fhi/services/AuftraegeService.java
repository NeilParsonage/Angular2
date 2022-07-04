package com.daimler.emst2.fhi.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.daimler.emst2.fhi.dto.AuftraegeDTO;
import com.daimler.emst2.fhi.dto.AuftragAggregateDTO;
import com.daimler.emst2.fhi.dto.AuftragCodesDTO;
import com.daimler.emst2.fhi.dto.AuftragKabelsaetzeDTO;
import com.daimler.emst2.fhi.dto.AuftragKriterienDTO;
import com.daimler.emst2.fhi.dto.AuftragLackeDTO;
import com.daimler.emst2.fhi.dto.AuftragTermineDTO;
import com.daimler.emst2.fhi.dto.AuftragTermineDetailsDTO;
import com.daimler.emst2.fhi.dto.FhiDtoFactory;
import com.daimler.emst2.fhi.dto.ProtocolEntryDTO;
import com.daimler.emst2.fhi.dto.SendResponseDTO;
import com.daimler.emst2.fhi.dto.SendungDTO;
import com.daimler.emst2.fhi.dto.SendungsprotokollDTO;
import com.daimler.emst2.fhi.jpa.dao.AuftraegeDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragAggregateDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragCodesDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragDetailsDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragKabelsaetzeDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragKriterienDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragLackeDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragSendestatusDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragTermineDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragTermineDetailsDao;
import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.jpa.model.AuftragAggregate;
import com.daimler.emst2.fhi.jpa.model.AuftragCodes;
import com.daimler.emst2.fhi.jpa.model.AuftragDetails;
import com.daimler.emst2.fhi.jpa.model.AuftragKabelsaetze;
import com.daimler.emst2.fhi.jpa.model.AuftragKriterien;
import com.daimler.emst2.fhi.jpa.model.AuftragLacke;
import com.daimler.emst2.fhi.jpa.model.AuftragSendestatus;
import com.daimler.emst2.fhi.jpa.model.AuftragSperrInformation;
import com.daimler.emst2.fhi.jpa.model.AuftragTermine;
import com.daimler.emst2.fhi.jpa.model.AuftragTermineDetails;
import com.daimler.emst2.fhi.sendung.check.SendCheckEnum;
import com.daimler.emst2.fhi.sendung.comparators.AuftragAnkuendigungenComparator;
import com.daimler.emst2.fhi.sendung.comparators.AuftragSperrenComparator;
import com.daimler.emst2.fhi.sendung.constants.SperrtypEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.model.SperrenPredicate;
import com.daimler.emst2.fhi.sendung.model.SperrtypPredicate;


@Service
public class AuftraegeService {

    private static final String MATERIALBEREICH_LMT = "RHM";
    private static final String MATERIALBEREICH_FHI = "FHI";

    @Autowired
    AuftraegeDao auftraegeDao;

    @Autowired
    AuftragDetailsDao auftragDetailsDao;

    @Autowired
    AuftragTermineDao auftragTermineDao;

    @Autowired
    AuftragTermineDetailsDao auftragTermineDetailsDao;

    @Autowired
    SendungService sendungService;

    @Autowired
    FhiDtoFactory dtoFactory;

    @Autowired
    AuftragKabelsaetzeDao auftragKabelsaetzeDao;

    @Autowired
    AuftragLackeDao auftragLackeDao;

    @Autowired
    AuftragAggregateDao auftragAggregateDao;

    @Autowired
    AuftragSendestatusDao auftragSendestatusDao;

    @Autowired
    AuftragCodesDao auftragCodesDao;

    @Autowired
    AuftragKriterienDao auftragKriterienDao;

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

    public SendResponseDTO sendeAuftrag(SendungDTO sendung) {
        if (StringUtils.isBlank(sendung.pnr)) {
            throw new RuntimeException("PNR kann nicht leer sein");
        }

        SendContext ctx =  this.sendungService.senden(sendung);
        return dtoFactory.createSendResponseDTO(sendung, ctx.getErrorMessages(), ctx.getProtocol());
    }

    public SendResponseDTO sendeAuftragWithProtokoll(SendungsprotokollDTO sendungProtokoll) {
        SendungsprotokollDTO test = sendungProtokoll;

        Map<SendCheckEnum, ProtocolEntryDTO> userProtocolEntrySendChecks =
                createUserProtocolSendCheckEntries(sendungProtokoll);

        SendContext ctx = this.sendungService.senden(sendungProtokoll, userProtocolEntrySendChecks);
        return dtoFactory.createSendResponseDTO(sendungProtokoll, ctx.getErrorMessages(), ctx.getProtocol());
    }

    private Map<SendCheckEnum, ProtocolEntryDTO> createUserProtocolSendCheckEntries(SendungsprotokollDTO sendungProtokoll) {

        Map<SendCheckEnum, ProtocolEntryDTO> userProtocollEntry = new HashMap<SendCheckEnum, ProtocolEntryDTO>();

        sendungProtokoll.protocol.allEntries.forEach(e -> {
            SendCheckEnum entry = SendCheckEnum.getByName(e.taskId);
            if (entry instanceof SendCheckEnum) {
                userProtocollEntry.put(entry, e);
            }
        });
        return userProtocollEntry;
    }

    public void initializeTransientSperrenUndAnkuendigungen(Auftraege pAuftrag) {
        if (!Hibernate.isInitialized(pAuftrag.getSperrInformationen())) {
            // initializeAssociations(pAuftrag, pAuftrag.getSperrInformationen());
            Hibernate.initialize(pAuftrag.getSperrInformationen());
        }
        List<AuftragSperrInformation> sperrenUndAnkuendigungen = pAuftrag.getSperrInformationen();

        initTransientSperrInfoList(pAuftrag, sperrenUndAnkuendigungen);
        initTransientAnkuendigungInfoList(pAuftrag, sperrenUndAnkuendigungen);
        initTransientSperrInfoFHIList(pAuftrag, sperrenUndAnkuendigungen);
        initTransientSperrInfoLMTList(pAuftrag, sperrenUndAnkuendigungen);
        initTransientSperrInfoSonstList(pAuftrag, sperrenUndAnkuendigungen);
    }

    private void initTransientSperrInfoList(Auftraege pAuftrag,
            List<AuftragSperrInformation> sperrenUndAnkuendigungen) {
        // Liste kopieren, damit umsortieren an Hibernate "vorbei" erfolgt
        List<AuftragSperrInformation> sperrenList = new ArrayList<AuftragSperrInformation>(sperrenUndAnkuendigungen);
        CollectionUtils.filter(sperrenList, new SperrtypPredicate(SperrtypEnum.SPERRE));
        Comparator<AuftragSperrInformation> comparator = new AuftragSperrenComparator();
        Collections.sort(sperrenList, comparator);
        pAuftrag.meta.setSperrInfos(sperrenList);
    }

    private void initTransientAnkuendigungInfoList(Auftraege pAuftrag,
            List<AuftragSperrInformation> sperrenUndAnkuendigungen) {
        // Liste kopieren, damit umsortieren an Hibernate "vorbei" erfolgt
        List<AuftragSperrInformation> ankuendigungenList =
                new ArrayList<AuftragSperrInformation>(sperrenUndAnkuendigungen);
        CollectionUtils.filter(ankuendigungenList, new SperrtypPredicate(SperrtypEnum.ANKUENDIGUNG));
        Comparator<AuftragSperrInformation> comparator = new AuftragAnkuendigungenComparator();
        Collections.sort(ankuendigungenList, comparator);
        pAuftrag.meta.setAnkuendigungInfos(ankuendigungenList);
    }

    private void initTransientSperrInfoFHIList(Auftraege pAuftrag,
            List<AuftragSperrInformation> sperrenUndAnkuendigungen) {
        // Liste kopieren, damit umsortieren an Hibernate "vorbei" erfolgt
        List<AuftragSperrInformation> sperrenList = new ArrayList<AuftragSperrInformation>(sperrenUndAnkuendigungen);
        CollectionUtils.filter(sperrenList, new SperrenPredicate(SperrtypEnum.SPERRE, true, MATERIALBEREICH_FHI));
        Comparator<AuftragSperrInformation> comparator = new AuftragSperrenComparator();
        Collections.sort(sperrenList, comparator);
        pAuftrag.meta.setSperrInfosFHI(sperrenList);
    }

    private void initTransientSperrInfoLMTList(Auftraege pAuftrag,
            List<AuftragSperrInformation> sperrenUndAnkuendigungen) {
        // Liste kopieren, damit umsortieren an Hibernate "vorbei" erfolgt
        List<AuftragSperrInformation> sperrenList = new ArrayList<AuftragSperrInformation>(sperrenUndAnkuendigungen);
        CollectionUtils.filter(sperrenList, new SperrenPredicate(SperrtypEnum.SPERRE, true, MATERIALBEREICH_LMT));
        Comparator<AuftragSperrInformation> comparator = new AuftragSperrenComparator();
        Collections.sort(sperrenList, comparator);
        pAuftrag.meta.setSperrInfosLMT(sperrenList);
    }

    private void initTransientSperrInfoSonstList(Auftraege pAuftrag,
            List<AuftragSperrInformation> sperrenUndAnkuendigungen) {
        // Liste kopieren, damit umsortieren an Hibernate "vorbei" erfolgt
        List<AuftragSperrInformation> sperrenList = new ArrayList<AuftragSperrInformation>(sperrenUndAnkuendigungen);
        CollectionUtils.filter(sperrenList, new SperrenPredicate(SperrtypEnum.SPERRE, false, null));
        Comparator<AuftragSperrInformation> comparator = new AuftragSperrenComparator();
        Collections.sort(sperrenList, comparator);
        pAuftrag.meta.setSperrInfosSonst(sperrenList);
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

    public List<AuftragCodesDTO> getAuftragCodes(String pnr) {
        List<AuftragCodes> result = auftragCodesDao.findCodesByPnr(pnr);

        return result instanceof List
                ? result.stream().map(x -> dtoFactory.createAuftragCodesDTO(x)).collect(Collectors.toList())
                : Collections.emptyList();
    }

    public List<AuftragKriterienDTO> getAuftragKriterien(String pnr) {
        List<AuftragKriterien> result = auftragKriterienDao.findKriterienByPnr(pnr);

        return result instanceof List
                ? result.stream().map(x -> dtoFactory.createAuftragKriterienDTO(x)).collect(Collectors.toList())
                : Collections.emptyList();
    }

}
