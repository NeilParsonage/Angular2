package com.daimler.emst2.fhi.sendung.check;

import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public class CheckUbmImpliziteTeilsendung extends AbstractCheckImpliziteTeilsendung {

    public CheckUbmImpliziteTeilsendung(ProtocolService pProtocolService) {
        super(pProtocolService);
    }

    @Override
    protected SendCheckEnum getInitCheckEnum() {
        return SendCheckEnum.UBM_IMPLIZITE_TEILSENDUNG;
    }
}
