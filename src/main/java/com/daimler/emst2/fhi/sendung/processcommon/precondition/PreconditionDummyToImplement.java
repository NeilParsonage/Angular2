package com.daimler.emst2.fhi.sendung.processcommon.precondition;

import com.daimler.emst2.fhi.jpa.dao.AuftraegeDao;
import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.sendung.process.precondition.AbstractPrecondition;
import com.daimler.emst2.fhi.sendung.processcommon.IAuftragLfdNrProcessContext;
import com.daimler.emst2.fhi.sendung.processcommon.IAuftragProcessContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public class PreconditionDummyToImplement<GenPreconditionEnum extends IProcessId, CTX extends IAuftragLfdNrProcessContext & IAuftragProcessContext>
extends AbstractPrecondition<GenPreconditionEnum, CTX> {

    private final AuftraegeDao auftragDao;

    public PreconditionDummyToImplement(GenPreconditionEnum pSendActionEnum,
            ProtocolService pProtocolService, AuftraegeDao pAuftragDao) {
        super(pSendActionEnum, pProtocolService);
        auftragDao = pAuftragDao;
    }

    @Override
    public boolean doPrepareContext(CTX pContext) {
        return true;
    }
}
