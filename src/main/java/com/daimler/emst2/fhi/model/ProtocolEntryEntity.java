package com.daimler.emst2.fhi.model;

import java.util.Date;
import java.util.logging.Logger;


/**
 * Entity wrapper for SendungsProtokollEintrag.
 * @author THB
 *
 */
public class ProtocolEntryEntity implements
IProtocolEntryEntity {

    private static final Logger LOG = Logger.getLogger(ProtocolEntryEntity.class.getName());

    private final IProtocolEntry protocolEntry;

    private final IUebersetzungstexte uebersetzter;

    public ProtocolEntryEntity(IProtocolEntry pProtokollEintrag, IUebersetzungstexte pUebersetzter) {
        super();
        protocolEntry = pProtokollEintrag;
        uebersetzter = pUebersetzter;
    }

    public Object getId() {
        nullPointerLogging(getTaskId(), getSeverity(), getProtocolMessage());
        String resultId = getTaskId().getId().toString() + getSeverity().getId().toString()
        + getProtocolMessage().getId().toString();
        return resultId;
    }

    private void nullPointerLogging(IProcessId taskId, SeverityEnum severity, IProtocolMessage protocolMessage) {
        if (taskId==null) {
            LOG.severe(createIsNullMessage("TaskId/IProcessId", "object"));
        }
        else if (taskId.getId() == null) {
            LOG.severe(createIsNullMessage("TaskId/IProcessId::getId()", "attribute"));
        }
        if (severity==null) {
            LOG.severe(createIsNullMessage("SeverityEnum", "object"));
        }
        else if (severity.getId() == null) {
            LOG.severe(createIsNullMessage("SeverityEnum::getId()", "attribute"));
        }
        if (protocolMessage == null) {
            LOG.severe(createIsNullMessage("IProtocolMessage", "object"));
        }
        else if (protocolMessage.getId() == null) {
            LOG.severe(createIsNullMessage("IProtocolMessage::getId()", "attribute"));
        }
    }

    private String createIsNullMessage(String desc, String type) {
        return String.format("%s %s is null", desc, type);
    }

    @Override
    public Date getTimestamp() {
        return getProtokollEintrag().getTimestamp();
    }

    @Override
    public IProtocolMessage getProtocolMessage() {
        return getProtokollEintrag().getProtocolMessage();
    }

    @Override
    public SeverityEnum getSeverity() {
        return getProtokollEintrag().getSeverity();
    }

    @Override
    public IProcessId getTaskId() {
        return getProtokollEintrag().getTaskId();
    }

    @Override
    public boolean isUserAcknowledged() {
        return getProtokollEintrag().isUserAcknowledged();
    }

    @Override
    public void setUserAcknowledged(boolean pUserAcknowledged) {
        getProtokollEintrag().setUserAcknowledged(pUserAcknowledged);
    }

    @Override
    public String toString() {
        return getProtokollEintrag().toString();
    }

    protected IProtocolEntry getProtokollEintrag() {
        return protocolEntry;
    }

    @Override
    public String getTaskIdGui() {
        String result = uebersetzter.getText(getTaskId().getTuebKey());
        return result;
    }

    @Override
    public String getProtocolMessageGui() {
        String result = uebersetzter.getText(getProtocolMessage().getTuebKey(), getProtocolMessage().getParameter());
        return result;
    }

    @Override
    public String getSeverityGui() {
        String result = uebersetzter.getText(SeverityEnum.DEFAULT_TUEB_PREFIX + getSeverity().getTuebKey());
        return result;
    }

    @Override
    public int compareTo(IProtocolEntry pO) {
        if (pO == null) {
            return 1;
        }
        if (this.getProtokollEintrag() == null) {
            return -1;
        }
        return this.getProtokollEintrag().compareTo(pO);
    }

    @Override
    public boolean isImportant() {
        return getProtokollEintrag().isImportant();
    }

    @Override
    public int getMessageNumber() {
        return getProtokollEintrag().getMessageNumber();
    }
}
