package com.daimler.emst2.fhi.sendung.processcommon.precondition;

import com.daimler.emst2.fhi.jpa.model.Auftrag;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.sendung.werk.precondition.SendPreconditionEnum;
import com.daimler.emst2.fhi.services.AuftragService;

public class PreconditionAuftragSperrenundAnkuendigungenUpToDate extends AbstractSendPrecondition {

    AuftragService auftragService;

    public PreconditionAuftragSperrenundAnkuendigungenUpToDate(SendPreconditionEnum pCheckPrecondition,
            ProtocolService pProtocolService, AuftragService auftragService) {
        super(pCheckPrecondition, pProtocolService);
        this.auftragService = auftragService;
    }

    @Override
    public boolean doPrepareContext(SendContext pContext) {
        Auftrag auftrag = pContext.getAuftrag();
        auftragService.initializeTransientSperrenUndAnkuendigungen(auftrag);

        return true;
    }
}
