package com.daimler.emst2.fhi.sendung.werk.action;

import com.daimler.emst2.fhi.jpa.model.Auftrag;
import com.daimler.emst2.fhi.model.Protocol;
import com.daimler.emst2.fhi.sendung.constants.SendStatusEnum;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.util.AuftragUtil;
import com.daimler.emst2.fhi.util.DateTimeHelper;

/**
 * Setzt den Sendestatus-IST auf "gesendet" und protokolliert den Sendetermin am zugehoerigen Auftragsattribut.
 * @author thb
 *
 */
public class ActionSendeStatusSetzen extends AbstractSendAction {

    public ActionSendeStatusSetzen(SendActionEnum pSendActionEnum, ProtocolService pProtocolService) {
		super(pSendActionEnum.getTyp(), pSendActionEnum, pProtocolService);
	}

	@Override
	protected void init() {
		// no static preconditions
		// sendAction is specified via constructor
	}

	@Override
	protected boolean doExecuteImpl(SendContext pContext) {
        Protocol protocol = pContext.getProtocol();
        Auftrag auftrag = pContext.getAuftrag();
        AuftragUtil.setSendStatusIst(auftrag, getSendTypeEnum(), SendStatusEnum.PLANSEQUENZIERT);
        //		auftrag.setSendStatusIst(getSendTypeEnum(),
        //				SendStatusEnum.PLANSEQUENZIERT);

        AuftragUtil.setSendetermin(auftrag, getSendTypeEnum(), DateTimeHelper.getAktuellesDatum());
        // auftrag.setSendetermin(getSendTypeEnum(), DateTimeHelper.getAktuellesDatum());


		// BESONDERHEIT FUER DIE FHI-SENDUNG
		if (SendTypeEnum.FHI.equals(getSendTypeEnum())) {
            AuftragUtil.setZugebundenBool(auftrag, false);
            // auftrag.setZugebundenBool(false);
		}
        getProtocolService().addDebugProtocolEntry(pContext, getIdentifier());
		return true;
	}
}
