package com.daimler.emst2.fhi.sendung.processcommon.precondition;

import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.exception.LockAcquisitionException;

import com.daimler.emst2.fhi.jpa.dao.AuftragDao;
import com.daimler.emst2.fhi.jpa.model.Auftrag;
import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.model.Protocol;
import com.daimler.emst2.fhi.model.SeverityEnum;
import com.daimler.emst2.fhi.sendung.constants.ProtocolMessageEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.process.precondition.AbstractPrecondition;
import com.daimler.emst2.fhi.sendung.processcommon.IAuftragLfdNrProcessContext;
import com.daimler.emst2.fhi.sendung.processcommon.IAuftragProcessContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public class PreconditionAuftragUpToDate<GenPreconditionEnum extends IProcessId, CTX extends IAuftragLfdNrProcessContext & IAuftragProcessContext>
extends AbstractPrecondition<GenPreconditionEnum, CTX> {

    private final AuftragDao auftragDao;

    public PreconditionAuftragUpToDate(GenPreconditionEnum pSendActionEnum,
            ProtocolService pProtocolService, AuftragDao pAuftragDao) {
        super(pSendActionEnum, pProtocolService);
        auftragDao = pAuftragDao;
    }

    @Override
    public boolean doPrepareContext(CTX pContext) {
        if (!(pContext instanceof SendContext)) {
            throw new RuntimeException("SendContext is Missing");
        }
        SendContext ctx = (SendContext)pContext;
        Auftrag auftrag = ctx.getAuftrag();
        boolean lockSuccess = false;
        try {
            String lockedPnr = auftragDao.lockAuftragForUpdate(auftrag.getPnr(), auftrag.getVersion());
            lockSuccess = ObjectUtils.anyNotNull(lockedPnr);
        } catch (LockAcquisitionException e) {
            lockSuccess = false;
        }
        // Meldung hier nur falls ein Fehler auftritt
        if (!lockSuccess) {
            Protocol protocol = ctx.getProtocol();
            getProtocolService().addProtocolEntry(ctx, ProtocolMessageEnum.AUFTRAG_OUTDATED, getIdentifier(),
                    SeverityEnum.FATAL);
        }
        return lockSuccess;
    }
}
