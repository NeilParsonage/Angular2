package com.daimler.emst2.fhi.sendung;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.util.Assert;

import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.model.ITuebObject;
import com.daimler.emst2.fhi.model.Protocol;
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
import com.daimler.emst2.fhi.sendung.process.action.IActionFactory;
import com.daimler.emst2.fhi.sendung.process.check.ICheckFactory;
import com.daimler.emst2.fhi.sendung.process.precondition.IPreconditionFactory;
import com.daimler.emst2.fhi.sendung.protocol.IProtocolService;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.util.SendungUtil;
import com.daimler.emst2.fhi.werk152.sendung.SendDefinitionFactory;

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

    // private SendDefinitionFactory sendDefinitionFactory;

    private final ProtocolService protocolService;

    private SendungenService(ProtocolService protocolService,
            IActionFactory<SendActionEnum> sendActionFactory,
            ICheckFactory<SendCheckEnum> sendCheckFactory,
            IPreconditionFactory<SendPreconditionEnum> preconditionFactory) {
        this.protocolService = protocolService;
        this.actionFactory = sendActionFactory;
        this.checkFactory = sendCheckFactory;
        this.preconditionFactory = preconditionFactory;
    }

    public static ISendService create(ProtocolService protocolService,
            IActionFactory<SendActionEnum> sendActionFactory152,
            ICheckFactory<SendCheckEnum> sendCheckFactory,
            IPreconditionFactory<SendPreconditionEnum> preconditionFactory) {
        return new SendungenService(protocolService, sendActionFactory152, sendCheckFactory, preconditionFactory);
    }

	@Override
	protected List<ISend> getSubProcessList(SendContext pContext) {
        Auftraege auftrag = pContext.getAuftrag();
		SendTypeEnum sendTypeEnum = pContext.getSendTypeEnum();

        List<ISend> sendList = SendDefinitionFactory.create().createSendList(auftrag, sendTypeEnum);

        //if (sendList.isEmpty() || (!SendTypeEnum.KOMPLETT.equals(sendTypeEnum) && !auftrag.isSendungOffen(sendTypeEnum))) {
        if (sendList.isEmpty()
            || (!SendTypeEnum.KOMPLETT.equals(sendTypeEnum) && !SendungUtil.isSendungOffen(auftrag, sendTypeEnum))) {
            Protocol protocol = pContext.getProtocol();
			protocol.addEntry(new ProtocolEntry(sendTypeEnum, new ProtocolMessage(ProtocolMessageEnum.AUFTRAG_SENDUNG_NICHT_OFFEN_ERR), SeverityEnum.ERROR));
		}

		return sendList;
	}

    @Override
    //public boolean sendeAuftrag(Auftraege pAuftrag, Date pLetzteAenderungRestriktionen, Protocol pProtocol) {
    public boolean sendeAuftrag(SendContext sendContext) {
        ITuebObject actionForProtocol = sendContext.protocol.getActionForProtocol();
		SendTypeEnum sendTypeEnum = (SendTypeEnum)actionForProtocol;
        if (LOG.isLoggable(Level.INFO)) {
            LOG.info("Starte Sendung vom Typ '"
                     + sendTypeEnum.name()
                     + "' fuer den Auftrag '"
                     + sendContext.auftrag.getPnr()
                     + "'");
		}

		//Precondition: auftrag not null, hierarchy not null
        Assert.notNull(sendContext.auftrag, "Auftrag darf nicht null sein!");
        Assert.notNull(sendContext.protocol, "Das Protokoll muss initialisiert sein!");
        Assert.notNull(sendContext.sendTypeEnum, "Der Sendungstyp darf nicht null sein!");

        /*SendContext sendContext = SendContext.create();
        
        sendContext.auftrag = ctx.auftrag;
        // sendContext.aktiveRestriktionenMetaList.updatedOn = pLetzteAenderungRestriktionen;
        sendContext.sendTypeEnum = sendTypeEnum;
        sendContext.protocol = ctx.protocol;*/
        /*SendContext sendContext = new SendContext(sendTypeEnum, pAuftrag, pLetzteAenderungRestriktionen, pProtocol,
                username);*/

		boolean result = super.execute(sendContext);
		// falls Sendung erfolgt - erzeuge INFO Protokolleintrag
		if (result) {
            getProtocolService().addProtocolEntry(sendContext.protocol, ProtocolMessageEnum.SENDUNG_DONE_INFO,
                    sendTypeEnum,
                    SeverityEnum.INFO);
		}

		return result;
	}

    private ProtocolService getProtocolService() {
		return protocolService;
	}

    @Override
    public void setProtocolService(IProtocolService protocolService) {
        // already present - new implementation
    }

}
