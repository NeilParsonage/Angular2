package com.daimler.emst2.fhi.sendung.processcommon.precondition;

import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.precondition.SendPreconditionEnum;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

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
