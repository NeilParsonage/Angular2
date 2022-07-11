package com.daimler.emst2.fhi.sendung.werk.action;

import org.springframework.util.Assert;

import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.process.AbstractProcessStep;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.sendung.werk.precondition.SendPreconditionEnum;

/**
 * the abstact class AbstractSendAcion implements a constructor that stores the given {@link SendTypeEnum} and checks if the
 * given {@link SendActionEnum} corresponds to the send type.
 * 
 * @author thb
 */
public abstract class AbstractSendAction extends AbstractProcessStep<SendPreconditionEnum, SendActionEnum, SendContext>
        implements ISendAction {

    private final SendTypeEnum sendTypeEnum;

    public AbstractSendAction(SendTypeEnum pSendTypeEnum, SendActionEnum pSendActionEnum,
            ProtocolService pProtocolService) {
        super(pProtocolService);
        sendTypeEnum = pSendTypeEnum;
        super.setStepIdentifierEnum(pSendActionEnum);
        Assert.isTrue(sendTypeEnum.equals(pSendActionEnum.getTyp()));
    }

    protected SendTypeEnum getSendTypeEnum() {
        return sendTypeEnum;
    }
}
