package com.daimler.emst2.fhi.werk152;

import com.daimler.emst2.fhi.sendung.ISendDefinitionFactory;
import com.daimler.emst2.fhi.sendung.SendungenService;
import com.daimler.emst2.fhi.sendung.action.SendActionEnum;
import com.daimler.emst2.fhi.sendung.check.SendCheckEnum;
import com.daimler.emst2.fhi.sendung.model.ISendService;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.precondition.SendPreconditionEnum;
import com.daimler.emst2.fhi.sendung.process.action.IActionFactory;
import com.daimler.emst2.fhi.sendung.process.check.ICheckFactory;
import com.daimler.emst2.fhi.sendung.process.precondition.IPreconditionFactory;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public class Sendung152 implements ISendService {

    private final ProtocolService protocolService;

    private final IActionFactory<SendActionEnum> sendActionFactory152;
    private final ICheckFactory<SendCheckEnum> sendCheckFactory152;
    private final IPreconditionFactory<SendPreconditionEnum> preconditionFactory;

    private final ISendDefinitionFactory sendDefinitionFactory;

    private Sendung152(ProtocolService protocolService,
            IActionFactory<SendActionEnum> sendActionFactory152,
            ICheckFactory<SendCheckEnum> sendCheckFactory152,
            IPreconditionFactory<SendPreconditionEnum> preconditionFactory,
            ISendDefinitionFactory sendDefinitionFactory) {
        this.protocolService = protocolService;
        this.sendActionFactory152 = sendActionFactory152;
        this.sendCheckFactory152 = sendCheckFactory152;
        this.preconditionFactory = preconditionFactory;
        this.sendDefinitionFactory = sendDefinitionFactory;
    }

    public static Sendung152 create(ProtocolService protocolService,
            IActionFactory<SendActionEnum> sendActionFactory152,
            ICheckFactory<SendCheckEnum> sendCheckFactory152,
            IPreconditionFactory<SendPreconditionEnum> preconditionFactory,
            ISendDefinitionFactory sendDefinitionFactory) {
        return new Sendung152(protocolService, sendActionFactory152, sendCheckFactory152, preconditionFactory,
                sendDefinitionFactory);
    }

    @Override
    public boolean sendeAuftrag(SendContext sendContext) {
        return SendungenService.create(protocolService, sendActionFactory152, sendCheckFactory152, preconditionFactory,
                sendDefinitionFactory)
                .sendeAuftrag(sendContext);
    }

}
