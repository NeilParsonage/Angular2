package com.daimler.emst2.fhi.sendung.werk060.precondition;

import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.process.IProcessContext;
import com.daimler.emst2.fhi.sendung.process.precondition.AbstractPrecondition;
import com.daimler.emst2.fhi.sendung.processcommon.IOrtsdatenProcessContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public class PreconditionAuftragsSperre<GenPreconditionEnum extends IProcessId, CTX extends IProcessContext & IOrtsdatenProcessContext>
extends AbstractPrecondition<GenPreconditionEnum, CTX> {


    private static final String FHI = "FHI";

    private static final String RHM = "RHM";

    private static final String LEER = "";

    public PreconditionAuftragsSperre(GenPreconditionEnum pPreconditionEnum,
            ProtocolService pProtocolService) {
        super(pPreconditionEnum, pProtocolService);
    }

    @Override
    public boolean doPrepareContext(CTX pContext) {
        SendContext sendContext = (SendContext)pContext;

        // Ermittlung der relevanten Sperren und deren Bereiche
        // Es gibt hier 3 Bereiche: FHI, RHM und NULL (Ohne)

        sendContext.anzahlSperrenForBereich =
                sendContext.dao.getAuftragSperrenDao().findSperrenFuerBereich(sendContext.getAuftrag().getPnr());

        return true;
    }

}
