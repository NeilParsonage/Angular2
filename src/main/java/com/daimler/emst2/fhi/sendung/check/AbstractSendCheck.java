package com.daimler.emst2.fhi.sendung.check;

import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.precondition.SendPreconditionEnum;
import com.daimler.emst2.fhi.sendung.process.AbstractProcessStep;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public abstract class AbstractSendCheck extends
        AbstractProcessStep<SendPreconditionEnum, SendCheckEnum, SendContext> implements ISendCheck {

    protected AbstractSendCheck(ProtocolService pProtocolService) {
        super(pProtocolService);
    }
}
