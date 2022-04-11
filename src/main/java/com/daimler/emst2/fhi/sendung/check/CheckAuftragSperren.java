package com.daimler.emst2.fhi.sendung.check;

import java.util.List;

import org.springframework.util.Assert;

import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.jpa.model.AuftragSperrInformation;
import com.daimler.emst2.fhi.model.IProtocol;
import com.daimler.emst2.fhi.model.SeverityEnum;
import com.daimler.emst2.fhi.sendung.constants.ProtocolMessageEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.precondition.SendPreconditionEnum;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public class CheckAuftragSperren extends AbstractSendCheck {

    public CheckAuftragSperren(ProtocolService pProtocolService) {
        super(pProtocolService);
    }

    @Override
    protected void init() {
        super.addPrecondition(SendPreconditionEnum.AUFTRAG_SPERREN_ANKUENDIGUNGEN_UPTODATE);
        super.setStepIdentifierEnum(SendCheckEnum.AUFTRAG_SPERRE_VORHANDEN);
    }

    @Override
    protected boolean doExecuteImpl(SendContext pContext) {
        IProtocol protocol = pContext.getProtocol();
        Auftraege auftrag = pContext.getAuftrag();

        List<AuftragSperrInformation> sperren = auftrag.meta.getSperrInfos();
        Assert.notNull(sperren, "transiente SperrInfos am Auftrag nicht versorgt");

        if (sperren.isEmpty()) {
            getProtocolService().addDebugProtocolEntry(protocol, getIdentifier());
            return true;
        } else {
            getProtocolService().addProtocolEntry(protocol, ProtocolMessageEnum.AUFTRAG_SPERRE_VORHANDEN, getIdentifier(), SeverityEnum.ERROR);
            return false;
        }
    }

}
