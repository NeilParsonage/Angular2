package com.daimler.emst2.fhi.sendung.werk.check;

import com.daimler.emst2.fhi.jpa.model.Auftrag;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public class CheckRhmSollabstand extends AbstractCheckSollabstand {

    public CheckRhmSollabstand(ProtocolService pProtocolService) {
        super(pProtocolService);
    }

    @Override
    protected SendCheckEnum getInitCheckEnum() {
        return SendCheckEnum.RHM_SOLLABSTAND;
    }

    @Override
    protected Integer getSollabstandsVerletzung(Auftrag pAuftrag) {
        return pAuftrag.meta.getTransientSollabstandLmt();
    }

}
