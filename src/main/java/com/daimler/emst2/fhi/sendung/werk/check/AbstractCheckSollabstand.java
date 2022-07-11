package com.daimler.emst2.fhi.sendung.werk.check;

import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.model.Protocol;
import com.daimler.emst2.fhi.model.SeverityEnum;
import com.daimler.emst2.fhi.sendung.constants.ProtocolMessageEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.sendung.werk.precondition.SendPreconditionEnum;
import com.daimler.emst2.fhi.util.SimpleDatatypeHelper;

public abstract class AbstractCheckSollabstand extends AbstractSendCheck {

    public AbstractCheckSollabstand(ProtocolService pProtocolService) {
        super(pProtocolService);
    }

    @Override
    protected void init() {
        addPrecondition(SendPreconditionEnum.RESTRIKTIONEN_UPTODATE);
        setStepIdentifierEnum(getInitCheckEnum());
    }

    /**
     * Liefert den SendCheckEnum, fuer den die Check-Klasse verwendet wird.
     * @return
     */
    protected abstract SendCheckEnum getInitCheckEnum();

    /**
     * Liefert die Sollabstandsverletzung fuer den zu ueberpruefenden Sollabstand. Ist die Verletzung
     * > 0, so ist eine Warnung zu erzeugen.
     */
    protected abstract Integer getSollabstandsVerletzung(Auftraege pAuftrag);

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean doExecuteImpl(SendContext pContext) {
        // precondition ist erfuellt
        Auftraege auftrag = pContext.getAuftrag();
        Integer sollabstandsVerletzung = getSollabstandsVerletzung(auftrag);
        boolean isVerletzt = isSollabstandVerletzt(sollabstandsVerletzung);

        Protocol protocol = pContext.getProtocol();
        if (isVerletzt) {
            // ProtocolEntry erzeugen
            getProtocolService().addProtocolEntry(pContext, ProtocolMessageEnum.SOLLABSTAND_VERLETZT_WARN,
                    getIdentifier(),
                    SeverityEnum.WARNING);
        }
        else {
            // ProtocolEntry erzeugen
            getProtocolService().addDebugProtocolEntry(pContext, getIdentifier());
        }
        return !isVerletzt;
    }

    private boolean isSollabstandVerletzt(Integer pVerletzung) {
        int verletzungInt = SimpleDatatypeHelper.getIntFromInteger(pVerletzung);
        return verletzungInt > 0;
    }

}
