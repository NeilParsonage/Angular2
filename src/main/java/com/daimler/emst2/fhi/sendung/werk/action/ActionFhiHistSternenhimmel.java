package com.daimler.emst2.fhi.sendung.werk.action;

import com.daimler.emst2.fhi.jpa.model.Auftrag;
import com.daimler.emst2.fhi.model.BandAuswahl;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public class ActionFhiHistSternenhimmel extends AbstractActionHistSterneAndCodes {

    public ActionFhiHistSternenhimmel(SendActionEnum pSendActionEnum, ProtocolService pProtocolService) {
        super(pSendActionEnum, pProtocolService);
    }

    @Override
    protected BandAuswahl getHistBandAuswahl(Auftrag pAuftrag) {
        return BandAuswahl.FHI;
    }

}
