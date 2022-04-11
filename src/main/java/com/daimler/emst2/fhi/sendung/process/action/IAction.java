package com.daimler.emst2.fhi.sendung.process.action;

import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.sendung.process.IProcessContext;
import com.daimler.emst2.fhi.sendung.process.IProcessStep;

/**
 * Aktionen Interface für Sendung und Storno Aktionen unterschiedlicher Typen.
 * Dieses Interface dient der Typisierung von {@link IProcessStep} zu Aktionen in Sendungen oder Stornierungen
 * 
 * @author thi
 *
 * @param <GenPreconditionEnum> action precondition
 * @param <GenActionEnum> action type
 * @param <CTX> process context
 */
public interface IAction<GenPreconditionEnum extends IProcessId, GenActionEnum extends IProcessId, CTX extends IProcessContext>
extends IProcessStep<GenPreconditionEnum, GenActionEnum, CTX> {

}
