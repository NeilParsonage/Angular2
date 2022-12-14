package com.daimler.emst2.fhi.sendung.werk152.config;

import java.util.List;

import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.process.IProcessConfiguration;
import com.daimler.emst2.fhi.sendung.werk.action.SendActionEnum;
import com.daimler.emst2.fhi.sendung.werk.check.SendCheckEnum;

/**
 * Knoten fuer die Komplettsendung.
 */
public class SendKomplettConfiguration implements IProcessConfiguration<SendTypeEnum, SendCheckEnum, SendActionEnum> {

    @Override
    public SendTypeEnum getType() {
        return SendTypeEnum.KOMPLETT;
    }

    @Override
    public void fillCheckEnumList(List<SendCheckEnum> pCheckList) {
        // Komplettsendung hat keine eigenen checks oder actions - ist lediglich "Strukturknoten"
    }

    @Override
    public void fillActionEnumList(List<SendActionEnum> pActionList) {
        // Komplettsendung hat keine eigenen checks oder actions - ist lediglich "Strukturknoten"
    }
}