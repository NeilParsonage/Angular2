package com.daimler.emst2.fhi.sendung.werk060.check;

import com.daimler.emst2.fhi.jpa.model.Auftrag;
import com.daimler.emst2.fhi.jpa.model.Lapu;
import com.daimler.emst2.fhi.model.SeverityEnum;
import com.daimler.emst2.fhi.sendung.constants.FHISendStatusEnum;
import com.daimler.emst2.fhi.sendung.constants.FHISendungEnum;
import com.daimler.emst2.fhi.sendung.constants.ProtocolMessageEnum;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.sendung.werk.check.AbstractSendCheck;
import com.daimler.emst2.fhi.sendung.werk.check.SendCheckEnum;
import com.daimler.emst2.fhi.util.BasisStringUtils;

public class CheckFhiAnzahlGasse4 extends AbstractSendCheck {

    private static final String REIHE_7 = "7";

    private static final String REIHE_8 = "8";

    private static final String ORT_LAPU = "LAPU";

    public CheckFhiAnzahlGasse4(ProtocolService pProtocolService, SendCheckEnum sendCheckStepIdentifier) {
        super(pProtocolService, sendCheckStepIdentifier);
    }

    @Override
    protected void init() {
    }

    @Override
    protected boolean doExecuteImpl(SendContext pContext) {

        if (pContext.getSendTypeEnum() != SendTypeEnum.FHI) {
            // this check only relevant for a teil-Sendung/FHI
            return true;
        }

        checkAnzahlGasse4(pContext, this.sendCheck.getTyp());

        // ProtocolEntry erzeugen
        getProtocolService().addDebugProtocolEntry(pContext, getIdentifier());
        return true;
    }

    protected void checkAnzahlGasse4(SendContext pContext, SendTypeEnum sendTyp) {

        Auftrag auftrag = pContext.auftrag;
        if (null == auftrag) {
            return;
        }

        String ort = auftrag.getOrt();
        if (BasisStringUtils.isEmptyOrNull(ort)) {
            return;
        }
        if (!ort.equalsIgnoreCase(ORT_LAPU)) {
            return;
        }

        Lapu lapu = pContext.service.getAuftragService().findLapuEntryByPnr(auftrag.getPnr());
        if (null != lapu) {
            String reihe = lapu.getReihe();
            FHISendungEnum fHISendungEnum = FHISendungEnum.getSendStatus(auftrag.getFhiSendung());
            FHISendStatusEnum fHISendStatusEnum = FHISendStatusEnum.getSendStatus(auftrag.getFhiSendStatus());
            if (isReihe7or8(reihe)
                && fHISendungEnum.equals(FHISendungEnum.SENDUNG)
                && !fHISendStatusEnum.equals(FHISendStatusEnum.PLANSEQUENZIERT)) {
                // Determine Uml Obergrenze
                long gasse4Anz = pContext.service.getAuftragService().getGasse4Anz();
                long gasse4Max = pContext.service.getAuftragService().getGasse4Max();
                if (gasse4Max <= gasse4Anz) {
                    getProtocolService().addProtocolEntry(pContext,
                            ProtocolMessageEnum.AUFTRAG_GASSE_4_VERLETZT_WARN,
                            getIdentifier(),
                            SeverityEnum.WARNING);
                }

            }
        }
    }

    private boolean isReihe7or8(final String reihe) {
        if (BasisStringUtils.isEmptyOrNull(reihe)) {
            return false;
        }
        return reihe.equalsIgnoreCase(REIHE_7) || reihe.equalsIgnoreCase(REIHE_8) ? true : false;
    }
}
