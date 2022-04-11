package com.daimler.emst2.fhi.sendung.processcommon.precondition;

import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.precondition.SendPreconditionEnum;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.services.AuftraegeService;

public class PreconditionAuftragSperrenundAnkuendigungenUpToDate extends AbstractSendPrecondition {

    AuftraegeService auftragService;

    public PreconditionAuftragSperrenundAnkuendigungenUpToDate(SendPreconditionEnum pCheckPrecondition,
            ProtocolService pProtocolService, AuftraegeService auftragService) {
        super(pCheckPrecondition, pProtocolService);
        this.auftragService = auftragService;
    }

    @Override
    public boolean doPrepareContext(SendContext pContext) {
        Auftraege auftrag = pContext.getAuftrag();
        auftragService.initializeTransientSperrenUndAnkuendigungen(auftrag);

        return true;
    }
}
