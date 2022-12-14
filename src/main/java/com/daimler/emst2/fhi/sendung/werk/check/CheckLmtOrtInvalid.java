package com.daimler.emst2.fhi.sendung.werk.check;

import com.daimler.emst2.fhi.sendung.constants.OrtCheckEnum;
import com.daimler.emst2.fhi.sendung.constants.OrtTypEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.sendung.werk.precondition.SendPreconditionEnum;

public class CheckLmtOrtInvalid extends AbstractCheckOrtError {

    public CheckLmtOrtInvalid(ProtocolService pProtocolService) {
        super(pProtocolService);
    }

    @Override
    protected SendCheckEnum getMyCheckEnum() {
        return SendCheckEnum.LMT_ORT_ERR;
    }

    @Override
    protected void init() {
        super.init();
        addPrecondition(SendPreconditionEnum.SEND_HIERARCHY_INFOS_FOR_HISTORY_LMT);
    }

    /**
     * LMT-Sendung ueberprueft den RHM-Ort UND den FHS-Ort.
     */
    @Override
    protected boolean isOrtSendAllowed(SendContext pContext) {
        boolean isAllowed = isOrtAllowedForParameters(pContext, OrtCheckEnum.LMT_AUSFALL_ERLAUBT_ALL, OrtTypEnum.RHM);
        isAllowed &= isOrtAllowedForParameters(pContext, OrtCheckEnum.LMT_AUSFALL_ERLAUBT_ALL, OrtTypEnum.FHS);
        return isAllowed;
    }
}
