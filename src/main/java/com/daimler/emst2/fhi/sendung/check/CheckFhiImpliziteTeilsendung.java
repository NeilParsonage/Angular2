package com.daimler.emst2.fhi.sendung.check;

import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public class CheckFhiImpliziteTeilsendung extends AbstractCheckImpliziteTeilsendung {

    public CheckFhiImpliziteTeilsendung(ProtocolService pProtocolService) {
        super(pProtocolService);
    }

    @Override
    protected SendCheckEnum getInitCheckEnum() {
        return SendCheckEnum.FHI_IMPLIZITE_TEILSENDUNG;
    }
}
