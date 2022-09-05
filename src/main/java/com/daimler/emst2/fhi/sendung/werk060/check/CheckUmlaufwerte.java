package com.daimler.emst2.fhi.sendung.werk060.check;

import com.daimler.emst2.fhi.jpa.model.Auftrag;
import com.daimler.emst2.fhi.model.SeverityEnum;
import com.daimler.emst2.fhi.sendung.constants.ProtocolMessageEnum;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.sendung.werk.check.AbstractSendCheck;
import com.daimler.emst2.fhi.sendung.werk.check.SendCheckEnum;

public class CheckUmlaufwerte extends AbstractSendCheck {

    public CheckUmlaufwerte(ProtocolService pProtocolService, SendCheckEnum sendCheckStepIdentifier) {
        super(pProtocolService, sendCheckStepIdentifier);
    }

    @Override
    protected void init() {
    }

    @Override
    protected boolean doExecuteImpl(SendContext pContext) {
        checkUmlaufwerte(pContext, this.sendCheck.getTyp());

        // ProtocolEntry erzeugen
        getProtocolService().addDebugProtocolEntry(pContext, getIdentifier());
        return true;
    }

    protected void checkUmlaufwerte(SendContext pContext, SendTypeEnum sendTyp) {

        Auftrag auftrag = pContext.auftrag;
        if (null == auftrag) {
            return;
        }

        long maxUmlauf = pContext.service.getAuftragService().getUmlaufObergrenze();


        if (maxUmlauf == 0L) {

            getProtocolService().addProtocolEntry(pContext,
                    ProtocolMessageEnum.WARTESCHLANGE_VERLETZT_ERR,
                    getIdentifier(),
                    SeverityEnum.ERROR);

        }
    }
}
