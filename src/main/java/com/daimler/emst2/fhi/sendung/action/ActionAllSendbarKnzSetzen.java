package com.daimler.emst2.fhi.sendung.action;

import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.model.Protocol;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.util.SendungUtil;

@Deprecated
public class ActionAllSendbarKnzSetzen extends AbstractSendAction {

    public ActionAllSendbarKnzSetzen(SendActionEnum pSendActionEnum, ProtocolService pProtocolService) {
        super(pSendActionEnum.getTyp(), pSendActionEnum, pProtocolService);
    }

    @Override
    protected boolean doExecuteImpl(SendContext pContext) {
        Protocol protocol = pContext.getProtocol();
        Auftraege auftrag = pContext.getAuftrag();

        boolean isOffen = SendungUtil.isSendungOffen(auftrag, SendTypeEnum.FHI);
        isOffen |= SendungUtil.isSendungOffen(auftrag, SendTypeEnum.LMT);
        isOffen |= SendungUtil.isSendungOffen(auftrag, SendTypeEnum.UBM);
        isOffen |= SendungUtil.isSendungOffen(auftrag, SendTypeEnum.RHM);

        auftrag.meta.setSendbar(isOffen);

        getProtocolService().addDebugProtocolEntry(protocol, getIdentifier());
        return true;
    }

    @Override
    protected void init() {
        // no static preconditions
        // sendAction is specified via constructor
    }
}
