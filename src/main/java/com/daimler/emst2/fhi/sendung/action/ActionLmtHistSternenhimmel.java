package com.daimler.emst2.fhi.sendung.action;

import org.springframework.util.Assert;

import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.model.BandAuswahl;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;


public class ActionLmtHistSternenhimmel extends AbstractActionHistSterneAndCodes {

    public ActionLmtHistSternenhimmel(SendActionEnum pSendActionEnum, ProtocolService pProtocolService) {
        super(pSendActionEnum, pProtocolService);
    }

    @Override
    protected BandAuswahl getHistBandAuswahl(Auftraege pAuftrag) {
        Integer bandnr = pAuftrag.getBandNr();
        Assert.notNull(bandnr);
        return BandAuswahl.getBandAuswahlFromBandNr(bandnr);
    }
}
