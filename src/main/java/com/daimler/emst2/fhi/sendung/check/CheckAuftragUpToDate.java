package com.daimler.emst2.fhi.sendung.check;

import com.daimler.emst2.fhi.model.Protocol;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.precondition.SendPreconditionEnum;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public class CheckAuftragUpToDate extends AbstractSendCheck {

    public CheckAuftragUpToDate(ProtocolService pProtocolService) {
        super(pProtocolService);
    }

    @Override
    protected void init() {
        addPrecondition(SendPreconditionEnum.AUFTRAG_UPTODATE);
        setStepIdentifierEnum(SendCheckEnum.AUFTRAG_UPTODATE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean doExecuteImpl(SendContext pContext) {
        Protocol protocol = pContext.getProtocol();
        // ProtocolEntry erzeugen
        getProtocolService().addDebugProtocolEntry(pContext, getIdentifier());
        // check is empty - if auftrag is not up to date Precondition will fail earlier
        return true;
    }

}
