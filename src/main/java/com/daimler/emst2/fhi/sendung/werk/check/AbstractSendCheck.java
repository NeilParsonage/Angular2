package com.daimler.emst2.fhi.sendung.werk.check;

import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.process.AbstractProcessStep;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.sendung.werk.precondition.SendPreconditionEnum;

public abstract class AbstractSendCheck extends
        AbstractProcessStep<SendPreconditionEnum, SendCheckEnum, SendContext> implements ISendCheck {

    protected SendCheckEnum sendCheck;

    protected AbstractSendCheck(ProtocolService pProtocolService) {
        super(pProtocolService);
    }

    protected AbstractSendCheck(ProtocolService pProtocolService, SendCheckEnum sendCheck) {
        super(pProtocolService);
        this.sendCheck = sendCheck;
        setStepIdentifierEnum(sendCheck);
    }
}
