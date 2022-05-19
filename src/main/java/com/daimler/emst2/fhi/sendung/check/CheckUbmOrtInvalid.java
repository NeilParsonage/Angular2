package com.daimler.emst2.fhi.sendung.check;

import com.daimler.emst2.fhi.sendung.constants.OrtCheckEnum;
import com.daimler.emst2.fhi.sendung.constants.OrtTypEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.precondition.SendPreconditionEnum;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public class CheckUbmOrtInvalid extends AbstractCheckOrtError {

    public CheckUbmOrtInvalid(ProtocolService pProtocolService) {
        super(pProtocolService);
    }

    @Override
    protected SendCheckEnum getMyCheckEnum() {
        return SendCheckEnum.UBM_ORT_ERR;
    }

    @Override
    protected void init() {
        super.init();
        addPrecondition(SendPreconditionEnum.SEND_HIERARCHY_INFOS_FOR_HISTORY_UBM);
    }

    /**
     * UBM-Sendung ueberprueft den RHM-Ort UND den FHS-Ort.
     */
    @Override
    protected boolean isOrtSendAllowed(SendContext pContext) {
        boolean isAllowed = isOrtAllowedForParameters(pContext, OrtCheckEnum.UBM_AUSFALL_ERLAUBT_152, OrtTypEnum.RHM);
        isAllowed &= isOrtAllowedForParameters(pContext, OrtCheckEnum.UBM_AUSFALL_ERLAUBT_152, OrtTypEnum.FHS);
        return isAllowed;
    }
}
