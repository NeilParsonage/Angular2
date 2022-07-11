package com.daimler.emst2.fhi.sendung.werk.check;

import com.daimler.emst2.fhi.jpa.model.Auftrag;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.util.AuftragUtil;

public class CheckUbmSollabstand extends AbstractCheckSollabstand {

    public CheckUbmSollabstand(ProtocolService pProtocolService) {
        super(pProtocolService);
    }

    @Override
    protected SendCheckEnum getInitCheckEnum() {
        return SendCheckEnum.UBM_SOLLABSTAND;
    }

    @Override
    protected Integer getSollabstandsVerletzung(Auftrag pAuftrag) {
        return AuftragUtil.getTransientSollabstandLmt(pAuftrag);
    }

}
