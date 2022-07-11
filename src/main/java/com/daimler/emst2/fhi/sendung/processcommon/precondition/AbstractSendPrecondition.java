package com.daimler.emst2.fhi.sendung.processcommon.precondition;

import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.process.precondition.AbstractPrecondition;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.sendung.werk.precondition.SendPreconditionEnum;

public abstract class AbstractSendPrecondition extends AbstractPrecondition<SendPreconditionEnum, SendContext> {

    public AbstractSendPrecondition(SendPreconditionEnum pPreconditionEnum, ProtocolService pProtocolService) {
        super(pPreconditionEnum, pProtocolService);
    }
}
