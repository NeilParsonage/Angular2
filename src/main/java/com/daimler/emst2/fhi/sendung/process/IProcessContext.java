package com.daimler.emst2.fhi.sendung.process;

import java.util.Date;

import com.daimler.emst2.fhi.model.IProtocol;

/**
 * Data Container for any Sendung or Storno Check or Action
 * 
 * @author thi
 */
public interface IProcessContext {

    public String getUser();

    public IProtocol getProtocol();

    public Date getProcessTimestamp();
}
