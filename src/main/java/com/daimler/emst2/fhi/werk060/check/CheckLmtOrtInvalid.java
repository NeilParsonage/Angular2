package com.daimler.emst2.fhi.werk060.check;

import com.daimler.emst2.fhi.sendung.check.AbstractCheckOrtError;
import com.daimler.emst2.fhi.sendung.check.SendCheckEnum;
import com.daimler.emst2.fhi.sendung.constants.OrtCheckEnum;
import com.daimler.emst2.fhi.sendung.constants.OrtTypEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.precondition.SendPreconditionEnum;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

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
        boolean isAllowed = isOrtAllowedForParameters(pContext, OrtCheckEnum.RHM_SENDUNG_ERLAUBT_ALL, OrtTypEnum.FHS);
        isAllowed &= isOrtAllowedForParameters(pContext, OrtCheckEnum.FHI_SENDUNG_ERLAUBT_ALL, OrtTypEnum.FHS);
        return isAllowed;
    }
}
