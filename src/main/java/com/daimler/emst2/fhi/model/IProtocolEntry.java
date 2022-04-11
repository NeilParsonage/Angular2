package com.daimler.emst2.fhi.model;

import java.util.Date;

/**
 * Action and Checks generate IProtocolEntries. The IProtocol is a Container of IProtocolEntries
 * @author thi
 */
public interface IProtocolEntry extends Comparable<IProtocolEntry> {

    /**
     * Liefert ein ITuebObject als Id fuer den Task (Check oder Action), der diesen ProtocolEntry
     * erzeugt hat.
     */
    public IProcessId getTaskId();

    public Date getTimestamp();

    public IProtocolMessage getProtocolMessage();

    public SeverityEnum getSeverity();

    public boolean isUserAcknowledged();

    public void setUserAcknowledged(boolean pUserAcknowledged);

    /**
     * XXX - see comment:
     * Additional marker: mark a given message as "important" - no matter what severity given.
     * The obvious design: adding one or more levels of severity (in SeverityEnum). This solution is
     * NOT implemented because: this extra condition is only required temporarily! (written in July 2017 :-)
     * Currently this flag marks messages from db procedure calls with numbers >= 40.000.
     */
    boolean isImportant();

    int getMessageNumber();
}