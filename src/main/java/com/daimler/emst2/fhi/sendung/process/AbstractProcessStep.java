package com.daimler.emst2.fhi.sendung.process;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.model.IProtocol;
import com.daimler.emst2.fhi.model.IProtocolEntry;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

/**
 * Diese Abstrakte
 * 
 * @author thi
 *
 * @param <GenPreconditionEnum>
 * @param <GenProcessStepEnum>
 * @param <CTX>
 */
public abstract class AbstractProcessStep<GenPreconditionEnum extends IProcessId, GenProcessStepEnum extends IProcessId, CTX extends IProcessContext>
implements IProcessStep<GenPreconditionEnum, GenProcessStepEnum, CTX> {

    private static final Logger LOG = Logger.getLogger(AbstractProcessStep.class.getName());

    private GenProcessStepEnum stepIdentifierEnum;

    private final List<GenPreconditionEnum> preconditionIdentifierList = new ArrayList<GenPreconditionEnum>();

    private final ProtocolService protocolService;

    protected AbstractProcessStep(ProtocolService pProtocolService) {
        super();
        protocolService = pProtocolService;
        init();
    }

    /**
     * Die statische Belegung der beiden Attribute stepIdentifierEnum und preconditionIdentifierList erfolgt in der
     * konkreten Implementierung der init-Methode.
     */
    protected abstract void init();

    /**
     * Hier muss die eigentliche konkrete Implementierung des Prozess-Schritts erfolgen.
     * Bei der Implementierung zu beachten
     * - Der Prozess muss durch seine Precondition fuer diese Implementierung alles vorbereiten
     *   - dazu schreibt er die zu prüfenden und die zu ändernden Objekte in den {@link IProcessContext}
     * - Der Prozess-Schritt schreibt seinen eigenen {@link IProtocolEntry} in das im {@link IProcessContext} liegende {@link IProtocol}
     * 
     * @return TRUE wenn alles OK ist, andernfalls FALSE
     */
    protected abstract boolean doExecuteImpl(CTX pContext);

    @Override
    public final void doExecute(CTX pContext) {
        LOG.info("ProcessStep " + getClass().getSimpleName() + " wird gestartet.");
        boolean checkOk = doExecuteImpl(pContext);
        LOG.info("ProcessStep " + getClass().getSimpleName() + " ist durchgefuehrt: " + (checkOk ? "ok" : "warning"));
    }

    protected void setStepIdentifierEnum(GenProcessStepEnum pStepIdentifierEnum) {
        stepIdentifierEnum = pStepIdentifierEnum;
    }

    protected void addPrecondition(GenPreconditionEnum pIdentifierEnum) {
        preconditionIdentifierList.add(pIdentifierEnum);
    }

    @Override
    public GenProcessStepEnum getIdentifier() {
        return stepIdentifierEnum;
    }

    @Override
    public List<GenPreconditionEnum> getPreconditionIdentifier() {
        return preconditionIdentifierList;
    }

    protected ProtocolService getProtocolService() {
        return protocolService;
    }

}
