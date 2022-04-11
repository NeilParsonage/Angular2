package com.daimler.emst2.fhi.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daimler.emst2.fhi.dto.SendungDTO;
import com.daimler.emst2.fhi.jpa.dao.AuftraegeDao;
import com.daimler.emst2.fhi.jpa.model.AktiveRestriktion;
import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.jpa.model.KriteriumRelevant;
import com.daimler.emst2.fhi.model.FhiMandantEnum;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.model.ISendService;
import com.daimler.emst2.fhi.sendung.model.MetaList;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.werk060.Sendung060;
import com.daimler.emst2.fhi.util.BasisCollectionUtils;
import com.daimler.emst2.fhi.werk152.Sendung152;
import com.daimler.emst2.frw.context.AuthenticationContext;

@Transactional
@Service
public class SendungService {

    @Autowired
    AuthenticationContext authContext;

    @Autowired
    KonfigurationService configService;

    @Autowired
    RestriktionenService restriktionenService;

    @Autowired
    KriterienService kriterienService;

    @Autowired
    AuftraegeDao auftragDao;

    public SendungDTO senden(SendungDTO sendung) {

        SendTypeEnum sendType = SendTypeEnum.valueOf(sendung.sendeTyp);
        Optional<Auftraege> auftrag = getAuftragByPnr(sendung.pnr);
        if (!auftrag.isPresent()) {
            throw new RuntimeException("Could not find PNR");
        }

        SendContext sendContext = SendContext.create();
        sendContext.mandant = this.configService.getWerksId(true);
        sendContext.mandantEnum = FhiMandantEnum.valueOf(sendContext.mandant);
        sendContext.sendTypeEnum = sendType;
        sendContext.auftrag = auftrag.get();
        sendContext.user = authContext.getAuthentication().getName();

        this.auftragSendungStart(sendContext);
        return null;
    }

    private Optional<Auftraege> getAuftragByPnr(String pnr) {
        return auftragDao.findById(pnr);
    }

    // Auftraege pAuftrag, String mandant, SendTypeEnum sendType
    private void auftragSendungStart(SendContext sendContext) {
        // FhiMandantEnum mandantEnum = FhiMandantEnum.valueOf(mandant);
        // XXX different implmentations for 060 and 521:
        // * in 521: sendung implementation in java - information via protocol, all messages at once
        // * in 060: sendung implementation in plsql - information via dbResult, messages in "salamitaktik" - controlled in continueFunctionContainer
        switch (sendContext.mandantEnum) {
            case WERK_060:
                auftragSendung060Start(sendContext);
                return;
            case WERK_152:
                auftragSendung521Start(sendContext);
                return;
            default:
                throw new RuntimeException("invalid mandant: " + sendContext.mandant);
        }
    }

    private void auftragSendung060Start(SendContext sendContext) {
        // TODO Auto-generated method stub

    }

    private void auftragSendung521Start(SendContext ctx) {
        //*******
        // TODO sendungContext als Model - welches durchgeschleift wird
        //*******
        // update popupData for sendung
        // getSendungPopupManager().init(pAuftrag, sendungEnum);

        // make usecase call and fetch result

        // IUser user = getUserFromContext();
        //IMandantInfo mandant = getMandantInfo();

        MetaList<AktiveRestriktion> aktiveRestriktionenMetaList = restriktionenService.getAllAktiveRestriktionenList();
        ctx.aktiveRestriktionenMetaList = aktiveRestriktionenMetaList;
        //Date letzteAktualisierungRestriktionen = aktiveRestriktionenMetaList.updatedOn;
        //        Date letzteAktualisierungRestriktionen = getUseCaseContext()
        //                .getLetzteAktualisierungAktiveRestriktionenList();

        // fill transient infos to auftrag
        prepareAuftragForSendung521(ctx);
        // IProtocol protocol;
        try {
            doSendung(ctx);
            //            protocol = doSendung(sendungContext, pAuftrag,
            //                    sendType, mandantEnum, letzteAktualisierungRestriktionen,
            //                    user);
        } catch (RuntimeException e) {
            // protocol = handleSendungsException(sendungContext, sendType, e);
            handleSendungsException(ctx, e);
        }
        updatePopupDataAfterSendung(ctx);

    }

