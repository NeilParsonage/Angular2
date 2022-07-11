package com.daimler.emst2.fhi.sendung.process;

import java.util.List;

import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.model.IProtocolEntry;
import com.daimler.emst2.fhi.sendung.process.precondition.IPrecondition;
import com.daimler.emst2.fhi.sendung.werk.check.SendCheckEnum;

/**
 * A IProcessStep is a Part of an {@link IProcess}. ProcessSteps are ordered. A realization implements the doExecute and processes the 
 * intended operations. If an IProcessStep needs any data an {@link IPrecondition} must be implemented which collects all needed
 * data for the IProcessStep and stores it in the {@link IProcessContext}. As a result a process step creates an {@link IProtocolEntry}
 * and stores it in the {@link IProcess} that can be accessed through the {@link IProcessContext}
 * 
 * @author thb
 *
 * @param <GenPreconditionEnum>
 * @param <GenCheckEnum>
 * @param <CTX>
 */
public interface IProcessStep<GenPreconditionEnum extends IProcessId, GenCheckEnum extends IProcessId, CTX extends IProcessContext> {

    /**
     * Gibt den Identifier der Pruefung dieser Implementierung zurueck z.B.{@link SendCheckEnum}
     */
    public GenCheckEnum getIdentifier();

    /**
     * Gibt eine Liste von CheckPreconditionIdentifier zurueck, die mit der Factory dann instanziert werden koennen
     */
    public List<GenPreconditionEnum> getPreconditionIdentifier();

    /**
     * Fuehrt den Check durch. Daten zur Durchfuehrung liegen im IProcessContext.
     * Schreibt das Protokoll fort.
     * Tritt ein fataler Fehler auf, wird false zurueckgeliefert - true sonst.
     */
    public void doExecute(CTX context);

}
