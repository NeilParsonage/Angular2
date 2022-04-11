package com.daimler.emst2.fhi.sendung.check;

import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public class CheckLmtSollabstand extends AbstractCheckSollabstand {

    public CheckLmtSollabstand(ProtocolService pProtocolService) {
        super(pProtocolService);
    }

    @Override
    protected SendCheckEnum getInitCheckEnum() {
        return SendCheckEnum.LMT_SOLLABSTAND;
    }

    @Override
    protected Integer getSollabstandsVerletzung(Auftraege pAuftrag) {
        return pAuftrag.meta.getTransientSollabstandLmt();
    }

}
