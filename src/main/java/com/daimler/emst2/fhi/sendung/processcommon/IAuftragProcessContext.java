package com.daimler.emst2.fhi.sendung.processcommon;

import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.sendung.process.IProcessContext;


public interface IAuftragProcessContext extends IProcessContext {

    public Auftraege getAuftrag();
}
