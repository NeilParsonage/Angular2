package com.daimler.emst2.fhi.sendung.werk.action;

import java.util.Optional;

import com.daimler.emst2.fhi.jpa.dao.ManuellePnrReihenfolgeDao;
import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.jpa.model.ManuellePnrReihenfolge;
import com.daimler.emst2.fhi.model.Protocol;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.util.SendungUtil;

public class ActionAllManuelleReihenfolgeInfoLoeschen extends AbstractSendAction {

    private final ManuellePnrReihenfolgeDao manuellePnrReihenfolgeDao;

    public ActionAllManuelleReihenfolgeInfoLoeschen(SendActionEnum pSendActionEnum, ProtocolService pProtocolService,
            ManuellePnrReihenfolgeDao pManuellePnrReihenfolgeDao) {
        super(pSendActionEnum.getTyp(), pSendActionEnum, pProtocolService);
        manuellePnrReihenfolgeDao = pManuellePnrReihenfolgeDao;
    }

    @Override
    protected void init() {
        //noop
    }

    @Override
    protected boolean doExecuteImpl(SendContext pContext) {
        Auftraege auftrag = pContext.getAuftrag();
        Protocol protocol = pContext.getProtocol();

        //        boolean isOffen = auftrag.isSendungOffen(SendTypeEnum.FHI);
        //        isOffen |= auftrag.isSendungOffen(SendTypeEnum.LMT);
        //        isOffen |= auftrag.isSendungOffen(SendTypeEnum.UBM);
        //        isOffen |= auftrag.isSendungOffen(SendTypeEnum.RHM);

        boolean isOffen = SendungUtil.isSendungOffen(auftrag, SendTypeEnum.FHI);
        isOffen |= SendungUtil.isSendungOffen(auftrag, SendTypeEnum.LMT);
        isOffen |= SendungUtil.isSendungOffen(auftrag, SendTypeEnum.UBM);
        isOffen |= SendungUtil.isSendungOffen(auftrag, SendTypeEnum.RHM);

        if (!isOffen) {
            //delete from MAN_RF where pnr=auftrag.getpnr()
            //ManuellePnrReihenfolge forDelete = manuellePnrReihenfolgeDao.get(auftrag.getPnr());
            Optional<ManuellePnrReihenfolge> forDeleteOpt = manuellePnrReihenfolgeDao.findById(auftrag.getPnr());
            ManuellePnrReihenfolge forDelete = null;
            if (forDeleteOpt.isPresent()) {
                forDelete = forDeleteOpt.get();
            }
            if (forDelete != null) {
                manuellePnrReihenfolgeDao.delete(forDelete);

                getProtocolService().addDebugProtocolEntry(pContext, getIdentifier());
            }
            // XXX handle initial delete without entry in manuellePnrReihenfolge
            // after first action, there is no entry anymore
        }
        return true;
    }

}
