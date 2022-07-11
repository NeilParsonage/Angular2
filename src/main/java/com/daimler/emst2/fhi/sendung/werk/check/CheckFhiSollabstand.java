package com.daimler.emst2.fhi.sendung.werk.check;

import com.daimler.emst2.fhi.jpa.model.Auftrag;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public class CheckFhiSollabstand extends AbstractCheckSollabstand {

    public CheckFhiSollabstand(ProtocolService pProtocolService) {
        super(pProtocolService);
    }

    @Override
    protected SendCheckEnum getInitCheckEnum() {
        return SendCheckEnum.FHI_SOLLABSTAND;
    }

    @Override
    protected Integer getSollabstandsVerletzung(Auftrag pAuftrag) {
        return pAuftrag.meta.getTransientSollabstandFhi();
    }
}
