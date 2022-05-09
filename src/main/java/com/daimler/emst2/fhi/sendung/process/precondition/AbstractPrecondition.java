package com.daimler.emst2.fhi.sendung.process.precondition;

import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.sendung.process.IProcessContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;


public abstract class AbstractPrecondition<GenPreconditionEnum extends IProcessId, CTX extends IProcessContext>
implements IPrecondition<GenPreconditionEnum, CTX> {

    private final ProtocolService protocolService;

    private final GenPreconditionEnum preconditionEnum;

    public AbstractPrecondition(GenPreconditionEnum pPreconditionEnum,
            ProtocolService pProtocolService) {
        super();
        protocolService = pProtocolService;
        preconditionEnum = pPreconditionEnum;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenPreconditionEnum getIdentifier() {
        return preconditionEnum;
    }

    protected ProtocolService getProtocolService() {
        return protocolService;
    }
}
