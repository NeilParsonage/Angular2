package com.daimler.emst2.fhi.sendung.werk060.check;

import com.daimler.emst2.fhi.jpa.model.Auftrag;
import com.daimler.emst2.fhi.jpa.model.Warteschlange;
import com.daimler.emst2.fhi.model.SeverityEnum;
import com.daimler.emst2.fhi.sendung.constants.ProtocolMessageEnum;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.constants.SendungsTypeMap;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.sendung.werk.check.AbstractSendCheck;
import com.daimler.emst2.fhi.sendung.werk.check.SendCheckEnum;

public class CheckFhiWarteschlange extends AbstractSendCheck {

    public CheckFhiWarteschlange(ProtocolService pProtocolService, SendCheckEnum sendCheckStepIdentifier) {
        super(pProtocolService, sendCheckStepIdentifier);
    }

    @Override
    protected void init() {
    }

    @Override
    protected boolean doExecuteImpl(SendContext pContext) {
        checkWarteschlange(pContext, this.sendCheck.getTyp());

        // ProtocolEntry erzeugen
        getProtocolService().addDebugProtocolEntry(pContext, getIdentifier());
        return true;
    }

    protected void checkWarteschlange(SendContext pContext, SendTypeEnum sendTyp) {

        //TODO NEP check the sendTyp Parameter, doesn't always match what was selected in the GUI
        // for example just selected Komplettsendung und here we get an UNDEFINED !!

        /*
            Wenn die Warteschlange voll ist und die aktuelle PNR steht an 1. Stelle, dann wird diese Plansequenziert.
            Deshalb darf sie nicht erneut mit der gleichen Sendungsart in die Warteschlange gestellt werden.
        */

        // FIXME NEP what to do if null pointer ??
        Auftrag auftrag = pContext.auftrag;
        if (null == auftrag) {
            return;
        }

        long maxWarteschlange = pContext.service.getAuftragService().getMaxWarteschlange();
        Warteschlange warteschlange =
                pContext.service.getAuftragService().getWarteschlangeForPnr(pContext.auftrag.getPnr());

        if (null == warteschlange) {
            // Pnr is not already in the Warteschlange so we can ignore the rest of the Prüfung !
            return;
        }

        if (pContext.service.getAuftragService().getCountWarteschlangeEntries() == maxWarteschlange) {
            if (null != warteschlange.getPosition() && warteschlange.getPosition().longValue() == maxWarteschlange) {
                if (null != warteschlange.getSendungsTyp()
                    && SendungsTypeMap
                            .getSendTypeEnumForString(warteschlange.getSendungsTyp()) == pContext.sendTypeEnum) {

                    getProtocolService().addProtocolEntry(pContext,
                            ProtocolMessageEnum.WARTESCHLANGE_VERLETZT_ERR,
                            getIdentifier(),
                            SeverityEnum.ERROR);

                }
            }
        }

    }
}
