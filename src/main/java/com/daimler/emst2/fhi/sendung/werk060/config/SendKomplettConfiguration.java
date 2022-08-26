package com.daimler.emst2.fhi.sendung.werk060.config;

import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.werk.config.AbstractSendConfiguration;

/**
 * Knoten fuer die Komplettsendung.
 */
public class SendKomplettConfiguration extends AbstractSendConfiguration {

    public SendKomplettConfiguration() {

        this.type = SendTypeEnum.KOMPLETT;
    }

    @Override
    public SendTypeEnum getType() {
        return SendTypeEnum.KOMPLETT;
    }


    @Override
    protected void setupCheckEnumList() {

    }


    @Override
    protected void setupActionEnumList() {

    }
}