package com.daimler.emst2.fhi.sendung.process.precondition;

import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.sendung.process.IProcessContext;

public interface IPrecondition<GenPreconditionEnum extends IProcessId, CTX extends IProcessContext> {

    /**
     * Gibt den zu dieser Implementierung zugehoerigen Identifier {z.B. @link SendPreconditionEnum}
     */
    public GenPreconditionEnum getIdentifier();

    /**
     * Bereitet den Context weiter fuer die Pruefung vor. Schreibt Daten in den Context.
     */
    public boolean doPrepareContext(CTX pContext);
}
