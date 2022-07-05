package com.daimler.emst2.fhi.sendung.check;

import java.util.Map;
import java.util.Set;

import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.model.FhiMandantEnum;
import com.daimler.emst2.fhi.model.Protocol;
import com.daimler.emst2.fhi.model.SeverityEnum;
import com.daimler.emst2.fhi.sendung.constants.OrtCheckEnum;
import com.daimler.emst2.fhi.sendung.constants.OrtTypEnum;
import com.daimler.emst2.fhi.sendung.constants.ProtocolMessageEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.precondition.SendPreconditionEnum;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.util.AuftragUtil;
import com.daimler.emst2.fhi.util.BasisCollectionUtils;

public abstract class AbstractCheckOrtError extends AbstractSendCheck {

    public AbstractCheckOrtError(ProtocolService pProtocolService) {
        super(pProtocolService);
    }

    protected abstract SendCheckEnum getMyCheckEnum();

    protected abstract boolean isOrtSendAllowed(SendContext pContext);

    @Override
    protected void init() {
        addPrecondition(SendPreconditionEnum.ORTSDATEN_FETCHED);
        setStepIdentifierEnum(getMyCheckEnum());
    }

    protected boolean isOrtAllowedForParameters(SendContext pContext, OrtCheckEnum pOrtCheckAusfallEnum, OrtTypEnum pOrtTypEnum) {
        Auftraege auftrag = pContext.getAuftrag();
        String auftragsOrt = AuftragUtil.getOrt(auftrag, pOrtTypEnum);
        Map<OrtCheckEnum, Map<OrtTypEnum, Set<String>>> ortChecksMap = pContext.getOrtChecksMap();
        Map<OrtTypEnum, Set<String>> ortTypMap = ortChecksMap.get(pOrtCheckAusfallEnum);
        if (BasisCollectionUtils.isEmptyOrNull(ortTypMap)) {
            return false;
        }
        Set<String> positiveOrteSet = ortTypMap.get(pOrtTypEnum);
        if (BasisCollectionUtils.isEmptyOrNull(positiveOrteSet)) {
            return false;
        }
        boolean ortIsOk = positiveOrteSet.contains(auftragsOrt);
        return ortIsOk;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean doExecuteImpl(SendContext pContext) {
        // auf ortsdaten pruefen und evtl. Meldung erzeugen
        Protocol protocol = pContext.getProtocol();

        boolean isSendbar = isOrtSendAllowed(pContext);

        boolean isFeatureActivated = FhiMandantEnum.WERK_060.equals(pContext.mandantEnum);
        if (isFeatureActivated && !isSendbar) {
            // FIXME WERK-152 thb sobald die Ortspruefungen spezifiziert und in der DB konfiguriert sind wird diese Protokollierung eingefuegt.
            getProtocolService().addProtocolEntry(pContext, ProtocolMessageEnum.ORT_SENDUNG_ERR, getIdentifier(),
                    SeverityEnum.ERROR);
            return false;
        }

        // ProtocolEntry erzeugen
        getProtocolService().addDebugProtocolEntry(pContext, getIdentifier());
        return true;
    }

}
