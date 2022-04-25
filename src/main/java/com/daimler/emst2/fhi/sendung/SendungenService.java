package com.daimler.emst2.fhi.sendung;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.model.IProtocol;
import com.daimler.emst2.fhi.model.ITuebObject;
import com.daimler.emst2.fhi.model.ProtocolEntry;
import com.daimler.emst2.fhi.model.ProtocolMessage;
import com.daimler.emst2.fhi.model.SeverityEnum;
import com.daimler.emst2.fhi.sendung.action.SendActionEnum;
import com.daimler.emst2.fhi.sendung.check.SendCheckEnum;
import com.daimler.emst2.fhi.sendung.constants.ProtocolMessageEnum;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.model.ISendService;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.precondition.SendPreconditionEnum;
import com.daimler.emst2.fhi.sendung.process.AbstractProcessService;
import com.daimler.emst2.fhi.sendung.protocol.IProtocolService;
import com.daimler.emst2.fhi.util.SendungUtil;
import com.daimler.emst2.frw.context.AuthenticationContext;

/**
 * Der Sendungen Service implementiert das IProcess Framework für die Sendungen auf Basis der Sendehierarchie Spezifikation
 * 
 * @author thi
 */
public class SendungenService extends AbstractProcessService<SendPreconditionEnum, SendCheckEnum, SendActionEnum, SendContext, ISend> implements ISendService {

	/**
	 * Service Logger
	 */
    private static final Logger LOG = Logger.getLogger(SendungenService.class.getName());

	private ISendDefinitionFactory sendDefinitionFactory;

	private IProtocolService protocolService;

    @Autowired
    AuthenticationContext authContext;

	@Override
	protected List<ISend> getSubProcessList(SendContext pContext) {
        Auftraege auftrag = pContext.getAuftrag();
		SendTypeEnum sendTypeEnum = pContext.getSendTypeEnum();
		List<ISend> sendList = sendDefinitionFactory.createSendList(auftrag, sendTypeEnum);

        //if (sendList.isEmpty() || (!SendTypeEnum.KOMPLETT.equals(sendTypeEnum) && !auftrag.isSendungOffen(sendTypeEnum))) {
        if (sendList.isEmpty()
            || (!SendTypeEnum.KOMPLETT.equals(sendTypeEnum) && !SendungUtil.isSendungOffen(auftrag, sendTypeEnum))) {
			IProtocol protocol = pContext.getProtocol();
			protocol.addEntry(new ProtocolEntry(sendTypeEnum, new ProtocolMessage(ProtocolMessageEnum.AUFTRAG_SENDUNG_NICHT_OFFEN_ERR), SeverityEnum.ERROR));
		}

		return sendList;
	}

    public boolean sendeAuftrag(Auftraege pAuftrag, Date pLetzteAenderungRestriktionen, IProtocol pProtocol) {
		ITuebObject actionForProtocol = pProtocol.getActionForProtocol();
		SendTypeEnum sendTypeEnum = (SendTypeEnum)actionForProtocol;
        if (LOG.isLoggable(Level.INFO)) {
			LOG.info("Starte Sendung vom Typ '" + sendTypeEnum.name() + "' fuer den Auftrag '" + pAuftrag.getPnr() + "'");
		}

		//Precondition: auftrag not null, hierarchy not null
		Assert.notNull(pAuftrag, "Auftrag darf nicht null sein!");
		Assert.notNull(pProtocol, "Das Protokoll muss initialisiert sein!");
		Assert.notNull(sendTypeEnum, "Der Sendungstyp darf nicht null sein!");

        SendContext sendContext = SendContext.create();
        sendContext.user = authContext.getAuthentication().getName();
        sendContext.auftrag = pAuftrag;
        sendContext.aktiveRestriktionenMetaList.updatedOn = pLetzteAenderungRestriktionen;
        sendContext.sendTypeEnum = sendTypeEnum;
        sendContext.protocol = pProtocol;
        /*SendContext sendContext = new SendContext(sendTypeEnum, pAuftrag, pLetzteAenderungRestriktionen, pProtocol,
                username);*/

		boolean result = super.execute(sendContext);
		// falls Sendung erfolgt - erzeuge INFO Protokolleintrag
		if (result) {
			getProtocolService().addProtocolEntry(pProtocol, ProtocolMessageEnum.SENDUNG_DONE_INFO, sendTypeEnum, SeverityEnum.INFO);
		}

		return result;
	}

	public void setSendDefinitionFactory(ISendDefinitionFactory pSendDefinitionFactory) {
		sendDefinitionFactory = pSendDefinitionFactory;
	}

	private IProtocolService getProtocolService() {
		return protocolService;
	}

	@Override
    public void setProtocolService(IProtocolService protocolService) {
		this.protocolService = protocolService;
	}

    @Override
    public boolean sendeAuftrag(SendContext sendContext) {
        // TODO Auto-generated method stub
        return sendeAuftrag(sendContext.getAuftrag(),
                sendContext.aktiveRestriktionenMetaList.updatedOn,
                sendContext.protocol);
    }
}
