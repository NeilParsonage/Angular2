package com.daimler.emst2.fhi.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.daimler.emst2.fhi.constants.AuftragSeqNrEnum;
import com.daimler.emst2.fhi.constants.FhiSystemwertKeyEnum;
import com.daimler.emst2.fhi.dto.AuftragAenderungstexteDTO;
import com.daimler.emst2.fhi.dto.AuftragAggregateDTO;
import com.daimler.emst2.fhi.dto.AuftragCodesDTO;
import com.daimler.emst2.fhi.dto.AuftragDTO;
import com.daimler.emst2.fhi.dto.AuftragHeberhausDTO;
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
import com.daimler.emst2.fhi.jpa.dao.AuftragAenderungstexteDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragAggregateDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragCodesDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragDetailsDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragHeberhausDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragKabelsaetzeDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragKriterienDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragLackeDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragSendestatusDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragTerminDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragTermineDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragTermineDetailsDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragZeitDao;
import com.daimler.emst2.fhi.jpa.dao.LapuDao;
import com.daimler.emst2.fhi.jpa.dao.OrtReihenfolgeDao;
import com.daimler.emst2.fhi.jpa.dao.SystemwertDao;
import com.daimler.emst2.fhi.jpa.dao.UmlaufWerteDao;
import com.daimler.emst2.fhi.jpa.dao.WarteschlangeDao;
import com.daimler.emst2.fhi.jpa.model.Auftrag;
import com.daimler.emst2.fhi.jpa.model.AuftragAenderungstexte;
import com.daimler.emst2.fhi.jpa.model.AuftragAggregate;
import com.daimler.emst2.fhi.jpa.model.AuftragCodes;
import com.daimler.emst2.fhi.jpa.model.AuftragDetails;
import com.daimler.emst2.fhi.jpa.model.AuftragHeberhaus;
import com.daimler.emst2.fhi.jpa.model.AuftragKabelsaetze;
import com.daimler.emst2.fhi.jpa.model.AuftragKriterien;
import com.daimler.emst2.fhi.jpa.model.AuftragLacke;
import com.daimler.emst2.fhi.jpa.model.AuftragSendestatus;
import com.daimler.emst2.fhi.jpa.model.AuftragSperrInformation;
import com.daimler.emst2.fhi.jpa.model.AuftragTermin;
import com.daimler.emst2.fhi.jpa.model.AuftragTermine;
import com.daimler.emst2.fhi.jpa.model.AuftragTermineDetails;
import com.daimler.emst2.fhi.jpa.model.AuftragZeit;
import com.daimler.emst2.fhi.jpa.model.IAuftragAllHighestSeqNr;
import com.daimler.emst2.fhi.jpa.model.ICountGassenperre;
import com.daimler.emst2.fhi.jpa.model.ICountVorsendungen;
import com.daimler.emst2.fhi.jpa.model.Lapu;
import com.daimler.emst2.fhi.jpa.model.OrtReihenfolge;
import com.daimler.emst2.fhi.jpa.model.UmlaufWerte;
import com.daimler.emst2.fhi.jpa.model.Warteschlange;
import com.daimler.emst2.fhi.sendung.comparators.AuftragAnkuendigungenComparator;
import com.daimler.emst2.fhi.sendung.comparators.AuftragSperrenComparator;
import com.daimler.emst2.fhi.sendung.constants.BereichEnum;
import com.daimler.emst2.fhi.sendung.constants.OrtEnum;
import com.daimler.emst2.fhi.sendung.constants.SperrtypEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.model.SperrenPredicate;
import com.daimler.emst2.fhi.sendung.model.SperrtypPredicate;
import com.daimler.emst2.fhi.sendung.werk.check.SendCheckEnum;


@Service
public class AuftragService {

    private static final String MATERIALBEREICH_LMT = "RHM";
    private static final String MATERIALBEREICH_FHI = "FHI";

    private static final Long DEFAULT_MAX_SEQUENZNUMMER = 999999L;

