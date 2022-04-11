package com.daimler.emst2.fhi.sendung.processcommon.precondition;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.daimler.emst2.fhi.jpa.dao.OrtCheckCustomDao;
import com.daimler.emst2.fhi.jpa.model.OrtCheck;
import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.sendung.constants.OrtCheckEnum;
import com.daimler.emst2.fhi.sendung.constants.OrtTypEnum;
import com.daimler.emst2.fhi.sendung.process.IProcessContext;
import com.daimler.emst2.fhi.sendung.process.precondition.AbstractPrecondition;
import com.daimler.emst2.fhi.sendung.processcommon.IOrtsdatenProcessContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public class PreconditionOrtCheckSendAndCancelSendData<GenPreconditionEnum extends IProcessId, CTX extends IProcessContext & IOrtsdatenProcessContext>
extends AbstractPrecondition<GenPreconditionEnum, CTX> {

    private final OrtCheckCustomDao ortCheckCustomDao;

    public PreconditionOrtCheckSendAndCancelSendData(GenPreconditionEnum pPreconditionEnum,
            ProtocolService pProtocolService,
            OrtCheckCustomDao pOrtCheckDao) {
        super(pPreconditionEnum, pProtocolService);
        ortCheckCustomDao = pOrtCheckDao;
    }

    @Override
    public boolean doPrepareContext(CTX pContext) {
        List<OrtCheck> ortChecksList = ortCheckCustomDao.getSendAndCancelSendChecks();
        storeCheckInfoInContext(ortChecksList, pContext);
        return true;
    }

    private void storeCheckInfoInContext(List<OrtCheck> pOrtCheckList, CTX pContext) {
        Map<OrtCheckEnum, Map<OrtTypEnum, Set<String>>> checkMap = new HashMap<OrtCheckEnum, Map<OrtTypEnum, Set<String>>>();
        for (OrtCheck iOrtCheck : pOrtCheckList) {
            String ort = iOrtCheck.getOrt();
            String ortTyp = iOrtCheck.getAuftragOrtsTyp();
            String ortCheck = iOrtCheck.getOrtCheck();
            OrtCheckEnum ortCheckEnum = OrtCheckEnum.getOrtCheckEnum(ortCheck);
            OrtTypEnum ortTypEnum = OrtTypEnum.getOrtTypEnum(ortTyp);
            if (!checkMap.containsKey(ortCheckEnum)) {
                Map<OrtTypEnum, Set<String>> ortTypeMap = new HashMap<OrtTypEnum, Set<String>>();
                ortTypeMap.put(OrtTypEnum.FHS, new HashSet<String>());
                ortTypeMap.put(OrtTypEnum.RHM, new HashSet<String>());
                checkMap.put(ortCheckEnum, ortTypeMap);
            }
            Map<OrtTypEnum, Set<String>> ortTypMap = checkMap.get(ortCheckEnum);
            Set<String> positiveSetOrte = ortTypMap.get(ortTypEnum);
            positiveSetOrte.add(ort);
        }

        pContext.setOrtChecksMap(checkMap);
    }
}
