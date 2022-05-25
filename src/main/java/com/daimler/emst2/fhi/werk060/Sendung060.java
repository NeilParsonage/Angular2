package com.daimler.emst2.fhi.werk060;

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

public class Sendung060 implements ISendService {

    private final ProtocolService protocolService;

    private final IActionFactory<SendActionEnum> sendActionFactory060;
    private final ICheckFactory<SendCheckEnum> sendCheckFactory060;
    private final IPreconditionFactory<SendPreconditionEnum> preconditionFactory;
    private final ISendDefinitionFactory sendDefinitionFactory;

    private Sendung060(ProtocolService protocolService,
            IActionFactory<SendActionEnum> sendActionFactory060,
            ICheckFactory<SendCheckEnum> sendCheckFactory060,
            IPreconditionFactory<SendPreconditionEnum> preconditionFactory,
            ISendDefinitionFactory sendDefinitionFactory) {
        this.protocolService = protocolService;
        this.sendActionFactory060 = sendActionFactory060;
        this.sendCheckFactory060 = sendCheckFactory060;
        this.preconditionFactory = preconditionFactory;
        this.sendDefinitionFactory = sendDefinitionFactory;
    }

    public static Sendung060 create(ProtocolService protocolService,
            IActionFactory<SendActionEnum> sendActionFactory060,
            ICheckFactory<SendCheckEnum> sendCheckFactory060,
            IPreconditionFactory<SendPreconditionEnum> preconditionFactory,
            ISendDefinitionFactory sendDefinitionFactory) {
        return new Sendung060(protocolService, sendActionFactory060, sendCheckFactory060, preconditionFactory,
                sendDefinitionFactory);
    }

    @Override
    public boolean sendeAuftrag(SendContext sendContext) {
        return SendungenService.create(protocolService, sendActionFactory060, sendCheckFactory060,
                preconditionFactory, sendDefinitionFactory)
                .sendeAuftrag(sendContext);
    }

}
