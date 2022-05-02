package com.daimler.emst2.fhi.sendung.action;

import com.daimler.emst2.fhi.jpa.dao.SequenceDao;
import com.daimler.emst2.fhi.jpa.dao.W73rimpoDao;
import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.model.Protocol;
import com.daimler.emst2.fhi.model.TaktTelegram;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.util.TaktTelegramDaoHelper;
import com.daimler.emst2.fhi.util.TaktTelegramUtil;

public class ActionTaktTelegramm extends AbstractSendAction {

    private final W73rimpoDao taktTelegramDao;
    private final TaktTelegramUtil util;
    private final SequenceDao seqDao;

    public ActionTaktTelegramm(SendTypeEnum pSendTypeEnum, SendActionEnum pActionEnum,
            ProtocolService pProtocolService, W73rimpoDao pTaktTelegramDao, TaktTelegramUtil util, SequenceDao seqDao) {
        super(pSendTypeEnum, pActionEnum, pProtocolService);
        this.taktTelegramDao = pTaktTelegramDao;
        this.seqDao = seqDao;
        this.util = util;

    }

    @Override
    protected void init() {
        // no static preconditions
        // sendAction is specified via constructor
    }

    @Override
    protected boolean doExecuteImpl(SendContext pContext) {
        Protocol protocol = pContext.getProtocol();
        Auftraege auftrag = pContext.getAuftrag();

        //TaktTelegramUtil util = new TaktTelegramUtil();
        TaktTelegram newTaktTelegram = util.createSendTelegram(getSendTypeEnum(), auftrag);

        TaktTelegramDaoHelper.create().saveTelegram(taktTelegramDao, newTaktTelegram, seqDao);
        // taktTelegramDao.saveTelegram(newTaktTelegram);

        getProtocolService().addDebugProtocolEntry(protocol, getIdentifier());
        return true;
    }
}
