package com.daimler.emst2.fhi.sendung.processcommon.precondition;

import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.precondition.SendPreconditionEnum;
import com.daimler.emst2.fhi.sendung.process.precondition.AbstractPrecondition;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public abstract class AbstractSendPrecondition extends AbstractPrecondition<SendPreconditionEnum, SendContext> {

    public AbstractSendPrecondition(SendPreconditionEnum pPreconditionEnum, ProtocolService pProtocolService) {
        super(pPreconditionEnum, pProtocolService);
    }
}
