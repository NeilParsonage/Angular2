package com.daimler.emst2.fhi.sendung.process.precondition;

import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.sendung.process.IProcessContext;

public interface IPreconditionFactory<GenPreconditionEnum extends IProcessId> {

	public IPrecondition<GenPreconditionEnum, ? extends IProcessContext> createPrecondition(
			GenPreconditionEnum pPrecondition);

}