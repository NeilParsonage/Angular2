package com.daimler.emst2.fhi.sendung.werk.check;

import java.util.List;

import org.springframework.util.Assert;

import com.daimler.emst2.fhi.jpa.model.Auftrag;
import com.daimler.emst2.fhi.jpa.model.AuftragSperrInformation;
import com.daimler.emst2.fhi.model.Protocol;
import com.daimler.emst2.fhi.model.SeverityEnum;
import com.daimler.emst2.fhi.sendung.constants.ProtocolMessageEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.sendung.werk.precondition.SendPreconditionEnum;

public class CheckAuftragAnkuendigung extends AbstractSendCheck{

    public CheckAuftragAnkuendigung(ProtocolService pProtocolService) {
        super(pProtocolService);
    }

    @Override
    protected void init() {
        super.addPrecondition(SendPreconditionEnum.AUFTRAG_SPERREN_ANKUENDIGUNGEN_UPTODATE);
        super.setStepIdentifierEnum(SendCheckEnum.AUFTRAG_ANKUENDIGUNG_VORHANDEN);
    }

    @Override
    protected boolean doExecuteImpl(SendContext pContext) {
        Auftrag auftrag = pContext.getAuftrag();
        Protocol protocol = pContext.getProtocol();

        List<AuftragSperrInformation> ankuendigungen = auftrag.meta.getAnkuendigungInfos();
        Assert.notNull(ankuendigungen, "transiente AnkuendigungenInfos am Auftrag nicht versorgt");

        if (ankuendigungen.isEmpty()) {
            getProtocolService().addDebugProtocolEntry(pContext, getIdentifier());
            return true;
        } else {
            getProtocolService().addProtocolEntry(pContext, ProtocolMessageEnum.AUFTRAG_ANKUENDIGUNG_VORHANDEN,
                    getIdentifier(), SeverityEnum.WARNING);
            return false;
        }
    }

}
