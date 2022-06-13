package com.daimler.emst2.fhi.werk060.check;

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

    private static final String SPC_PREFIX = " --//-- SPC:";

    private static final String SPG_PREFIX = " --//-- SPG:";

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
        getProtocolService().addDebugProtocolEntry(protocol, getIdentifier());
        return true;
    }

    private void checkAnzahlFreie(SendContext pContext) {

        Protocol protocol = pContext.getProtocol();

        // NEP This check does not throw errors, just warnings!! 
        if (null != pContext && null != pContext.auftragSperrenList && !pContext.auftragSperrenList.isEmpty()) {
            StringBuffer sperren = new StringBuffer();
            for (AuftragSperren auftragSperren : pContext.auftragSperrenList) {
                sperren.append(SPC_PREFIX
                               + auftragSperren.getSperrcode()
                               + SPG_PREFIX
                               + auftragSperren.getSperrgrund());


            }
            final String sperrenResult = sperren.toString().substring(8);

            getProtocolService().addProtocolEntry(protocol, ProtocolMessageEnum.ANZAHL_FREIE_VERLETZT_WARN,
                    sperrenResult,
                    getIdentifier(),
                    SeverityEnum.WARNING);

        }
    }

}
