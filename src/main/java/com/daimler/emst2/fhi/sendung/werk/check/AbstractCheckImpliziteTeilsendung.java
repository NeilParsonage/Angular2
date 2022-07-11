package com.daimler.emst2.fhi.sendung.werk.check;

import com.daimler.emst2.fhi.model.Protocol;
import com.daimler.emst2.fhi.model.SeverityEnum;
import com.daimler.emst2.fhi.sendung.constants.ProtocolMessageEnum;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public abstract class AbstractCheckImpliziteTeilsendung extends AbstractSendCheck {

    public AbstractCheckImpliziteTeilsendung(ProtocolService pProtocolService) {
        super(pProtocolService);
    }

    protected abstract SendCheckEnum getInitCheckEnum();

    protected final SendTypeEnum getMySendType() {
        return getInitCheckEnum().getTyp();
    }

    @Override
    protected void init() {
        setStepIdentifierEnum(getInitCheckEnum());
    }


    @Override
    protected boolean doExecuteImpl(SendContext pContext) {
        SendTypeEnum sendTypeEnum = pContext.getSendTypeEnum();
        SendTypeEnum mySendType = getMySendType();
        boolean noWarning = mySendType.equals(sendTypeEnum);
        noWarning |= SendTypeEnum.KOMPLETT.equals(sendTypeEnum);

        Protocol protocol = pContext.getProtocol();
        if (!noWarning) {
            // ProtocolEntry erzeugen
            getProtocolService().addProtocolEntry(pContext, ProtocolMessageEnum.TEILSEND_IMPLICIT,
                    getIdentifier(),
                    SeverityEnum.WARNING);
        }
        else {
            // ProtocolEntry erzeugen
            getProtocolService().addDebugProtocolEntry(pContext, getIdentifier());
        }

        return noWarning;
    }

}