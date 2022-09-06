package com.daimler.emst2.fhi.sendung.werk060.check;

import com.daimler.emst2.fhi.jpa.model.ICountGassenperre;
import com.daimler.emst2.fhi.model.SeverityEnum;
import com.daimler.emst2.fhi.sendung.constants.ProtocolMessageEnum;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.sendung.werk.check.AbstractSendCheck;
import com.daimler.emst2.fhi.sendung.werk.check.SendCheckEnum;

public class CheckFhiGassensperre extends AbstractSendCheck {

    public CheckFhiGassensperre(ProtocolService pProtocolService, SendCheckEnum sendCheckStepIdentifier) {
        super(pProtocolService, sendCheckStepIdentifier);
    }

    @Override
    protected void init() {
    }

    @Override
    protected boolean doExecuteImpl(SendContext pContext) {

        if (pContext.getSendTypeEnum() != SendTypeEnum.FHI) {
            // this check only relevant for a teil-Sendung/FHI
            return true;
        }

        checkGassensperre(pContext, this.sendCheck.getTyp());

        // ProtocolEntry erzeugen
        getProtocolService().addDebugProtocolEntry(pContext, getIdentifier());
        return true;
    }

    protected void checkGassensperre(SendContext pContext, SendTypeEnum sendTyp) {



        ICountGassenperre countGassenperre =
                pContext.service.getAuftragService().findCountGassensperre(pContext.auftrag.getPnr());

        if (null == countGassenperre
            || null == countGassenperre.getGassenSperre()
            || countGassenperre.getGassenSperre() < 1L) {
            // Pnr is not gesperrt so we can ignore the rest of the Prüfung !
            return;
        }

        String[] params = { pContext.auftrag.getPnr() };

        // Erreichbarkeit verpasst
        getProtocolService().addProtocolEntry(pContext,
                ProtocolMessageEnum.AUFTRAG_GASSENSPERRE_VERLETZT_WARN,
                params,
                getIdentifier(),
                SeverityEnum.WARNING);

    }
}
