package com.daimler.emst2.fhi.model;

import java.util.Date;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.daimler.emst2.fhi.util.DateTimeHelper;

public class ProtocolEntry implements IProtocolEntry, Comparable<IProtocolEntry> {

    private static final int DEFAULT_MESSAGE_NUMBER = -1;

    private static final int MIN_VALUE_IMPORTANT_MSG = 40000;

    /**
     * Message, die diesen Entry beschreibt (fuer die Ausgabe z.B. in einer Maske)
     */
    private final IProtocolMessage protocolMessage;

    /**
     * Kritikalitaet der Meldung. z.B. Info, Error, ..
     */
    private final SeverityEnum severity;

    /**
     * Identifier fuer den "Erzeuger" dieses ProtocolEntry.
     */
    private final IProcessId taskId;

    /**
     * Zeitstempel - Erzeugung des ProtocolEntry
     */
    private final Date timestamp;

    /**
     * Zeitstempel - Erzeugung des ProtocolEntry
     */
    private boolean userAcknowledged;

    private final int messageNumber;

    public ProtocolEntry(IProcessId pTaskId, IProtocolMessage pProtocolMessage, SeverityEnum pSeverity) {
        this(pTaskId, pProtocolMessage, pSeverity, DEFAULT_MESSAGE_NUMBER);
    }

    public ProtocolEntry(IProcessId pTaskId, IProtocolMessage pProtocolMessage, SeverityEnum pSeverity,
            Integer pMessageNumber) {
        super();
        protocolMessage = pProtocolMessage;
        severity = pSeverity;
        taskId = pTaskId;
        timestamp = DateTimeHelper.getAktuellesDatum();
        messageNumber = pMessageNumber != null ? pMessageNumber.intValue() : DEFAULT_MESSAGE_NUMBER;
    }

    @Override
    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public IProtocolMessage getProtocolMessage() {
        return protocolMessage;
    }

    @Override
    public SeverityEnum getSeverity() {
        return severity;
    }

    @Override
    public IProcessId getTaskId() {
        return taskId;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getProtocolMessage())
        .append(getSeverity())
        .append(getTaskId())
        .toHashCode();
    }

    @Override
    public boolean equals(Object pObj) {
        if (this == pObj) {
            return true;
        }
        else if (pObj == null) {
            return false;
        }
        else if (pObj.getClass() == this.getClass()) {
            boolean result = true;
            ProtocolEntry protocolObj = (ProtocolEntry)pObj;
            // userAck und timestamp spielen hier keine Rolle!!!
            if (this.getProtocolMessage() != null) {
                result &= this.getProtocolMessage().equals(protocolObj.getProtocolMessage());
            }
            if (result && this.getSeverity() != null) {
                result &= this.getSeverity().equals(protocolObj.getSeverity());
            }
            if (result && this.getTaskId() != null) {
                result &= this.getTaskId().equals(protocolObj.getTaskId());
            }
            return result;
        }
        else {
            return false;
        }
    }

    /**
     * 
     * @return  a negative integer, zero, or a positive integer as this object
     *      is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(IProtocolEntry pOther) {
        if (pOther == null) {
            throw new NullPointerException();
        }
        if (this.equals(pOther)) {
            return 0;
        }
        int cmp = getTaskId().getOrderNum().compareTo(pOther.getTaskId().getOrderNum());
        if (cmp != 0) {
            return cmp;
        }
        cmp = Integer.valueOf(getSeverity().getLevel()).compareTo(pOther.getSeverity().getLevel());
        return cmp;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean isUserAcknowledged() {
        return userAcknowledged;
    }

    @Override
    public void setUserAcknowledged(boolean pUserAcknowledged) {
        userAcknowledged = pUserAcknowledged;
    }

    @Override
    public boolean isImportant() {
        return messageNumber >= MIN_VALUE_IMPORTANT_MSG;
    }

    @Override
    public int getMessageNumber() {
        return messageNumber;
    }

}