    public static final Long DEFAULT_ABSTAND_UMLAUF_OBERGRENZE = 999999L;

    private static final Long DEFAULT_MAX_VORSENDUNGEN = 801L;

    private static final Long DEFAULT_MAX_WARTESCHLANGE = 1L;

    private static final Long DEFAULT_GASSE_$_ANZ = 0L;

    public static final Long MIN_SEQ_NR = 1L;

    private static final Long INCR_SEQ_LAPU_SEPU = 1L;

    private static final Long INCR_SEQ_SITZ = 3L;

    public static final Long INVALID_SEQ_NR = -1L;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AuftragDao auftragDao;

    @Autowired
    AuftragDetailsDao auftragDetailsDao;

    @Autowired
    AuftragHeberhausDao auftragHeberhausDao;

    @Autowired
    AuftragTermineDao auftragTermineDao;

    @Autowired
    AuftragTermineDetailsDao auftragTermineDetailsDao;

    @Autowired
    SendungService sendungService;

    @Autowired
    SystemwertDao systemWertDao;

    @Autowired
    OrtReihenfolgeDao ortReihenfolgeDao;

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

    @Autowired
    AuftragAenderungstexteDao auftragAenderungstexteDao;

    @Autowired
    AuftragZeitDao auftragZeitDao;

    @Autowired
    AuftragTerminDao auftragTerminDao;

    @Autowired
    LapuDao lapuDao;

    @Autowired
    WarteschlangeDao warteschlangeDao;

    @Autowired
    UmlaufWerteDao umlaufWerteDao;

    @Autowired
    private KonfigurationService configService;

    public AuftragDTO getAuftragByPnr(String pnr) {
        Optional<Auftrag> result = auftragDao.findById(pnr);
        if (ObjectUtils.isEmpty(result)) {

            throw new RuntimeException(String.format("Auftrag mit PNR %s nicht gefunden!", pnr));
        }

        Optional<AuftragDetails> resultDetails = auftragDetailsDao.findById(pnr);

        Optional<AuftragSendestatus> resultSendestatus = auftragSendestatusDao.findById(pnr);
      
        return dtoFactory.createAuftragDTO(result.get(), resultDetails.get(), resultSendestatus.get());
    }

    public AuftragDTO getAuftragByGesamtLfdNummer(String lfdNummer) {

        Auftrag result = auftragDao.findbyLfdNrGes(Integer.parseInt(lfdNummer));
        if (ObjectUtils.isEmpty(result)) {
    
            throw new RuntimeException(String.format("Auftrag mit Gesamt Lfd Nummer %s nicht gefunden!", lfdNummer));
        }
    
        Optional<AuftragDetails> resultDetails = auftragDetailsDao.findById(result.getPnr());
    
        Optional<AuftragSendestatus> resultSendestatus = auftragSendestatusDao.findById(result.getPnr());
    
           return dtoFactory.createAuftragDTO(result, resultDetails.get(), resultSendestatus.get());
    }

    public AuftragDTO getAuftragByLfdNrLmt(String lfdNummer, String band) {

        Auftrag result = auftragDao.findbyLfdNrLmt(Integer.parseInt(lfdNummer), Integer.parseInt(band));
        if (ObjectUtils.isEmpty(result)) {

            throw new RuntimeException(String.format("Auftrag mit Lfd Nummer Lmt %s nicht gefunden!", lfdNummer));
        }

        Optional<AuftragDetails> resultDetails = auftragDetailsDao.findById(result.getPnr());

        Optional<AuftragSendestatus> resultSendestatus = auftragSendestatusDao.findById(result.getPnr());

        return dtoFactory.createAuftragDTO(result, resultDetails.get(), resultSendestatus.get());
    }

