package com.daimler.emst2.fhi.sendung.process.check;

import com.daimler.emst2.fhi.model.IProcessId;

public interface ICheckFactory<C extends IProcessId> {

	public ICheck createCheck(C pCheckId);

}