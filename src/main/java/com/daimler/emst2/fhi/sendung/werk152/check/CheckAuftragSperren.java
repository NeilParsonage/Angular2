package com.daimler.emst2.fhi.sendung.werk152.check;

import java.util.List;

import org.springframework.util.Assert;

import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.jpa.model.AuftragSperrInformation;
import com.daimler.emst2.fhi.model.Protocol;
import com.daimler.emst2.fhi.model.SeverityEnum;
import com.daimler.emst2.fhi.sendung.constants.ProtocolMessageEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.sendung.werk.check.AbstractSendCheck;
import com.daimler.emst2.fhi.sendung.werk.check.SendCheckEnum;
import com.daimler.emst2.fhi.sendung.werk.precondition.SendPreconditionEnum;

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
        Protocol protocol = pContext.getProtocol();
        Auftraege auftrag = pContext.getAuftrag();

        List<AuftragSperrInformation> sperren = auftrag.meta.getSperrInfos();
        Assert.notNull(sperren, "transiente SperrInfos am Auftrag nicht versorgt");

        if (sperren.isEmpty()) {
            getProtocolService().addDebugProtocolEntry(pContext, getIdentifier());
            return true;
        } else {
            getProtocolService().addProtocolEntry(pContext, ProtocolMessageEnum.AUFTRAG_SPERRE_VORHANDEN,
                    getIdentifier(), SeverityEnum.ERROR);
            return false;
        }
    }

}
