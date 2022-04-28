package com.daimler.emst2.fhi.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.daimler.emst2.fhi.dto.AuftraegeDTO;
import com.daimler.emst2.fhi.dto.AuftragTermineDTO;
import com.daimler.emst2.fhi.dto.AuftragTermineDetailsDTO;
import com.daimler.emst2.fhi.dto.FhiDtoFactory;
import com.daimler.emst2.fhi.dto.SendResponseDTO;
import com.daimler.emst2.fhi.dto.SendungDTO;
import com.daimler.emst2.fhi.jpa.dao.AuftraegeDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragDetailsDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragTermineDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragTermineDetailsDao;
import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.jpa.model.AuftragDetails;
import com.daimler.emst2.fhi.jpa.model.AuftragSperrInformation;
import com.daimler.emst2.fhi.jpa.model.AuftragTermine;
import com.daimler.emst2.fhi.jpa.model.AuftragTermineDetails;
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

    public AuftraegeDTO getAuftragByPnr(String pnr) {
        Optional<Auftraege> result = auftraegeDao.findById(pnr);
        if (ObjectUtils.isEmpty(result)) {

            throw new RuntimeException(String.format("Auftrag %s nicht gefunden!", pnr));
        }

        Optional<AuftragDetails> resultDetails = auftragDetailsDao.findById(pnr);
      
        return dtoFactory.createAuftragDTO(result.get(), resultDetails.get());
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
}
