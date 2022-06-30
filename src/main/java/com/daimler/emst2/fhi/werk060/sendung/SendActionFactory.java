package com.daimler.emst2.fhi.werk060.sendung;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daimler.emst2.fhi.jpa.dao.AktiveCodesHistDao;
import com.daimler.emst2.fhi.jpa.dao.AktiveRestriktionHistDao;
import com.daimler.emst2.fhi.jpa.dao.AuftraegeDao;
import com.daimler.emst2.fhi.jpa.dao.ManuellePnrReihenfolgeDao;
import com.daimler.emst2.fhi.jpa.dao.SequenceDao;
import com.daimler.emst2.fhi.jpa.dao.SystemwerteDao;
import com.daimler.emst2.fhi.jpa.dao.W73rimpoDao;
import com.daimler.emst2.fhi.sendung.action.ActionFhiHistSternenhimmel;
import com.daimler.emst2.fhi.sendung.action.ActionLmtHistSternenhimmel;
import com.daimler.emst2.fhi.sendung.action.SendActionEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.precondition.SendPreconditionEnum;
import com.daimler.emst2.fhi.sendung.process.action.IAction;
import com.daimler.emst2.fhi.sendung.process.action.IActionFactory;
import com.daimler.emst2.fhi.sendung.processcommon.action.ActionDummyToImplement;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.services.AuftragHistorienService;
import com.daimler.emst2.fhi.services.SendemaskeService;
import com.daimler.emst2.fhi.util.TaktTelegramUtil;


/**
 * Erzeugt alle implementierten Sendungs Aktionen entsprechend der übergebenen SendActionEnum
 * und übergibt auch gleich die Referenz auf den Protokoll Service, damit die erzeugte Aktion
 * ihre Protokolleinträge erzeugen kann.
 * 
 * @author thb
 */
@Service("sendActionFactory060")
public class SendActionFactory implements IActionFactory<SendActionEnum> {

	/**
	 * dao references injected by spring
	 */
    @Autowired
    private AuftraegeDao auftragDao;

    @Autowired
    private SystemwerteDao systemwertDao;

    @Autowired
    private ManuellePnrReihenfolgeDao manuellePnrReihenfolgeDao;

    @Autowired
    private W73rimpoDao taktTelegramDao;

    @Autowired
    private SequenceDao seqDao;

    @Autowired
    private AktiveRestriktionHistDao histRestriktionDao;

    @Autowired
    private AktiveCodesHistDao histCodeDao;

	/**
	 * service reference
	 */
    @Autowired
    private SendemaskeService sendemaskeService;

    @Autowired
    private AuftragHistorienService auftragHistorienService;

    @Autowired
    TaktTelegramUtil taktTelegramUtil;

	/**
	 * helper actions
	 */
	private ActionFhiHistSternenhimmel actionFhiHistSternenhimmel;

	private ActionLmtHistSternenhimmel actionlmtHistSternenhimmel;

    @Autowired
    private ProtocolService protocolService;

	public SendActionFactory() {
		super();
	}

    private ProtocolService getProtocolService() {
        return protocolService;
    }

