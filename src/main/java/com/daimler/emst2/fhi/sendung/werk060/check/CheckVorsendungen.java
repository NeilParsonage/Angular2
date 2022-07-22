package com.daimler.emst2.fhi.sendung.werk060.check;

import com.daimler.emst2.fhi.model.SeverityEnum;
import com.daimler.emst2.fhi.sendung.constants.ProtocolMessageEnum;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.sendung.werk.check.AbstractSendCheck;
import com.daimler.emst2.fhi.sendung.werk.check.SendCheckEnum;

public class CheckVorsendungen extends AbstractSendCheck {

    public CheckVorsendungen(ProtocolService pProtocolService, SendCheckEnum sendCheckStepIdentifier) {
        super(pProtocolService, sendCheckStepIdentifier);
    }

    @Override
    protected void init() {
    }

    @Override
    protected boolean doExecuteImpl(SendContext pContext) {
        checkVorsendungen(pContext, this.sendCheck.getTyp());

        // ProtocolEntry erzeugen
        getProtocolService().addDebugProtocolEntry(pContext, getIdentifier());
        return true;
    }

    protected void checkVorsendungen(SendContext pContext, SendTypeEnum sendTyp) {

        long maxVorsendungen = pContext.service.getAuftragService().getMaxVorsendungen();
        long countVorsendungen = pContext.service.getAuftragService().getVorsendungen();

        if (maxVorsendungen >= 0L && maxVorsendungen <= countVorsendungen) {
            // Obergrenze gerissen
            getProtocolService().addProtocolEntry(pContext,
                    ProtocolMessageEnum.AUFTRAG_VORSENDUNGEN_VERLETZT_ERR,
                    getIdentifier(),
                    SeverityEnum.ERROR);
        }
    }
}
