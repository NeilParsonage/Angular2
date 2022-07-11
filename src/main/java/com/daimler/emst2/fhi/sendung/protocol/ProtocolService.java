package com.daimler.emst2.fhi.sendung.protocol;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daimler.emst2.fhi.dto.ProtocolEntryDTO;
import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.model.IProtocolMessage;
import com.daimler.emst2.fhi.model.Protocol;
import com.daimler.emst2.fhi.model.ProtocolEntry;
import com.daimler.emst2.fhi.model.ProtocolMessage;
import com.daimler.emst2.fhi.model.SeverityEnum;
import com.daimler.emst2.fhi.sendung.constants.ProtocolMessageEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.werk.check.SendCheckEnum;
import com.daimler.emst2.fhi.services.LoggingService;
import com.daimler.emst2.fhi.services.UebersetzungService;
import com.daimler.emst2.fhi.util.BasisObjectUtil;

/**
 * session scoped service -
 * 
 * @author THB
 * 
 */
@Service
public class ProtocolService {

    @Autowired
    LoggingService loggingService;

    @Autowired
    UebersetzungService uebersetzungService;

    /*
     * (non-Javadoc)
     * 
     * @see com.dcx.server.fhi.frw.util.IProtocolService#
     */
    public Protocol create(IProcessId pActionId, String pLogAusloeserDetails) {
        Protocol newProtocol =
                new Protocol(pActionId, pLogAusloeserDetails, this.loggingService, this.uebersetzungService);
        newProtocol.setActionId(pActionId);

        return newProtocol;
    }

    /* (non-Javadoc)
     * @see com.dcx.server.fhi.frw.util.IProtocolService#addProtocolEntry(com.dcx.common.fhi.frw.model.IProtocol, com.dcx.common.fhi.frw.constants.ProtocolMessageEnum, com.dcx.common.fhi.frw.model.IProcessId, com.dcx.common.basis.frw.constants.SeverityEnum)
     */
    public void addProtocolEntry(SendContext ctx, ProtocolMessageEnum pMessageEnum,
            IProcessId pTaskId,
            SeverityEnum pSeverityEnum) {
        addProtocolEntry(ctx, pMessageEnum, StringUtils.EMPTY, pTaskId, pSeverityEnum);
    }

    //    public void addProtocolEntry(Protocol pProtocol, ProtocolMessageEnum pMessageEnum,
    //            IProcessId pTaskId,
    //            SeverityEnum pSeverityEnum) {
    //        addProtocolEntry(null, pProtocol, pMessageEnum, StringUtils.EMPTY, pTaskId, pSeverityEnum);
    //    }

    /* (non-Javadoc)
     * @see com.dcx.server.fhi.frw.util.IProtocolService#addProtocolEntry(com.dcx.common.fhi.frw.model.IProtocol, com.dcx.common.fhi.frw.constants.ProtocolMessageEnum, java.lang.String, com.dcx.common.fhi.frw.model.IProcessId, com.dcx.common.basis.frw.constants.SeverityEnum)
     */
    public void addProtocolEntry(SendContext ctx, ProtocolMessageEnum teilsendImplicit,
            String pMessageParam,
            IProcessId pTaskId, SeverityEnum pSeverityEnum) {
        ProtocolEntry newProtocolEntry = createProtocolEntry(teilsendImplicit, pMessageParam, pTaskId,
                pSeverityEnum);
        addEntry(ctx, newProtocolEntry);
    }

    //    public void addProtocolEntry(Protocol protocol, ProtocolMessageEnum teilsendImplicit,
    //            String pMessageParam,
    //            IProcessId pTaskId, SeverityEnum pSeverityEnum) {
    //        addProtocolEntry(null, protocol, teilsendImplicit, pMessageParam, pTaskId, pSeverityEnum);
    //    }


    /* (non-Javadoc)
     * @see com.dcx.server.fhi.frw.util.IProtocolService#addProtocolEntry(com.dcx.common.fhi.frw.model.IProtocol, com.dcx.common.fhi.frw.constants.ProtocolMessageEnum, java.lang.String[], com.dcx.common.fhi.frw.model.IProcessId, com.dcx.common.basis.frw.constants.SeverityEnum)
     */
    public void addProtocolEntry(SendContext ctx, ProtocolMessageEnum pMessageEnum,
            String[] pMessageParamArray,
            IProcessId pTaskId, SeverityEnum pSeverityEnum) {
        ProtocolEntry newProtocolEntry = createProtocolEntry(pMessageEnum, pMessageParamArray, pTaskId, pSeverityEnum);
        addEntry(ctx, newProtocolEntry);
    }

    //    public void addProtocolEntry(Protocol pProtocol, ProtocolMessageEnum pMessageEnum,
    //            String[] pMessageParamArray,
    //            IProcessId pTaskId, SeverityEnum pSeverityEnum) {
    //        addProtocolEntry(null, pProtocol, pMessageEnum, pMessageParamArray, pTaskId, pSeverityEnum);
    //    }

