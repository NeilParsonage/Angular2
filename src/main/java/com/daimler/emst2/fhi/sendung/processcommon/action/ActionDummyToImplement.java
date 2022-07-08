package com.daimler.emst2.fhi.sendung.processcommon.action;

import java.util.logging.Logger;

import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.sendung.action.SendActionEnum;
import com.daimler.emst2.fhi.sendung.process.AbstractProcessStep;
import com.daimler.emst2.fhi.sendung.process.action.IAction;
import com.daimler.emst2.fhi.sendung.processcommon.IAuftragLfdNrProcessContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public class ActionDummyToImplement<GenPreconditionEnum extends IProcessId, GenActionEnum extends IProcessId, CTX extends IAuftragLfdNrProcessContext>
extends AbstractProcessStep<GenPreconditionEnum, GenActionEnum, CTX>
implements IAction<GenPreconditionEnum, GenActionEnum, CTX> {

    private static final Logger LOG = Logger.getLogger(ActionDummyToImplement.class.getName());

    public ActionDummyToImplement(
            ProtocolService pProtocolService) {
        super(pProtocolService);
        setStepIdentifierEnum(((GenActionEnum)SendActionEnum.UNDEFINED));
    }

    @Override
    protected void init() {
        // no static preconditions
        // sendAction is specified via constructor
    }

    @Override
    protected boolean doExecuteImpl(CTX pContext) {

        // getProtocolService().addDebugProtocolEntry(pContext.getProtocol(), getIdentifier());
        return true;
    }
}
