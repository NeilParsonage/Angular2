package com.daimler.emst2.fhi.sendung.processcommon;

import com.daimler.emst2.fhi.jpa.model.Auftrag;
import com.daimler.emst2.fhi.sendung.process.IProcessContext;


public interface IAuftragProcessContext extends IProcessContext {

    public Auftrag getAuftrag();
}
