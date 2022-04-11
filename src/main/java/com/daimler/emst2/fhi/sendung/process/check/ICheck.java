package com.daimler.emst2.fhi.sendung.process.check;

import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.sendung.process.IProcessContext;
import com.daimler.emst2.fhi.sendung.process.IProcessStep;

public interface ICheck<GenPreconditionEnum extends IProcessId, GenCheckEnum extends IProcessId, CTX extends IProcessContext>
extends IProcessStep<GenPreconditionEnum, GenCheckEnum, CTX> {

}