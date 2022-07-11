package com.daimler.emst2.fhi.sendung;

import java.util.List;

import com.daimler.emst2.fhi.jpa.model.Auftrag;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;

public interface ISendDefinitionFactory {

    /**
     * Liefert die Liste der für den gegebenen Auftrag und die angeforderte Sendungsart
     * durchzuführenden Teilsendungen.
     */
    public List<ISend> createSendList(Auftrag pAuftrag, SendTypeEnum pSendTypeEnum);
}
