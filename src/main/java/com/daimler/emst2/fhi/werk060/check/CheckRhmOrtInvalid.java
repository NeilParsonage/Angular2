package com.daimler.emst2.fhi.werk060.check;

import com.daimler.emst2.fhi.sendung.check.AbstractCheckOrtError;
import com.daimler.emst2.fhi.sendung.check.SendCheckEnum;
import com.daimler.emst2.fhi.sendung.constants.OrtCheckEnum;
import com.daimler.emst2.fhi.sendung.constants.OrtTypEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.precondition.SendPreconditionEnum;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public class CheckRhmOrtInvalid extends AbstractCheckOrtError {

    public CheckRhmOrtInvalid(ProtocolService pProtocolService) {
        super(pProtocolService);
    }

    @Override
    protected SendCheckEnum getMyCheckEnum() {
        return SendCheckEnum.RHM_ORT_ERR;
    }

    @Override
    protected void init() {
        super.init();
        addPrecondition(SendPreconditionEnum.SEND_HIERARCHY_INFOS_FOR_HISTORY_RHM);
    }

    /**
     * RHM-Sendung ueberprueft den RHM-Ort UND den FHS-Ort.
     */
    @Override
    protected boolean isOrtSendAllowed(SendContext pContext) {
        boolean isAllowed = isOrtAllowedForParameters(pContext, OrtCheckEnum.RHM_SENDUNG_ERLAUBT_060, OrtTypEnum.RHM);
        return isAllowed;
    }
}
