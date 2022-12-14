package com.daimler.emst2.fhi.sendung.werk060.precondition;

import java.math.BigDecimal;
import java.util.List;

import com.daimler.emst2.fhi.jpa.model.AuftragSperren;
import com.daimler.emst2.fhi.jpa.model.Systemwert;
import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.process.IProcessContext;
import com.daimler.emst2.fhi.sendung.process.precondition.AbstractPrecondition;
import com.daimler.emst2.fhi.sendung.processcommon.IOrtsdatenProcessContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public class PreconditionAnzahlFreie<GenPreconditionEnum extends IProcessId, CTX extends IProcessContext & IOrtsdatenProcessContext>
extends AbstractPrecondition<GenPreconditionEnum, CTX> {


    private static final String ANZ_FREIE_SENDUNGSPRUEFUNG = "ANZ_FREIE_SENDUNGSPRUEFUNG";

    private static final int DEFAULT_ANZ_FREIE = 1;

    public PreconditionAnzahlFreie(GenPreconditionEnum pPreconditionEnum,
            ProtocolService pProtocolService) {
        super(pPreconditionEnum, pProtocolService);
    }

    @Override
    public boolean doPrepareContext(CTX pContext) {
        SendContext sendContext = (SendContext)pContext;

        Systemwert systemwert = sendContext.dao.getSystemwertDao().findByWertName(ANZ_FREIE_SENDUNGSPRUEFUNG);
        Long anzahlFreieAsInt = new Long(DEFAULT_ANZ_FREIE);
        if (null != systemwert) {
            BigDecimal anzahlFreieAsBigDecimal = systemwert.getWertDouble();
            if (null != anzahlFreieAsBigDecimal) {
                anzahlFreieAsInt = anzahlFreieAsBigDecimal.longValue();
            }
        }

        List<AuftragSperren> auftragSperrenList =
                sendContext.dao.getAuftragSperrenDao().findSperren(sendContext.getAuftrag().getPnr(), anzahlFreieAsInt);

        sendContext.auftragSperrenList = auftragSperrenList;

        return true;
    }

}