	@Override
    @SuppressWarnings("rawtypes")
	public IAction createAction(SendActionEnum pActionEnum) {
		switch (pActionEnum) {
		case ALL_LAUFENDE_NUMMER_AKTUALISIEREN:
		    return new ActionDummyToImplement<SendPreconditionEnum, SendActionEnum, SendContext>(getProtocolService());
            //			return new ActionIncLaufendeNummer<SendPreconditionEnum, SendActionEnum, SendContext>(
            //					SendTypeEnum.UNDEFINED, SendPreconditionEnum.AUFTRAG_LFD_NR_FETCHED, pActionEnum,
            //					getProtocolService(), systemwertDao);
		case ALL_MANUELLE_RF_INFO_LOESCHEN:
            return new ActionDummyToImplement<SendPreconditionEnum, SendActionEnum, SendContext>(getProtocolService());
            //			return new ActionAllManuelleReihenfolgeInfoLoeschen(pActionEnum,
            //					getProtocolService(), manuellePnrReihenfolgeDao);
		case ALL_SENDBAR_KNZ_SETZEN:
            return new ActionDummyToImplement<SendPreconditionEnum, SendActionEnum, SendContext>(getProtocolService());
            //			return new ActionAllSendbarKnzSetzen(pActionEnum,
            //					getProtocolService());
		case ALL_HISTORIE_SCHREIBEN:
            return new ActionDummyToImplement<SendPreconditionEnum, SendActionEnum, SendContext>(getProtocolService());
            //			return new ActionAllSendHistorienEintragSchreiben(pActionEnum,
            //					getProtocolService(), auftragHistorienService);
		case FHI_HISTORISIERUNG_STERNENHIMMEL:
            return new ActionDummyToImplement<SendPreconditionEnum, SendActionEnum, SendContext>(getProtocolService());
            //			actionFhiHistSternenhimmel = new ActionFhiHistSternenhimmel(
            //					pActionEnum, getProtocolService());
            //			actionFhiHistSternenhimmel.setHistRestriktionDao(histRestriktionDao);
            //			actionFhiHistSternenhimmel.setHistCodeDao(histCodeDao);
            //			return actionFhiHistSternenhimmel;
		case FHI_LAUFENDE_NUMMER_AKTUALISIEREN:
            return new ActionDummyToImplement<SendPreconditionEnum, SendActionEnum, SendContext>(getProtocolService());
            //			return new ActionIncLaufendeNummer<SendPreconditionEnum, SendActionEnum, SendContext>(
            //					SendTypeEnum.FHI,
            //					SendPreconditionEnum.AUFTRAG_LFD_NR_FETCHED, pActionEnum,
            //					getProtocolService(), systemwertDao);
		case FHI_LMT_SOLLABSTAND_VORBERECHNEN:
            return new ActionDummyToImplement<SendPreconditionEnum, SendActionEnum, SendContext>(getProtocolService());
            //			return new ActionSollabstandNeuVorberechnen<SendActionEnum>(
            //					pActionEnum, getProtocolService(), sendemaskeService);
		case FHI_SENDESTATUS_SETZEN:
            return new ActionDummyToImplement<SendPreconditionEnum, SendActionEnum, SendContext>(getProtocolService());
            //			return new ActionSendeStatusSetzen(pActionEnum,
            //					getProtocolService());
		case FHI_TAKT_TELEGRAMM:
            System.out.println("**** FHI TAKT TELEGRAM - DUMMY");
            return new ActionDummyToImplement<SendPreconditionEnum, SendActionEnum, SendContext>(getProtocolService());
            //			return new ActionTaktTelegramm(SendTypeEnum.FHI, pActionEnum,
            //                    getProtocolService(), taktTelegramDao, taktTelegramUtil, seqDao);
		case LMT_HISTORISIERUNG_STERNENHIMMEL:
            return new ActionDummyToImplement<SendPreconditionEnum, SendActionEnum, SendContext>(getProtocolService());
            //			actionlmtHistSternenhimmel = new ActionLmtHistSternenhimmel(
            //					pActionEnum, getProtocolService());
            //			actionlmtHistSternenhimmel.setHistRestriktionDao(histRestriktionDao);
            //			actionlmtHistSternenhimmel.setHistCodeDao(histCodeDao);
            //			return actionlmtHistSternenhimmel;
		case LMT_LAUFENDE_NUMMER_AKTUALISIEREN:
            return new ActionDummyToImplement<SendPreconditionEnum, SendActionEnum, SendContext>(getProtocolService());
            //			return new ActionIncLaufendeNummer<SendPreconditionEnum, SendActionEnum, SendContext>(SendTypeEnum.LMT,
            //					SendPreconditionEnum.AUFTRAG_LFD_NR_FETCHED, pActionEnum,
            //					getProtocolService(), systemwertDao);
		case LMT_SENDESTATUS_SETZEN:
            return new ActionDummyToImplement<SendPreconditionEnum, SendActionEnum, SendContext>(getProtocolService());
            //			return new ActionSendeStatusSetzen(pActionEnum,
            //					getProtocolService());
		case LMT_TAKT_TELEGRAMM:
            System.out.println("**** LMT TAKT TELEGRAM - DUMMY");
            return new ActionDummyToImplement<SendPreconditionEnum, SendActionEnum, SendContext>(getProtocolService());
            //			return new ActionTaktTelegramm(SendTypeEnum.LMT, pActionEnum,
            //                    getProtocolService(), taktTelegramDao, taktTelegramUtil, seqDao);
		case RHM_LAUFENDE_NUMMER_AKTUALISIEREN:
            return new ActionDummyToImplement<SendPreconditionEnum, SendActionEnum, SendContext>(getProtocolService());
            //			return new ActionIncLaufendeNummer<SendPreconditionEnum, SendActionEnum, SendContext>(SendTypeEnum.RHM,
            //					SendPreconditionEnum.AUFTRAG_LFD_NR_FETCHED, pActionEnum,
            //					getProtocolService(), systemwertDao);
		case RHM_SENDESTATUS_SETZEN:
            return new ActionDummyToImplement<SendPreconditionEnum, SendActionEnum, SendContext>(getProtocolService());
            //			return new ActionSendeStatusSetzen(pActionEnum,
            //					getProtocolService());
		case RHM_TAKT_TELEGRAMM:
            System.out.println("**** RHM TAKT TELEGRAM - DUMMY");
            return new ActionDummyToImplement<SendPreconditionEnum, SendActionEnum, SendContext>(getProtocolService());
            //			return new ActionTaktTelegramm(SendTypeEnum.RHM, pActionEnum,
            //                    getProtocolService(), taktTelegramDao, taktTelegramUtil, seqDao);
		case UBM_LAUFENDE_NUMMER_AKTUALISIEREN:
            return new ActionDummyToImplement<SendPreconditionEnum, SendActionEnum, SendContext>(getProtocolService());
            //			return new ActionIncLaufendeNummer<SendPreconditionEnum, SendActionEnum, SendContext>(SendTypeEnum.UBM,
            //					SendPreconditionEnum.AUFTRAG_LFD_NR_FETCHED, pActionEnum,
            //					getProtocolService(), systemwertDao);
		case UBM_SENDESTATUS_SETZEN:
            return new ActionDummyToImplement<SendPreconditionEnum, SendActionEnum, SendContext>(getProtocolService());
            //			return new ActionSendeStatusSetzen(pActionEnum,
            //					getProtocolService());
		case UBM_TAKT_TELEGRAMM:
            System.out.println("**** UBM TAKT TELEGRAM - DUMMY");
            return new ActionDummyToImplement<SendPreconditionEnum, SendActionEnum, SendContext>(getProtocolService());
            //			return new ActionTaktTelegramm(SendTypeEnum.UBM, pActionEnum,
            //                    getProtocolService(), taktTelegramDao, taktTelegramUtil, seqDao);
		case ALL_SAVE_AUFTRAG:
            return new ActionDummyToImplement<SendPreconditionEnum, SendActionEnum, SendContext>(getProtocolService());
            //			return new ActionSaveAuftrag<SendActionEnum>(pActionEnum,
            //					getProtocolService(), auftragDao);
		default:
			throw new RuntimeException("Cannot create unknown SendAction " + pActionEnum.name());
		}
	}

}
