package com.daimler.emst2.fhi.werk060.sendung;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.sendung.ISend;
import com.daimler.emst2.fhi.sendung.ISendDefinitionFactory;
import com.daimler.emst2.fhi.sendung.SendProcess;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.util.SendungUtil;

public class SendDefinitionFactory implements ISendDefinitionFactory {

    private ISend root;

    private SendDefinitionFactory() {
        super();
        init();
    }

    public static SendDefinitionFactory create() {
        return new SendDefinitionFactory();
    }

    private void init() {
        // hier wird die Struktur der Sendungsknoten (ISend) fest- und angelegt
        SendKomplettConfiguration sendKomplettConfig = new SendKomplettConfiguration();
        ISend sendKomplett = new SendProcess(sendKomplettConfig);

        SendLmtConfiguration sendLmtConfig = new SendLmtConfiguration();
        ISend sendLmt = new SendProcess(sendLmtConfig);

        SendFhiConfiguration sendFhiConfig = new SendFhiConfiguration();
        ISend sendFhi = new SendProcess(sendFhiConfig);

        SendRhmConfiguration sendRhmConfig = new SendRhmConfiguration();
        ISend sendRhm = new SendProcess(sendRhmConfig);

        sendKomplett.addChild(sendLmt);
        sendLmt.addChild(sendFhi);
        sendLmt.addChild(sendRhm);

        root = sendKomplett;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ISend> createSendList(Auftraege pAuftrag, SendTypeEnum pSendTypeEnum) {
        Assert.isTrue(pSendTypeEnum != null && !SendTypeEnum.UNDEFINED.equals(pSendTypeEnum));
        ISend requestedSend = findSendNodeByType(pSendTypeEnum);
        @SuppressWarnings("unchecked")
        List<ISend> sendSubTreeFlat = (List<ISend>) requestedSend.getSubtreeFlat();
        List<ISend> shrinkedListForAuftrag = shrinkSendListForAuftrag(pAuftrag, sendSubTreeFlat);
        return shrinkedListForAuftrag;
    }

    private List<ISend> shrinkSendListForAuftrag(Auftraege pAuftrag, List<ISend> sendSubTreeFlat) {
        List<ISend> listForAuftrag = new ArrayList<ISend>();
        for (ISend iSendNode : sendSubTreeFlat) {
            ISend sendNode = iSendNode;
            SendTypeEnum type = sendNode.getType();
            // XXX AUFTRAG - Teilsendung "Komplett" ist keine "echte" Teilsendung, sondern aggregiert nur alle uebrigen
            // - diese wird daher auch nicht als expliziter SubProzess verwendet.
            // ACHTUNG: muss hier auch mit continue weitermachen, da der Auftrag keinen Status Komplettsendung kennt.
            if (SendTypeEnum.KOMPLETT.equals(type)) {
                continue;
            }
            // @TODO JHE test new implementation
            // if (pAuftrag.isSendungOffen(type)) {
            if (SendungUtil.isSendungOffen(pAuftrag, type)) {
                listForAuftrag.add(sendNode);
            }
        }
        return listForAuftrag;
    }

    private ISend findSendNodeByType(SendTypeEnum pSendTypeEnum) {

        @SuppressWarnings("unchecked")
        List<ISend> completeSendTreeFlat = (List<ISend>) getRootNode().getSubtreeFlat();
        ISend resultNode = null;
        for (ISend iSendNode : completeSendTreeFlat) {
            ISend sendNode = iSendNode;
            if (pSendTypeEnum.equals(sendNode.getType())) {
                resultNode = sendNode;
                break;
            }
        }
        return resultNode;
    }

    private ISend getRootNode() {
        return root;
    }


}
