package com.daimler.emst2.fhi.sendung.werk060.check;

import java.util.Date;

import com.daimler.emst2.fhi.jpa.model.Auftrag;
import com.daimler.emst2.fhi.jpa.model.AuftragTermin;
import com.daimler.emst2.fhi.jpa.model.AuftragZeit;
import com.daimler.emst2.fhi.model.SeverityEnum;
import com.daimler.emst2.fhi.sendung.constants.BereichEnum;
import com.daimler.emst2.fhi.sendung.constants.ProtocolMessageEnum;
import com.daimler.emst2.fhi.sendung.constants.SendStatusEnum;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.sendung.werk.check.AbstractSendCheck;
import com.daimler.emst2.fhi.sendung.werk.check.SendCheckEnum;
import com.daimler.emst2.fhi.util.DateTimeHelper;

public class CheckFhiErreichbarkeit extends AbstractSendCheck {

    public CheckFhiErreichbarkeit(ProtocolService pProtocolService, SendCheckEnum sendCheckStepIdentifier) {
        super(pProtocolService, sendCheckStepIdentifier);
    }

    @Override
    protected void init() {
    }

    @Override
    protected boolean doExecuteImpl(SendContext pContext) {
        checkErreichbarkeit(pContext, this.sendCheck.getTyp());

        // ProtocolEntry erzeugen
        getProtocolService().addDebugProtocolEntry(pContext, getIdentifier());
        return true;
    }

    protected void checkErreichbarkeit(SendContext pContext, SendTypeEnum sendTyp) {

        Auftrag auftrag = pContext.auftrag; 

        SendStatusEnum sendStatusEnum = auftrag.meta.getFhiSendStatusEnum();
        
        // Use the SendStatusEnum to reference the "Fhi_Send_Status" Property of Auftrag
        // aus PL/SQL 'FHI-Sendestatus. 0: nicht gesendet; 1: gesendet; 3: storniert';
        // Gesendet appears to mean the same as Plansequenziert !!
        

        if (!sendStatusEnum.equals(SendStatusEnum.PLANSEQUENZIERT)) {

            AuftragZeit auftragZeit = pContext.service.getAuftragService().getFhiZeitForBereich(BereichEnum.LMT);
            Date vorschauzeit = auftragZeit.getFhiZeit();

            AuftragTermin auftragTermin =
                    pContext.service.getAuftragService().getAuftragTerminForPnr(pContext.auftrag.getPnr());
            Date ladispoPlan = auftragTermin.getLadispoPlan();
            if (null == ladispoPlan) {
                ladispoPlan = vorschauzeit;
            }
            
            if (DateTimeHelper.lessThan(vorschauzeit, ladispoPlan)) {

                String[] params = { pContext.auftrag.getPnr() };

                // Erreichbarkeit verpasst
                getProtocolService().addProtocolEntry(pContext,
                        ProtocolMessageEnum.AUFTRAG_ERREICHBARKEIT_VERLETZT_WARN,
                        params,
                        getIdentifier(),
                        SeverityEnum.WARNING);
            }

        }
    }
}
