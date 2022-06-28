package com.daimler.emst2.fhi.werk060.check;

import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

import com.daimler.emst2.fhi.jpa.model.IAuftragSperrenForBereich;
import com.daimler.emst2.fhi.model.Protocol;
import com.daimler.emst2.fhi.model.SeverityEnum;
import com.daimler.emst2.fhi.sendung.check.AbstractSendCheck;
import com.daimler.emst2.fhi.sendung.check.SendCheckEnum;
import com.daimler.emst2.fhi.sendung.constants.ProtocolMessageEnum;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.precondition.SendPreconditionEnum;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.util.BasisCollectionUtils;
import com.daimler.emst2.fhi.util.BasisStringUtils;

public class CheckAuftragSperren extends AbstractSendCheck {

    public static final String OHNE_BEREICH = "Ohne";

    public static final String SEPERATOR = ", ";

    public CheckAuftragSperren(ProtocolService pProtocolService, SendCheckEnum sendCheckStepIdentifier) {
        super(pProtocolService, sendCheckStepIdentifier);
    }

    @Override
    protected void init() {
        addPrecondition(SendPreconditionEnum.AUFTRAG_SPERREN_FETCHED);
        // setStepIdentifierEnum(this.sendCheck);
    }


    @Override
    protected boolean doExecuteImpl(SendContext pContext) {
        Protocol protocol = pContext.getProtocol();

        checkAuftragSperren(pContext, this.sendCheck.getTyp());

        // ProtocolEntry erzeugen
        getProtocolService().addDebugProtocolEntry(protocol, getIdentifier());
        return true;
    }

    protected void checkAuftragSperren(SendContext pContext, SendTypeEnum sendTyp) {

        Protocol protocol = pContext.getProtocol();

        StringJoiner bereiche = new StringJoiner(SEPERATOR);

        HashMap<String, Long> anzahlSperrenForBereichMap = new HashMap<String, Long>();
        List<IAuftragSperrenForBereich> anzahlSperrenForBereichList = pContext.anzahlSperrenForBereich;
        if (BasisCollectionUtils.isEmptyOrNull(anzahlSperrenForBereichList)) {
            return;
        }

        for (IAuftragSperrenForBereich entry : anzahlSperrenForBereichList) {

            // avoid empty or null Bereich being used as key in Hashmap!
            if (BasisStringUtils.isEmptyOrNull(entry.getBereich())) {
                anzahlSperrenForBereichMap.put(OHNE_BEREICH, entry.getAnzahlForBereich());
            }
            else {
                anzahlSperrenForBereichMap.put(entry.getBereich(), entry.getAnzahlForBereich());
            }

            bereiche.add(entry.getBereich());
        }

        if (anzahlSperrenForBereichList.size() == 1) {
            //            if (anzahlSperrenForBereichMap.containsKey(SendTypeEnum.FHI.name())
            //                || anzahlSperrenForBereichMap.containsKey(SendTypeEnum.RHM.name())) {
            if (anzahlSperrenForBereichMap.containsKey(sendTyp.name())) {

                // Einzelmeldung - FHI or RHM
                String bereichWithSperre = anzahlSperrenForBereichList.get(0).getBereich();

                String[] params = { bereichWithSperre, pContext.auftrag.getPnr() };
                getProtocolService().addProtocolEntry(protocol,
                        ProtocolMessageEnum.AUFTRAG_SPERREN_VERLETZT_SINGULAR_FHI_OR_RHM_WARN,
                        params,
                        getIdentifier(),
                        SeverityEnum.WARNING);

            }
            else if (anzahlSperrenForBereichMap.containsKey(OHNE_BEREICH)) {
                // Einzelmeldung - Leer
                getProtocolService().addProtocolEntry(protocol,
                        ProtocolMessageEnum.AUFTRAG_SPERREN_VERLETZT_SINGULAR_LEER_WARN,
                        pContext.auftrag.getPnr(),
                        getIdentifier(),
                        SeverityEnum.WARNING);
            }
            return;
        }

        // Prepare Protocol Warning for several  Sperren
        String[] params = { pContext.auftrag.getPnr(), bereiche.toString(), };
        getProtocolService().addProtocolEntry(protocol,
                ProtocolMessageEnum.AUFTRAG_SPERREN_VERLETZT_SEVERAL_AREAS_WARN,
                params,
                getIdentifier(),
                SeverityEnum.WARNING);

    }

}
