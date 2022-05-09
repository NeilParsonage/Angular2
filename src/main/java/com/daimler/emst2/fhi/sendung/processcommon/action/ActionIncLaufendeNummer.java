package com.daimler.emst2.fhi.sendung.processcommon.action;

import java.util.Map;
import java.util.logging.Logger;

import org.springframework.util.Assert;

import com.daimler.emst2.fhi.constants.FhiSystemwertKeyEnum;
import com.daimler.emst2.fhi.jpa.dao.SystemwerteDao;
import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.jpa.model.Systemwerte;
import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.model.SeverityEnum;
import com.daimler.emst2.fhi.sendung.constants.ProtocolMessageEnum;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.process.AbstractProcessStep;
import com.daimler.emst2.fhi.sendung.process.action.IAction;
import com.daimler.emst2.fhi.sendung.processcommon.IAuftragLfdNrProcessContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.util.AuftragUtil;
import com.daimler.emst2.fhi.util.TaktTelegramUtil;

public class ActionIncLaufendeNummer<GenPreconditionEnum extends IProcessId, GenActionEnum extends IProcessId, CTX extends IAuftragLfdNrProcessContext>
extends AbstractProcessStep<GenPreconditionEnum, GenActionEnum, CTX>
implements IAction<GenPreconditionEnum, GenActionEnum, CTX> {

    private static final Logger LOG = Logger.getLogger(ActionIncLaufendeNummer.class.getName());

    private final SendTypeEnum sendTypeEnum;

    private final SystemwerteDao systemwertDao;

    public ActionIncLaufendeNummer(SendTypeEnum pSendTypeEnum, GenPreconditionEnum pPreconditionEnum,
            GenActionEnum pActionEnum, ProtocolService pProtocolService, SystemwerteDao pSystemwertDao) {
        super(pProtocolService);
        sendTypeEnum = pSendTypeEnum;
        systemwertDao = pSystemwertDao;
        addPrecondition(pPreconditionEnum);
        setStepIdentifierEnum(pActionEnum);
        Assert.isTrue(sendTypeEnum.equals(pActionEnum.getTyp()));
    }

    protected SendTypeEnum getSendTypeEnum() {
        return sendTypeEnum;
    }

    @Override
    protected void init() {
        // no static preconditions
        // sendAction is specified via constructor
    }

    @Override
    protected boolean doExecuteImpl(CTX pContext) {
        Auftraege auftrag = pContext.getAuftrag();
        int bandnr = auftrag.getBandnr().intValue();
        Map<String, Systemwerte> currentLfdNrMap = pContext.getCurrentLfdNrMap();
        FhiSystemwertKeyEnum systemwertKey = FhiSystemwertKeyEnum.getLfdNrKeyFor(getSendTypeEnum(), bandnr);
        String key = systemwertKey.getKey();

        Systemwerte iSystemwert = currentLfdNrMap.get(key);
        if (iSystemwert == null) {
            String sendTypeName = getSendTypeEnum().name();
            String bandnrString = String.valueOf(bandnr);
            getProtocolService().addProtocolEntry(pContext.getProtocol(), ProtocolMessageEnum.ERR_NO_SYSTEMWERT_LFD_NR, new String[] { sendTypeName, bandnrString }, getIdentifier(),
                    SeverityEnum.FATAL);
            throw new RuntimeException("No 'Systemwert' found for 'Sendung' " + sendTypeName + ", bandnr " + bandnrString);
        }

        Long currentLfdNr = iSystemwert.getWertNum();
        Assert.isTrue(currentLfdNr != null && currentLfdNr > 0);
        // neue lfd nr vergeben
        currentLfdNr++;
        TaktTelegramUtil util = new TaktTelegramUtil();
        // check: is (0)000 - representation in telegram - increase to (0)001
        long relevantValue = util.getRelevantValue(getSendTypeEnum(), currentLfdNr);
        if (relevantValue <= 0) {
            currentLfdNr++;
        }

        iSystemwert.setWertNum(currentLfdNr);
        AuftragUtil.setLfdNr(auftrag, getSendTypeEnum(), currentLfdNr);

        // aenderung des vergebnen laufenden nummer speichern, auftrag wird in
        // separater action gespeichert
        try {
            systemwertDao.save(iSystemwert);
        } catch (RuntimeException e) {
            getProtocolService().addProtocolEntry(pContext.getProtocol(), ProtocolMessageEnum.TECHNICAL_ERR, getIdentifier(), SeverityEnum.FATAL);
            LOG.severe("Technischer Fehler beim Aktualisieren des Systemwerts fuer die laufenden Nummern.");
            throw e;
        }

        getProtocolService().addDebugProtocolEntry(pContext.getProtocol(), getIdentifier());
        return true;
    }
}
