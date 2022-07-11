package com.daimler.emst2.fhi.sendung.processcommon.precondition;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.daimler.emst2.fhi.constants.FhiSystemGruppeKeyEnum;
import com.daimler.emst2.fhi.jpa.model.Systemwert;
import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.sendung.process.precondition.AbstractPrecondition;
import com.daimler.emst2.fhi.sendung.processcommon.IAuftragLfdNrProcessContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.services.KonfigurationService;

public class PreconditionFetchLaufendeNummern<GenPreconditionEnum extends IProcessId, CTX extends IAuftragLfdNrProcessContext>
extends AbstractPrecondition<GenPreconditionEnum, CTX> {

    private static final Logger LOG = Logger.getLogger(AbstractPrecondition.class.getName());

    private final KonfigurationService konfigurationsService;

    public PreconditionFetchLaufendeNummern(GenPreconditionEnum pPreconditionEnum, ProtocolService pProtocolService,
            KonfigurationService pKonfigurationsService) {
        super(pPreconditionEnum, pProtocolService);
        konfigurationsService = pKonfigurationsService;
    }

    @Override
    public boolean doPrepareContext(CTX pContext) {
        Map<String, Long> mapForContext = new HashMap<String, Long>();
        try {
            fetchDataAndStoreInContext(pContext, mapForContext);
        } catch (RuntimeException e) {
            LOG.severe("error fetching systemwerte for 'laufende Nummern'" + ExceptionUtils.getStackTrace(e));
            return false;
        }
        return true;
    }

    private void fetchDataAndStoreInContext(CTX pContext, Map<String, Long> mapForContext) {
        Map<String, Systemwert> konfigurationMap =
                konfigurationsService.getKonfigurationGruppeWithReload(FhiSystemGruppeKeyEnum.AUFTRAG_LFD_NUMMERN);
        pContext.setCurrentLfdNrMap(konfigurationMap);
    }

}
