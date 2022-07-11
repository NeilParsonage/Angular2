package com.daimler.emst2.fhi.sendung.werk.action;

import java.util.Collection;
import java.util.Date;

import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.services.AuftragHistorienService;

/**
 * 
 * @author thb
 */
public class ActionAllSendHistorienEintragSchreiben extends AbstractSendAction {

    private final AuftragHistorienService auftragHistorienService;

    public ActionAllSendHistorienEintragSchreiben(SendActionEnum pSendActionEnum, ProtocolService pProtocolService,
            AuftragHistorienService pAuftragHistorienService) {
        super(pSendActionEnum.getTyp(), pSendActionEnum, pProtocolService);
        auftragHistorienService = pAuftragHistorienService;
    }

    /**
     * @see com.dcx.server.fhi.service.process.AbstractProcessStep#init()
     */
    @Override
    protected void init() {
        // nothing todo
    }

    /**
     * @see
     * com.dcx.server.fhi.service.process.AbstractProcessStep#doExecuteImpl(
     * com.dcx.server.fhi.service.process.IProcessContext)
     */
    @Override
    protected boolean doExecuteImpl(SendContext pContext) {
        Auftraege auftrag = pContext.getAuftrag();
        Collection<SendTypeEnum> performedSendTypes = pContext.getPerformedSendCollection();
        SendTypeEnum performedUserAction = pContext.getSendTypeEnum();
        Date processTimestamp = pContext.getProcessTimestamp();
        auftragHistorienService.createSendungHistorie(performedUserAction, auftrag, performedSendTypes, processTimestamp);
        getProtocolService().addDebugProtocolEntry(pContext, getIdentifier());
        return true;
    }
}
