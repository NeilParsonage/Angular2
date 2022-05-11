package com.daimler.emst2.fhi.werk060.check;

import com.daimler.emst2.fhi.sendung.check.AbstractCheckOrtError;
import com.daimler.emst2.fhi.sendung.check.SendCheckEnum;
import com.daimler.emst2.fhi.sendung.constants.OrtCheckEnum;
import com.daimler.emst2.fhi.sendung.constants.OrtTypEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.precondition.SendPreconditionEnum;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public class CheckFhiOrtInvalid extends AbstractCheckOrtError {

    public CheckFhiOrtInvalid(ProtocolService pProtocolService) {
        super(pProtocolService);
    }

    @Override
    protected void init() {
        super.init();
        addPrecondition(SendPreconditionEnum.SEND_HIERARCHY_INFOS_FOR_HISTORY_FHI);
    }

    @Override
    protected SendCheckEnum getMyCheckEnum() {
        return SendCheckEnum.FHI_ORT_ERR;
    }

    /**
     * FHI-Sendung ueberprueft lediglich den FHS-Ort - der RHM-Ort ist irrelevant.
     */
    @Override
    protected boolean isOrtSendAllowed(SendContext pContext) {
        return isOrtAllowedForParameters(pContext, OrtCheckEnum.FHI_SENDUNG_ERLAUBT_060, OrtTypEnum.FHS);
    }
}
