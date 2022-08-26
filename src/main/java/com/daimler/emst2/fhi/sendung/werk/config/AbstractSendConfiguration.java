package com.daimler.emst2.fhi.sendung.werk.config;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.process.IProcessConfiguration;
import com.daimler.emst2.fhi.sendung.werk.action.SendActionEnum;
import com.daimler.emst2.fhi.sendung.werk.check.SendCheckEnum;

public abstract class AbstractSendConfiguration
        implements IProcessConfiguration<SendTypeEnum, SendCheckEnum, SendActionEnum> {

    private static final Logger LOG = Logger.getLogger(AbstractSendConfiguration.class.getName());

    protected SendTypeEnum type;
    private final List<SendCheckEnum> checkList = new ArrayList<SendCheckEnum>();
    private final List<SendActionEnum> actionList = new ArrayList<SendActionEnum>();;

    @Override
    public SendTypeEnum getType() {
        return type;
    }

    /*********************************
     * SendCheck part
     * 
     */

    protected void addVerifiedCheck(SendCheckEnum sendCheckEnum) {
        if (isNotSendTypeAccepted(sendCheckEnum)) {
            LOG.warning(
                    String.format("SendCheck %s ignore configuration - expected sendType %s instead of %s",
                            sendCheckEnum.name(),
                            sendCheckEnum.getTyp().name(),
                            type.name()
                            ));
            return;
        }
        this.checkList.add(sendCheckEnum);
    }

    private boolean isSendTypeAccepted(SendCheckEnum sendEnum) {
        return sendEnum.getTyp().equals(SendTypeEnum.UNDEFINED)
               || sendEnum.getTyp().equals(getType());
    }

    private boolean isNotSendTypeAccepted(SendCheckEnum sendEnum) {
        return !isSendTypeAccepted(sendEnum);
    }

    abstract protected void setupCheckEnumList();

    @Override
    public void fillCheckEnumList(List<SendCheckEnum> pCheckList) {
        if (checkList.isEmpty()) {
            setupCheckEnumList();
        }
        checkList.forEach(e -> pCheckList.add(e));
    }

    /*********************************
     * SendAction part
     * 
     */

    protected void addVerifiedAction(SendActionEnum sendActionEnum) {
        if (isNotSendTypeAccepted(sendActionEnum)) {
            LOG.warning(
                    String.format("SendAction %s ignore configuration - expected sendType %s instead of %s",
                            sendActionEnum.name(),
                            sendActionEnum.getTyp().name(),
                            type.name()));
            return;
        }
        this.actionList.add(sendActionEnum);
    }

    private boolean isSendTypeAccepted(SendActionEnum sendEnum) {
        return sendEnum.getTyp().equals(SendTypeEnum.UNDEFINED)
               || sendEnum.getTyp().equals(getType());
    }

    private boolean isNotSendTypeAccepted(SendActionEnum sendEnum) {
        return !isSendTypeAccepted(sendEnum);
    }

    abstract protected void setupActionEnumList();

    @Override
    public void fillActionEnumList(List<SendActionEnum> pCheckList) {
        if (actionList.isEmpty()) {
            setupActionEnumList();
        }
        actionList.forEach(e -> pCheckList.add(e));
    }
}
