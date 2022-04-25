package com.daimler.emst2.fhi.sendung.werk060;

import com.daimler.emst2.fhi.sendung.SendungenService;
import com.daimler.emst2.fhi.sendung.model.ISendService;
import com.daimler.emst2.fhi.sendung.model.SendContext;

public class Sendung060 implements ISendService {

    @Override
    public boolean sendeAuftrag(SendContext sendContext) {
        SendungenService sendung = new SendungenService();
        return sendung.sendeAuftrag(sendContext);
    }

}