    public AuftragDTO getAuftragByLfdNrFhi(String lfdNummer) {

        Auftrag result = auftragDao.findbyLfdNrFhi(Integer.parseInt(lfdNummer));
        if (ObjectUtils.isEmpty(result)) {

            throw new RuntimeException(String.format("Auftrag mit Lfd Nummer Fhi %s nicht gefunden!", lfdNummer));
        }

        Optional<AuftragDetails> resultDetails = auftragDetailsDao.findById(result.getPnr());

        Optional<AuftragSendestatus> resultSendestatus = auftragSendestatusDao.findById(result.getPnr());

        return dtoFactory.createAuftragDTO(result, resultDetails.get(), resultSendestatus.get());
    }

    public AuftragDTO getAuftrag(String option, String key) {
        AuftragDTO auftrag = new AuftragDTO();

        switch (option) {
            case "pnr":
                return getAuftragByPnr(key);
            case "gesamt":
                return getAuftragByGesamtLfdNummer(key);
            case "fhi":
                return getAuftragByLfdNrFhi(key);
            case "lmt1":
                return getAuftragByLfdNrLmt(key, option.substring(3));
            case "lmt2":
                return getAuftragByLfdNrLmt(key, option.substring(3));
            case "lmt3":
                return getAuftragByLfdNrLmt(key, option.substring(3));
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

        Map<SendCheckEnum, ProtocolEntryDTO> unmodifiableuserProtocolEntrySendChecks =
                Collections.unmodifiableMap(userProtocolEntrySendChecks);

        SendContext ctx = this.sendungService.senden(sendungProtokoll, unmodifiableuserProtocolEntrySendChecks);
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

    public void initializeTransientSperrenUndAnkuendigungen(Auftrag pAuftrag) {
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

    private void initTransientSperrInfoList(Auftrag pAuftrag,
            List<AuftragSperrInformation> sperrenUndAnkuendigungen) {
        // Liste kopieren, damit umsortieren an Hibernate "vorbei" erfolgt
        List<AuftragSperrInformation> sperrenList = new ArrayList<AuftragSperrInformation>(sperrenUndAnkuendigungen);
        CollectionUtils.filter(sperrenList, new SperrtypPredicate(SperrtypEnum.SPERRE));
        Comparator<AuftragSperrInformation> comparator = new AuftragSperrenComparator();
        Collections.sort(sperrenList, comparator);
        pAuftrag.meta.setSperrInfos(sperrenList);
    }

    private void initTransientAnkuendigungInfoList(Auftrag pAuftrag,
            List<AuftragSperrInformation> sperrenUndAnkuendigungen) {
        // Liste kopieren, damit umsortieren an Hibernate "vorbei" erfolgt
        List<AuftragSperrInformation> ankuendigungenList =
                new ArrayList<AuftragSperrInformation>(sperrenUndAnkuendigungen);
        CollectionUtils.filter(ankuendigungenList, new SperrtypPredicate(SperrtypEnum.ANKUENDIGUNG));
        Comparator<AuftragSperrInformation> comparator = new AuftragAnkuendigungenComparator();
        Collections.sort(ankuendigungenList, comparator);
        pAuftrag.meta.setAnkuendigungInfos(ankuendigungenList);
    }

    private void initTransientSperrInfoFHIList(Auftrag pAuftrag,
            List<AuftragSperrInformation> sperrenUndAnkuendigungen) {
        // Liste kopieren, damit umsortieren an Hibernate "vorbei" erfolgt
        List<AuftragSperrInformation> sperrenList = new ArrayList<AuftragSperrInformation>(sperrenUndAnkuendigungen);
        CollectionUtils.filter(sperrenList, new SperrenPredicate(SperrtypEnum.SPERRE, true, MATERIALBEREICH_FHI));
        Comparator<AuftragSperrInformation> comparator = new AuftragSperrenComparator();
        Collections.sort(sperrenList, comparator);
        pAuftrag.meta.setSperrInfosFHI(sperrenList);
    }

    private void initTransientSperrInfoLMTList(Auftrag pAuftrag,
            List<AuftragSperrInformation> sperrenUndAnkuendigungen) {
        // Liste kopieren, damit umsortieren an Hibernate "vorbei" erfolgt
        List<AuftragSperrInformation> sperrenList = new ArrayList<AuftragSperrInformation>(sperrenUndAnkuendigungen);
        CollectionUtils.filter(sperrenList, new SperrenPredicate(SperrtypEnum.SPERRE, true, MATERIALBEREICH_LMT));
        Comparator<AuftragSperrInformation> comparator = new AuftragSperrenComparator();
        Collections.sort(sperrenList, comparator);
        pAuftrag.meta.setSperrInfosLMT(sperrenList);
    }

    private void initTransientSperrInfoSonstList(Auftrag pAuftrag,
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

    public List<AuftragDTO> getAuftraegebyLfdNrGes(int lfdNrGes) {
        List<Auftrag> result = auftragDao.findListAuftraegebyLfdNrGes(lfdNrGes);

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

    public ICountVorsendungen findMaxSeqNummernVonAuftragQuer(BigDecimal seqNrQuer) {
        return auftragDao.findMaxVorsendungen(seqNrQuer);
    }
    
    public IAuftragAllHighestSeqNr findMaxSeqNummernVonAuftrag() {
        return auftragDao.findMaxSeqNrn();
    }

    public Long getNextSeqNummer(AuftragSeqNrEnum auftragSeqNrEnum) {
        IAuftragAllHighestSeqNr auftragAllHighestSeqNr = findMaxSeqNummernVonAuftrag();

        Long nextSeqNr = INVALID_SEQ_NR;
        switch (auftragSeqNrEnum) {
            case LAPU:
                nextSeqNr =
                        nextSeqNrDefaultInc(auftragAllHighestSeqNr.getMaxSeqNrLapu(), MIN_SEQ_NR, INCR_SEQ_LAPU_SEPU);
                break;
            case SEPU:
                nextSeqNr =
                        nextSeqNrDefaultInc(auftragAllHighestSeqNr.getMaxSeqNrSepu(), MIN_SEQ_NR, INCR_SEQ_LAPU_SEPU);

                break;
            case SITZ:
                Long seqnrMs =
                        nextSeqNrDefaultInc(auftragAllHighestSeqNr.getMaxSeqNrMs(), MIN_SEQ_NR, INCR_SEQ_SITZ);

                Long seqnrNs =
                        nextSeqNrDefaultInc(auftragAllHighestSeqNr.getMaxSeqNrNs(), MIN_SEQ_NR, INCR_SEQ_SITZ);

                Long seqnrFs =
                        nextSeqNrDefaultInc(auftragAllHighestSeqNr.getMaxSeqNrFs(), MIN_SEQ_NR, INCR_SEQ_SITZ);

                nextSeqNr = LongStream.of(seqnrMs, seqnrNs, seqnrFs).max().getAsLong();
                break;
            default:
                // Oops Error !!!
                break;
        }

        Long maxSeqNr = getMaxSeqNummer();
        if (nextSeqNr > maxSeqNr) {
            return INVALID_SEQ_NR;
        }
        
        return nextSeqNr;
    }

    public Long getVorsendungen() {

        OrtReihenfolge rtReihenfolgeQuer = ortReihenfolgeDao.findOrtReihenfolgeForOrt(OrtEnum.QUER.getTyp());
        if (ObjectUtils.isEmpty(rtReihenfolgeQuer.getOrtRfFabrik())) {
            throw new RuntimeException(String.format("Ort_Reihenfolge entry for QUER not found"));
        }

        ICountVorsendungen countVorsendungen =
                findMaxSeqNummernVonAuftragQuer((rtReihenfolgeQuer.getOrtRfFabrik()));

        if (ObjectUtils.isEmpty(countVorsendungen.getMaxVorsendungen())) {
            throw new RuntimeException(String.format("Anzahl Vorsendungen kann nicht ermittelt werden"));
        }
        return countVorsendungen.getMaxVorsendungen();
    }

    private Long nextSeqNrDefaultInc(final Long seqNr, final Long defaultValue, final Long increment) {
        return (null == seqNr) ? defaultValue
                : seqNr + increment;
    }
    
    
    public Long getMaxSeqNummer() {
        return configService.getKonfigurationAsLong(FhiSystemwertKeyEnum.MAX_SEQUENZNUMMER,
                DEFAULT_MAX_SEQUENZNUMMER);
    }

    public Long getMaxVorsendungen() {
        return configService.getKonfigurationAsLong(FhiSystemwertKeyEnum.MAX_VORSENDUNGEN,
                DEFAULT_MAX_VORSENDUNGEN);
    }

    public Long getMaxWarteschlange() {
        return configService.getKonfigurationAsLong(FhiSystemwertKeyEnum.MAX_WARTESCHLANGE,
                DEFAULT_MAX_WARTESCHLANGE);
    }

    public Long getUmlaufObergrenze() {
        return configService.getKonfigurationAsLong(FhiSystemwertKeyEnum.ABSTAND_UMLAUF_OBERGRENZE,
                DEFAULT_ABSTAND_UMLAUF_OBERGRENZE);
    }

    public Long getGasse4Anz() {
        return configService.getKonfigurationAsLong(FhiSystemwertKeyEnum.GASSE_4_ANZ,
                DEFAULT_GASSE_$_ANZ);
    }

    public Long getGasse4Max() {
        return configService.getKonfigurationAsLong(FhiSystemwertKeyEnum.GASSE_4_MAX);
    }

    public Long getOgLmtForBandBand(FhiSystemwertKeyEnum key) {
        return configService.getKonfigurationAsLong(key);
    }

    public Warteschlange getWarteschlangeForPnr(final String pnr) {
        return warteschlangeDao.findEntryByPnr(pnr);
    }

    public long getCountWarteschlangeEntries() {
        long c = warteschlangeDao.count();
        return warteschlangeDao.count();
    }

    public AuftragZeit getFhiZeitForBereich(final BereichEnum bereich) {
        return auftragZeitDao.findEntryByBereich(bereich.getTyp());
    }

    public AuftragTermin getAuftragTerminForPnr(final String pnr) {
        //List<AuftragTermin> auftragTerminList = auftragTerminDao.findEntryByPnr(pnr);
        AuftragTermin auftragTermin = auftragTerminDao.findEntryByPnr(pnr);

        if (null != auftragTermin) {
            return auftragTermin;
        }

        /*
        if (null != auftragTerminList && auftragTerminList.size() == 1) {
            return auftragTerminList.get(0);
        }
        */

        throw new RuntimeException("no individual pnr found in Auftrag_Termin Table : " + pnr);

    }

    public ICountGassenperre findCountGassensperre(final String pnr) {
        return lapuDao.findCountGassensperre(pnr);
    }


    public AuftragHeberhausDTO getAuftragHeberhausByPnr(String pnr) {
        AuftragHeberhausDTO auftragHeberhaus = null;
        AuftragHeberhaus result = auftragHeberhausDao.findAuftragHeberhausByPnr(pnr);

        if (ObjectUtils.isEmpty(result)) {
        
            throw new RuntimeException(String.format("Keine Heberhausdaten fuer %s gefunden!", pnr));
        }
        auftragHeberhaus = dtoFactory.createAuftragHeberhausDTO(result);

        return  auftragHeberhaus;
    }
  
    public Lapu findLapuEntryByPnr(String pnr) {
        return lapuDao.findEntryByPnr(pnr);
    }

    public Long getUmlaufwertForBand(Long bandNr)
    {
        UmlaufWerte UmlaufWertForBand = umlaufWerteDao.findUmlaufWertForBand(bandNr);
        return UmlaufWertForBand.getUml();
    }

    public List<AuftragAenderungstexteDTO> getAuftragAenderungstexteByPnr(String pnr) {
        List<AuftragAenderungstexte> result = auftragAenderungstexteDao.findAuftragAenderungstexteByPnr(pnr);

        return result instanceof List
                ? result.stream().map(x -> dtoFactory.createAuftragAenderungstexteDTO(x)).collect(Collectors.toList())
                : Collections.emptyList();
    }

}
