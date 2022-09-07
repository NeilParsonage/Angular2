package com.daimler.emst2.fhi.sendung.werk060.check;

import org.springframework.util.Assert;

import com.daimler.emst2.fhi.constants.FhiSystemwertKeyEnum;
import com.daimler.emst2.fhi.jpa.model.Auftrag;
import com.daimler.emst2.fhi.model.SeverityEnum;
import com.daimler.emst2.fhi.sendung.constants.ProtocolMessageEnum;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.sendung.werk.check.AbstractSendCheck;
import com.daimler.emst2.fhi.sendung.werk.check.SendCheckEnum;
import com.daimler.emst2.fhi.services.AuftragService;

public class CheckUmlaufwerte extends AbstractSendCheck {

    public CheckUmlaufwerte(ProtocolService pProtocolService, SendCheckEnum sendCheckStepIdentifier) {
        super(pProtocolService, sendCheckStepIdentifier);
    }

    @Override
    protected void init() {
    }

    @Override
    protected boolean doExecuteImpl(SendContext pContext) {
        checkUmlaufwerte(pContext, this.sendCheck.getTyp());

        // ProtocolEntry erzeugen
        getProtocolService().addDebugProtocolEntry(pContext, getIdentifier());
        return true;
    }

    protected void checkUmlaufwerte(SendContext pContext, SendTypeEnum sendTyp) {

        Auftrag auftrag = pContext.auftrag;
        if (null == auftrag) {
            return;
        }

        // Determine Uml Obergrenze
        long maxUmlauf = pContext.service.getAuftragService().getUmlaufObergrenze();
        if (maxUmlauf == AuftragService.DEFAULT_ABSTAND_UMLAUF_OBERGRENZE) {

            getProtocolService().addProtocolEntry(pContext,
                    ProtocolMessageEnum.UMLAUF_OBERGRENZE_NICHT_DEFINIERT_ERR,
                    FhiSystemwertKeyEnum.ABSTAND_UMLAUF_OBERGRENZE.getKey(),
                    getIdentifier(),
                    SeverityEnum.ERROR);
            return;
        }

        // Determine Band Nr
        Assert.notNull(pContext.auftrag.getBandnr(), "Auftrag BandNr darf nicht 'null' sein, ");
        long bandNr = pContext.auftrag.getBandnr().longValue();

        // Determine umlaufWert for relevant BandNr
        Long umlaufWertForBand =
                pContext.service.getAuftragService().getUmlaufwertForBand(bandNr);

        FhiSystemwertKeyEnum fhiSystemwertKeyEnumValue = FhiSystemwertKeyEnum.getUmlOgrKeyForBand(bandNr);
        Long umlOberGrenze = pContext.service.getAuftragService().getOgLmtForBandBand(fhiSystemwertKeyEnumValue);

        if (umlaufWertForBand + maxUmlauf >= umlOberGrenze) {
            getProtocolService().addProtocolEntry(pContext, ProtocolMessageEnum.UMLAUF_OBERGRENZE_EXCEEDED_WARN,
                    String.valueOf(bandNr),
                    getIdentifier(),
                    SeverityEnum.WARNING);
        }


    }
}