    /* (non-Javadoc)
     * @see com.dcx.server.fhi.frw.util.IProtocolService#createProtocolEntry(com.dcx.common.fhi.frw.constants.ProtocolMessageEnum, java.lang.String, com.dcx.common.fhi.frw.model.IProcessId, com.dcx.common.basis.frw.constants.SeverityEnum)
     */
    public ProtocolEntry createProtocolEntry(ProtocolMessageEnum pMessageEnum, String pMessageParam,
            IProcessId pTaskId, SeverityEnum pSeverityEnum) {
        IProtocolMessage message = new ProtocolMessage(pMessageEnum, new String[] { pMessageParam });
        ProtocolEntry entry = new ProtocolEntry(pTaskId, message, pSeverityEnum);
        return entry;
    }

    /* (non-Javadoc)
     * @see com.dcx.server.fhi.frw.util.IProtocolService#createProtocolEntry(com.dcx.common.fhi.frw.constants.ProtocolMessageEnum, java.lang.String[], com.dcx.common.fhi.frw.model.IProcessId, com.dcx.common.basis.frw.constants.SeverityEnum)
     */
    public ProtocolEntry createProtocolEntry(ProtocolMessageEnum pMessageEnum, String[] pMessageParamArray,
            IProcessId pTaskId, SeverityEnum pSeverityEnum) {
        IProtocolMessage message = new ProtocolMessage(pMessageEnum, pMessageParamArray);
        ProtocolEntry entry = new ProtocolEntry(pTaskId, message, pSeverityEnum);
        return entry;
    }

    /* (non-Javadoc)
     * @see com.dcx.server.fhi.frw.util.IProtocolService#addDebugProtocolEntry(com.dcx.common.fhi.frw.model.IProtocol, com.dcx.common.fhi.frw.model.IProcessId)
     */
    public void addDebugProtocolEntry(SendContext ctx, IProcessId processId) {
        addProtocolEntry(ctx, ProtocolMessageEnum.PROCESSSTEP_INFO_OK, processId, SeverityEnum.DEBUG);
    }

    /* (non-Javadoc)
     * @see com.dcx.server.fhi.frw.util.IProtocolService#completeDbResultProtocol(com.dcx.common.fhi.frw.model.IDbResultWithProtocol, com.dcx.common.fhi.frw.model.IProcessId, com.dcx.common.fhi.frw.model.IProcessId)
     */
    //    public void completeDbResultProtocol(IDbResultWithProtocol dbResult, IProcessId processId,
    //            IProcessId messageVorgangId) {
    //        IMeldungVorgangContainer meldungContainer = (IMeldungVorgangContainer)dbResult;
    //        FrwVorgang meldungVorgang = meldungContainer.getMeldungVorgang();
    //        Protocol protocol = create(processId, "from db");
    //        addDbResultEntry(protocol, messageVorgangId, meldungVorgang);
    //        dbResult.setProtocol(protocol);
    //    }

    /**
     * Lies die aktuelle "Meldung" aus dem DbResult und erzeuge dafür einen neuen Protokoll-Eintrag.
     * Der neue Eintrag wird direkt dem Protokoll hinzugefügt.
     * 
     * @param pProtocol
     * @param dbResult
     */
    //    private void addDbResultEntry(Protocol pProtocol, IProcessId processId, FrwVorgang pVorgang) {
    //        if (pVorgang == null) {
    //            return;
    //        }
    //
    //        String tuebKey = pVorgang.getTuebTname();
    //        String[] messageParams = pVorgang.getMessageParams();
    //        String fallbackMeldungFromDb = pVorgang.getMeldung();
    //        Integer meldungNr = pVorgang.getMeldungNr();
    //        SeverityEnum severity = SeverityEnum.getEnumByString(pVorgang.getMeldungTyp());
    //
    //        final boolean isTechnicalIssue = SeverityEnum.ERROR.equals(severity) && StringUtils.isEmpty(tuebKey);
    //        if (isTechnicalIssue) {
    //            if (StringUtils.isNotEmpty(fallbackMeldungFromDb)) {
    //                tuebKey = ProtocolMessageEnum.TECHNICAL_ERR_WITH_PARAM.getDefaultTuebKey();
    //                messageParams = new String[] { fallbackMeldungFromDb };
    //            } else {
    //                tuebKey = ProtocolMessageEnum.TECHNICAL_ERR.getDefaultTuebKey();
    //            }
    //        }
    //
    //        ProtocolEntry entry = createProtocolEntry(processId, tuebKey, messageParams, severity, meldungNr);
    //        addEntry(pProtocol, entry);
    //    }