    private void updatePopupDataAfterSendung(SendContext ctx) {
        // TODO Auto-generated method stub

    }

    /**
     * Vorbereitung: Sendehierarchie - Sendung - Aktion Sternenhimmelhistorie
     * schreiben
     * @param sendungContext 
     * 
     * @param pAuftrag
     *            auftrag an dem die transiente Liste gefuellt wird
     */
    private void prepareAuftragForSendung521(SendContext ctx) {
        Map<Long, AktiveRestriktion> restIdAktiveRestriktionenMap = getRestIdAktiveRestriktionenMap(ctx);

        //List<KriteriumRelevant> auftragKriterien = pAuftrag.getKriterien();
        List<KriteriumRelevant> auftragKriterien = kriterienService.getKriterienRelevant(ctx.auftrag.getPnr());

        List<AktiveRestriktion> auftragTransList = new ArrayList<AktiveRestriktion>();
        for (KriteriumRelevant iKriteriumRelevant : auftragKriterien) {
            //Long kritId = iKriteriumRelevant.getAktiveRestriktionId();
            Long kritId = iKriteriumRelevant.getRestId();
            AktiveRestriktion aktiveRestriktion = restIdAktiveRestriktionenMap
                    .get(kritId);
            auftragTransList.add(aktiveRestriktion);
        }
        //pAuftrag.setTransAktiveRestriktionList(auftragTransList);
        ctx.auftragAktiveRestriktionList = auftragTransList;
    }

    private Map<Long, AktiveRestriktion> getRestIdAktiveRestriktionenMap(SendContext sendContext) {
        /* Map<Long, AktiveRestriktion> idAktiveRestriktionenMap = getUseCaseContext()
                .getIdAktiveRestriktionenMap();
        if (BasisCollectionUtils.isEmptyOrNull(idAktiveRestriktionenMap)) { */
        //List<AktiveRestriktion> aktiveRestriktionen = getAllAktiveRestriktionenList();
        Map<Long, AktiveRestriktion> idAktiveRestriktionenMap = new HashMap<Long, AktiveRestriktion>();

        List<AktiveRestriktion> aktiveRestriktionen = sendContext.aktiveRestriktionenMetaList.daten;
            if (!BasisCollectionUtils.isEmptyOrNull(aktiveRestriktionen)) {
                for (AktiveRestriktion aktiveRestriktion : aktiveRestriktionen) {
                    idAktiveRestriktionenMap.put(
                            aktiveRestriktion.getRestId(), aktiveRestriktion);
                }
            }
            // }
        return idAktiveRestriktionenMap;
    }

    //private IProtocol handleSendungsException(SendungContext sendungContext, IProcessId pProcessId,
    //        RuntimeException e) {
    private void handleSendungsException(SendContext sendContext,
            RuntimeException e) {
        //doActiveRefresh();
        //String facesMessageTextForException = getExceptionHandler()
        //        .getFacesMessageTextForException(e);
        //IProtocol protocol = getUseCaseSendemaskeService()
        //        .createProtocolForNewMessage(pProcessId,
        //                facesMessageTextForException);
        sendContext.addErrorMessage(e.getMessage());
        ///return protocol;
    }

    //    private IProtocol doSendung(SendungContext sendungContext, Auftraege pAuftrag, SendTypeEnum sendType,
    //            FhiMandantEnum mandantEnum,
    //            Date letzteAktualisierungRestriktionen, String user) {
    private void doSendung(SendContext sendContext) {
        ISendService sendungService = getSendService(sendContext);
        //sendungService.sendeAuftrag(pAuftrag, pLetzteAktualisierungRestriktionen, pKnownProtocol, pUser);
        sendungService.sendeAuftrag(sendContext);
    }

    private ISendService getSendService(SendContext sendContext) {
        switch (sendContext.mandantEnum) {
            case WERK_060:
                return new Sendung060();
            case WERK_152:
                return new Sendung152();
            default:
                throw new RuntimeException("invalid mandant" + sendContext.mandant);
        }
    }



}
