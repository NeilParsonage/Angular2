package com.daimler.emst2.fhi.sendung.check;

import com.daimler.emst2.fhi.model.Protocol;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public class CheckDummyToImplement extends AbstractSendCheck {

    public CheckDummyToImplement(ProtocolService pProtocolService) {
        super(pProtocolService);
    }

    @Override
    protected void init() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean doExecuteImpl(SendContext pContext) {
        Protocol protocol = pContext.getProtocol();
        // ProtocolEntry erzeugen
        // getProtocolService().addDebugProtocolEntry(protocol, getIdentifier());
        // check is empty - if auftrag is not up to date Precondition will fail earlier
        return true;
    }

}
