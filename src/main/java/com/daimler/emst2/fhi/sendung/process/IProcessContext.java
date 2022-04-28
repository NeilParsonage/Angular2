package com.daimler.emst2.fhi.sendung.process;

import java.util.Date;

import com.daimler.emst2.fhi.model.Protocol;

/**
 * Data Container for any Sendung or Storno Check or Action
 * 
 * @author thi
 */
public interface IProcessContext {

    public String getUser();

    public Protocol getProtocol();

    public Date getProcessTimestamp();
}
