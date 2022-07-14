package com.daimler.emst2.fhi.sendung.werk060.check;

import com.daimler.emst2.fhi.constants.AuftragSeqNrEnum;
import com.daimler.emst2.fhi.model.SeverityEnum;
import com.daimler.emst2.fhi.sendung.constants.ProtocolMessageEnum;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.sendung.werk.check.AbstractSendCheck;
import com.daimler.emst2.fhi.sendung.werk.check.SendCheckEnum;
import com.daimler.emst2.fhi.services.AuftragService;

public class CheckSeqNrObergrenze extends AbstractSendCheck {

    public CheckSeqNrObergrenze(ProtocolService pProtocolService, SendCheckEnum sendCheckStepIdentifier) {
        super(pProtocolService, sendCheckStepIdentifier);
    }

    @Override
    protected void init() {
    }

    @Override
    protected boolean doExecuteImpl(SendContext pContext) {
        checkMaxSeqNr(pContext, this.sendCheck.getTyp());

        // ProtocolEntry erzeugen
        getProtocolService().addDebugProtocolEntry(pContext, getIdentifier());
        return true;
    }

    protected void checkMaxSeqNr(SendContext pContext, SendTypeEnum sendTyp) {

        if (sendTyp != SendTypeEnum.RHM) {

            Long seqNrLapu = pContext.service.getAuftragService().getNextSeqNummer(AuftragSeqNrEnum.LAPU);
            Long seqNrSepu = pContext.service.getAuftragService().getNextSeqNummer(AuftragSeqNrEnum.SEPU);
            Long seqNrSitz = pContext.service.getAuftragService().getNextSeqNummer(AuftragSeqNrEnum.SITZ);

            if (seqNrLapu == AuftragService.INVALID_SEQ_NR
                || seqNrSepu == AuftragService.INVALID_SEQ_NR
                || seqNrSitz == AuftragService.INVALID_SEQ_NR) {

                // Obergrenze gerissen
                getProtocolService().addProtocolEntry(pContext,
                        ProtocolMessageEnum.AUFTRAG_SEQNR_OBERGRENZE_VERLETZT_ERR,
                        getIdentifier(),
                        SeverityEnum.ERROR);
            }
        }

    }

}
