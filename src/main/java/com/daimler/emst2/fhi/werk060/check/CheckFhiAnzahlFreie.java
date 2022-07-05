package com.daimler.emst2.fhi.werk060.check;

import java.util.StringJoiner;

import com.daimler.emst2.fhi.jpa.model.AuftragSperren;
import com.daimler.emst2.fhi.model.Protocol;
import com.daimler.emst2.fhi.model.SeverityEnum;
import com.daimler.emst2.fhi.sendung.check.AbstractSendCheck;
import com.daimler.emst2.fhi.sendung.check.SendCheckEnum;
import com.daimler.emst2.fhi.sendung.constants.ProtocolMessageEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.precondition.SendPreconditionEnum;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public class CheckFhiAnzahlFreie extends AbstractSendCheck {

    private static final String TEMPLATE_ENTRY = "SPC: %s - SPG: %s - # Freie: %d";

    private static final String SEPARATOR = " --//-- ";

    public CheckFhiAnzahlFreie(ProtocolService pProtocolService) {
        super(pProtocolService);
    }

    @Override
    protected void init() {
        addPrecondition(SendPreconditionEnum.ANZAHL_FREIE_FETCHED);
        setStepIdentifierEnum(SendCheckEnum.AUFTRAG_ANZAHL_FREIE_060);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean doExecuteImpl(SendContext pContext) {

        Protocol protocol = pContext.getProtocol();

        checkAnzahlFreie(pContext);

        // ProtocolEntry erzeugen
        getProtocolService().addDebugProtocolEntry(pContext, getIdentifier());
        return true;
    }

    private void checkAnzahlFreie(SendContext pContext) {

        if (null != pContext && null != pContext.auftragSperrenList && !pContext.auftragSperrenList.isEmpty()) {

            StringJoiner sperren = new StringJoiner(SEPARATOR, SEPARATOR, "");
            for (AuftragSperren auftragSperren : pContext.auftragSperrenList) {
                sperren.add(
                        String.format(TEMPLATE_ENTRY,
                                auftragSperren.getSperrcode(), auftragSperren.getSperrgrund(),
                                auftragSperren.getFreie()));
            }
            final String sperrenResult = sperren.toString();

            getProtocolService().addProtocolEntry(pContext, ProtocolMessageEnum.ANZAHL_FREIE_VERLETZT_WARN,
                    sperrenResult,
                    getIdentifier(),
                    SeverityEnum.WARNING);

        }
    }

}
