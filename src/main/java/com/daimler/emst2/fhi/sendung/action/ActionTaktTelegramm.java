package com.daimler.emst2.fhi.sendung.action;

import org.springframework.stereotype.Component;

import com.daimler.emst2.fhi.jpa.dao.W73rimpoDao;
import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.model.IProtocol;
import com.daimler.emst2.fhi.model.TaktTelegram;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.util.TaktTelegramDaoHelper;
import com.daimler.emst2.fhi.util.TaktTelegramUtil;

@Component
public class ActionTaktTelegramm extends AbstractSendAction {

    private final W73rimpoDao taktTelegramDao;

    public ActionTaktTelegramm(SendTypeEnum pSendTypeEnum, SendActionEnum pActionEnum,
            ProtocolService pProtocolService, W73rimpoDao pTaktTelegramDao) {
        super(pSendTypeEnum, pActionEnum, pProtocolService);
        taktTelegramDao = pTaktTelegramDao;
    }

    @Override
    protected void init() {
        // no static preconditions
        // sendAction is specified via constructor
    }

    @Override
    protected boolean doExecuteImpl(SendContext pContext) {
        IProtocol protocol = pContext.getProtocol();
        Auftraege auftrag = pContext.getAuftrag();

        TaktTelegramUtil util = new TaktTelegramUtil();
        TaktTelegram newTaktTelegram = util.createSendTelegram(getSendTypeEnum(), auftrag);

        TaktTelegramDaoHelper.create().saveTelegram(taktTelegramDao, newTaktTelegram);
        // taktTelegramDao.saveTelegram(newTaktTelegram);

        getProtocolService().addDebugProtocolEntry(protocol, getIdentifier());
        return true;
    }
}
