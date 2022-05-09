package com.daimler.emst2.fhi.sendung.processcommon.action;

import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.model.vorgang.IDbStandardResult;
import com.daimler.emst2.fhi.sendung.process.AbstractProcessStep;
import com.daimler.emst2.fhi.sendung.process.IProcessContext;
import com.daimler.emst2.fhi.sendung.process.action.IAction;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.services.SendemaskeService;

public class ActionSollabstandNeuVorberechnen<GenActionEnum extends IProcessId> extends
        AbstractProcessStep<IProcessId, GenActionEnum, IProcessContext> implements
        IAction<IProcessId, GenActionEnum, IProcessContext> {

    private final SendemaskeService sendemaskeService;

    public ActionSollabstandNeuVorberechnen(GenActionEnum pActionEnum, ProtocolService pProtocolService,
            SendemaskeService pSendemaskeService) {
        super(pProtocolService);
        setStepIdentifierEnum(pActionEnum);
        sendemaskeService = pSendemaskeService;
    }

    @Override
    protected void init() {
        // no static preconditions
        // (send/storno/..)Action is specified via constructor
    }

    @Override
    protected boolean doExecuteImpl(IProcessContext pContext) {
        IDbStandardResult dbStandardResult = sendemaskeService.sollabstaendeNeuBerechnen(pContext.getUser());
        if (dbStandardResult.isSyncOk()) {
            getProtocolService().addDebugProtocolEntry(pContext.getProtocol(), getIdentifier());
            return true;
        }
        throw new RuntimeException(
                "Error trying to refresh pre-calculated 'Sollabstand'-values. More details via Vorgang with id: "
                + dbStandardResult.getVorgangIdLong());
    }
}
