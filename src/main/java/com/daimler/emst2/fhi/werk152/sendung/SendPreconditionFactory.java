package com.daimler.emst2.fhi.werk152.sendung;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daimler.emst2.fhi.jpa.dao.AktiveRestriktionDao;
import com.daimler.emst2.fhi.jpa.dao.AuftraegeDao;
import com.daimler.emst2.fhi.jpa.dao.OrtCheckCustomDao;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.precondition.SendPreconditionEnum;
import com.daimler.emst2.fhi.sendung.process.precondition.IPrecondition;
import com.daimler.emst2.fhi.sendung.process.precondition.IPreconditionFactory;
import com.daimler.emst2.fhi.sendung.processcommon.precondition.PreconditionAuftragSperrenundAnkuendigungenUpToDate;
import com.daimler.emst2.fhi.sendung.processcommon.precondition.PreconditionAuftragUpToDate;
import com.daimler.emst2.fhi.sendung.processcommon.precondition.PreconditionFetchLaufendeNummern;
import com.daimler.emst2.fhi.sendung.processcommon.precondition.PreconditionOrtCheckSendAndCancelSendData;
import com.daimler.emst2.fhi.sendung.processcommon.precondition.PreconditionRestriktionenUpToDate;
import com.daimler.emst2.fhi.sendung.processcommon.precondition.PreconditionSendPrepareHierarchyInfosForHistory;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.services.AuftraegeService;
import com.daimler.emst2.fhi.services.KonfigurationService;


@Component
public class SendPreconditionFactory implements IPreconditionFactory<SendPreconditionEnum> {

    @Autowired
    private AuftraegeDao auftragDao;

    @Autowired
    private AuftraegeService auftragService;

    @Autowired
    private AktiveRestriktionDao aktiveRestriktionDao;

    @Autowired
    private OrtCheckCustomDao ortCheckCustomDao;

    @Autowired
    private KonfigurationService konfigurationsService;

    @Autowired
    private ProtocolService protocolService;

	@Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public IPrecondition createPrecondition(SendPreconditionEnum pCheckPrecondition )  {
		switch (pCheckPrecondition) {
		case AUFTRAG_UPTODATE:
			return new PreconditionAuftragUpToDate<SendPreconditionEnum, SendContext>(pCheckPrecondition, getProtocolService(), auftragDao);
		case AUFTRAG_SPERREN_ANKUENDIGUNGEN_UPTODATE:
            return new PreconditionAuftragSperrenundAnkuendigungenUpToDate(pCheckPrecondition, getProtocolService(),
                    auftragService);
		case AUFTRAG_LFD_NR_FETCHED:
			return new PreconditionFetchLaufendeNummern<SendPreconditionEnum, SendContext>(pCheckPrecondition, getProtocolService(), konfigurationsService);
		case RESTRIKTIONEN_UPTODATE:
			return new PreconditionRestriktionenUpToDate(pCheckPrecondition, getProtocolService(), aktiveRestriktionDao);
		case ORTSDATEN_FETCHED:
            return new PreconditionOrtCheckSendAndCancelSendData<SendPreconditionEnum, SendContext>(pCheckPrecondition,
                    getProtocolService(), ortCheckCustomDao);
		case SEND_HIERARCHY_INFOS_FOR_HISTORY_LMT:
			return new PreconditionSendPrepareHierarchyInfosForHistory(pCheckPrecondition, getProtocolService(), SendTypeEnum.LMT);
		case SEND_HIERARCHY_INFOS_FOR_HISTORY_FHI:
			return new PreconditionSendPrepareHierarchyInfosForHistory(pCheckPrecondition, getProtocolService(), SendTypeEnum.FHI);
		case SEND_HIERARCHY_INFOS_FOR_HISTORY_RHM:
			return new PreconditionSendPrepareHierarchyInfosForHistory(pCheckPrecondition, getProtocolService(), SendTypeEnum.RHM);
		case SEND_HIERARCHY_INFOS_FOR_HISTORY_UBM:
			return new PreconditionSendPrepareHierarchyInfosForHistory(pCheckPrecondition, getProtocolService(), SendTypeEnum.UBM);
		default:
			throw new RuntimeException("Unsupported value of SendPreconditionEnum: " + pCheckPrecondition);
		}
	}

    public void setAuftragDao(AuftraegeDao pAuftragDao) {
		auftragDao = pAuftragDao;
	}

    public void setAktiveRestriktionDao(AktiveRestriktionDao pAktiveRestriktionDao) {
		aktiveRestriktionDao = pAktiveRestriktionDao;
	}

    public void setKonfigurationsService(KonfigurationService pKonfigurationsService) {
		konfigurationsService = pKonfigurationsService;
	}


    private ProtocolService getProtocolService() {
		return protocolService;
	}

}