package com.daimler.emst2.fhi.sendung.werk060;

import com.daimler.emst2.fhi.sendung.model.ISendService;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public class Sendung060 implements ISendService {

    private final ProtocolService protocolService;

    private Sendung060(ProtocolService protocolService) {
        this.protocolService = protocolService;
    }

    public static Sendung060 create(ProtocolService protocolService) {
        return new Sendung060(protocolService);
    }

    @Override
    public boolean sendeAuftrag(SendContext sendContext) {
        //return SendungenService.create(protocolService).sendeAuftrag(sendContext);
        throw new RuntimeException("Sendung060 not implemented yet!");
    }

}