    /* (non-Javadoc)
     * @see com.dcx.server.fhi.frw.util.IProtocolService#addInfoProtocolEntry(com.dcx.common.fhi.frw.model.IProtocol, com.dcx.common.fhi.frw.model.IProcessId, java.lang.String, java.lang.String)
     */
    //    public void addInfoProtocolEntry(Protocol pProtocol, IProcessId processId, String tuebKey, String param) {
    //        ProtocolEntry entry = createProtocolEntry(processId, tuebKey, new String[] { param }, SeverityEnum.INFO, null);
    //        addEntry(pProtocol, entry);
    //    }

    private void addEntry(SendContext ctx, ProtocolEntry entry) {
        // pProtocol.addEntry(entry);
        addOrStepOverEntry(entry, ctx);
    }

    //    private ProtocolEntry createProtocolEntry(IProcessId processId, String tuebKey, String[] messageParams,
    //            SeverityEnum severity, Integer meldungNr) {
    //        IProtocolMessage protocolMessage = new TuebMessageWithParams(tuebKey, messageParams);
    //        ProtocolEntry entry = new ProtocolEntry(processId, protocolMessage, severity, meldungNr);
    //        return entry;
    //    }


    // TODO JHE addOrStepOver(entry)
    //private boolean processCheckUserAcknowleged(ICheck iCheck, SendContext ctx) {
    private boolean addOrStepOverEntry(ProtocolEntry newEntry, SendContext ctx) {

        if (BasisObjectUtil.isEmptyOrNull(ctx) || !(newEntry.getTaskId() instanceof SendCheckEnum)) {
            ctx.getProtocol().addEntry(newEntry);
            return false;
        }
        SendCheckEnum iCheck = (SendCheckEnum)newEntry.getTaskId();

        if (BasisObjectUtil.isEmptyOrNull(ctx.userProtocolSendChecks)) {
            ctx.getProtocol().addEntry(newEntry);
            return false;
        }

        ProtocolEntryDTO userProtocolEntry = ctx.userProtocolSendChecks.get(iCheck);

        if (BasisObjectUtil.isEmptyOrNull(userProtocolEntry)) {
            ctx.getProtocol().addEntry(newEntry);
            return false;
        }

        if (BooleanUtils.isNotTrue(SeverityEnum.WARNING.equals(newEntry.getSeverity()))
                ||  Boolean.FALSE.equals(userProtocolEntry.userAcknowledged)) {
            ctx.getProtocol().addEntry(newEntry);
            return false;
        }


        // process user acknowledged sendcheck
        //        ProtocolMessageEnum protocolMessageEnum =
        //                ProtocolMessageEnum.getEnum(userProtocolEntry.protocolMessage.protocolMessageEnum);
        //
        //        List<String> paramsList = userProtocolEntry.protocolMessage.parameter;
        //        String[] paramsArray = new String[paramsList.size()];
        //        paramsArray = paramsList.toArray(paramsArray);
        //
        //        SendCheckEnum taskId = SendCheckEnum.valueOf(userProtocolEntry.taskId);
        //
        //        SeverityEnum servity = SeverityEnum.valueOf(userProtocolEntry.severity);
        //
        //        IProtocolMessage message =
        //                new ProtocolMessage(protocolMessageEnum, paramsArray);
        //        ProtocolEntry entry = new ProtocolEntry(taskId, message, servity);
        //
        //        entry.setUserAcknowledged(true);
        //        
        //        ctx.getProtocol().addEntry(entry);

        newEntry.setUserAcknowledged(true);
        ctx.getProtocol().addEntry(newEntry);
        return true;
    }


    //    private boolean processCheckUserAcknowleged(ICheck iCheck, SendContext ctx) {
    //
    //        if (BasisObjectUtil.isEmptyOrNull(ctx.userProtocolSendChecks)) {
    //            return false;
    //        }
    //        ProtocolEntryDTO userProtocolEntry = ctx.userProtocolSendChecks.get(iCheck.getIdentifier());
    //
    //        if (BasisObjectUtil.isEmptyOrNull(userProtocolEntry)) {
    //            return false;
    //        }
    //
    //        if (Boolean.FALSE.equals(userProtocolEntry.userAcknowledged)) {
    //            return false;
    //        }
    //
    //        // process user acknowledged sendcheck
    //        ProtocolMessageEnum protocolMessageEnum =
    //                ProtocolMessageEnum.getEnum(userProtocolEntry.protocolMessage.protocolMessageEnum);
    //
    //        List<String> paramsList = userProtocolEntry.protocolMessage.parameter;
    //        String[] paramsArray = new String[paramsList.size()];
    //        paramsArray = paramsList.toArray(paramsArray);
    //
    //        SendCheckEnum taskId = SendCheckEnum.valueOf(userProtocolEntry.taskId);
    //
    //        SeverityEnum servity = SeverityEnum.valueOf(userProtocolEntry.severity);
    //
    //        IProtocolMessage message =
    //                new ProtocolMessage(protocolMessageEnum, paramsArray);
    //        ProtocolEntry entry = new ProtocolEntry(taskId, message, servity);
    //        ctx.getProtocol().addEntry(entry);
    //
    //        return true;
    //    }

}
