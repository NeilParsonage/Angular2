package com.daimler.emst2.fhi.sendung.check;

import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public class CheckRhmImpliziteTeilsendung extends AbstractCheckImpliziteTeilsendung {

    public CheckRhmImpliziteTeilsendung(ProtocolService pProtocolService) {
        super(pProtocolService);
    }

    @Override
    protected SendCheckEnum getInitCheckEnum() {
        return SendCheckEnum.RHM_IMPLIZITE_TEILSENDUNG;
    }
}
