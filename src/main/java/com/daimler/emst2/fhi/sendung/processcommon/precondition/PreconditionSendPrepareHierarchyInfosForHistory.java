package com.daimler.emst2.fhi.sendung.processcommon.precondition;

import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.sendung.werk.precondition.SendPreconditionEnum;

public class PreconditionSendPrepareHierarchyInfosForHistory extends AbstractSendPrecondition {

    private final SendTypeEnum sendTypeEnum;

    public PreconditionSendPrepareHierarchyInfosForHistory(SendPreconditionEnum pPreconditionEnum,
            ProtocolService pProtocolService, SendTypeEnum pSendType) {
        super(pPreconditionEnum, pProtocolService);
        sendTypeEnum = pSendType;
    }

    @Override
    public boolean doPrepareContext(SendContext pContext) {
        pContext.addPerformedSendToCollection(getSendTypeEnum());
        return true;
    }

    protected SendTypeEnum getSendTypeEnum() {
        return sendTypeEnum;
    }
}
