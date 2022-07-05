package com.daimler.emst2.fhi.sendung.processcommon.action;

import com.daimler.emst2.fhi.jpa.dao.AuftraegeDao;
import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.process.AbstractProcessStep;
import com.daimler.emst2.fhi.sendung.process.action.IAction;
import com.daimler.emst2.fhi.sendung.processcommon.IAuftragProcessContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public class ActionSaveAuftrag<GenActionEnum extends IProcessId> extends
AbstractProcessStep<IProcessId, GenActionEnum, IAuftragProcessContext> implements
IAction<IProcessId, GenActionEnum, IAuftragProcessContext> {

    private final AuftraegeDao auftragDao;

    public ActionSaveAuftrag(GenActionEnum pActionEnum, ProtocolService pProtocolService, AuftraegeDao pAuftragDao) {
        super(pProtocolService);
        setStepIdentifierEnum(pActionEnum);
        auftragDao = pAuftragDao;
    }

    @Override
    protected void init() {
        // no static preconditions
        // (send/storno/..)Action is specified via constructor
    }

    @Override
    protected boolean doExecuteImpl(IAuftragProcessContext pContext) {
        if (!(pContext instanceof SendContext)) {
            throw new RuntimeException("SendContext is Missing");
        }
        SendContext ctx = (SendContext)pContext;

        Auftraege auftrag = pContext.getAuftrag();
        auftragDao.save(auftrag);

        getProtocolService().addDebugProtocolEntry(ctx, getIdentifier());
        return true;
    }
}
