package com.daimler.emst2.fhi.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

import com.daimler.emst2.fhi.services.LoggingService;
import com.daimler.emst2.fhi.services.UebersetzungService;

public class Protocol {

    /**
     * attribute to remember the called action (e.g. "fhi-sendung")
     */
    private IProcessId actionId;

    private final String logAusloeserDetails;

    /**
     * aktuelle Protokolleintraege
     */
    private final List<ProtocolEntry> entryList;

    private final LoggingService loggingService;

    private final UebersetzungService uebersetzungService;

    // LOGGING: for using the same transactionId
    private Long transactionId;

    public Protocol(IProcessId pActionId, String pLogAusloeserDetails, LoggingService pLoggingService,
            UebersetzungService pUebersetzungService) {
        actionId = pActionId;
        uebersetzungService = pUebersetzungService;
        loggingService = pLoggingService;
        entryList = new ArrayList<ProtocolEntry>();
        logAusloeserDetails = pLogAusloeserDetails;

    }

    /** (non-Javadoc)
     * @see IProtocol#getAllUnacknowledgedWarningOrWorse()
     */
    public List<ProtocolEntry> getAllUnacknowlegedEntriesEvenOrWorse(SeverityEnum pSeverity) {
        List<ProtocolEntry> result = new ArrayList<ProtocolEntry>();
        Iterator<ProtocolEntry> cursor = entryList.iterator();
        while (cursor.hasNext()) {
            ProtocolEntry current = cursor.next();
            if (!current.isUserAcknowledged() && current.getSeverity().isHigherOrEqual(pSeverity)) {
                result.add(current);
            }
        }
        return result;
    }

    public IProcessId getActionForProtocol() {
        return actionId;
    }

    // public ISearchResult<IProtocolEntryEntity> getAsSearchResult(IUebersetzungstexte pUebersetzter, boolean pDeliverDebug) {
    public List<ProtocolEntryEntity> getAsSearchResult(IUebersetzungstexte pUebersetzter, boolean pDeliverDebug) {
        List<ProtocolEntry> allEntries = getAllEntries();
        Collections.sort(allEntries);
        List<ProtocolEntryEntity> entriesAsEntities = new ArrayList<ProtocolEntryEntity>();
        for (IProtocolEntry protokollEintrag : allEntries) {
            if (!pDeliverDebug && protokollEintrag.getSeverity().isLower(SeverityEnum.INFO)) {
                continue;
            }
            ProtocolEntryEntity entity = new ProtocolEntryEntity(protokollEintrag, pUebersetzter);
            entriesAsEntities.add(entity);
        }
        return entriesAsEntities;
        /*ISearchResult<IProtocolEntryEntity> result = new SearchResult<IProtocolEntryEntity>(entriesAsEntities);
        return result;*/
    }

    public boolean existsRelevantEntryEvenOrWorse(SeverityEnum pSeverity) {
        List<ProtocolEntry> allUnacknowlegedEntriesEvenOrWorse = getAllUnacknowlegedEntriesEvenOrWorse(pSeverity);
        return !allUnacknowlegedEntriesEvenOrWorse.isEmpty();
    }

    public boolean existsErrorOrWorse() {
        boolean result = false;
        for (IProtocolEntry iProtocolEntry : entryList) {
            SeverityEnum severity = iProtocolEntry.getSeverity();
            if (severity.isLower(SeverityEnum.ERROR)) {
                // severity < WARN - weiter
                continue;
            }
            // sonst haben wir einen Eintrag gefunden - break (true)
            result = true;
            break;
        }
        return result;
    }

    /**
     * Kennzeichnet die "unacknowledged" ProtocolEntries als "acknowledged" und entfernt sie aus der Liste
     * der aktuellen Entries.
     */
    public void acknowledgeAll() {
        for (IProtocolEntry protocolEntry : entryList) {
            // nur zur Sicherheit - Fehler koennen nicht uebersteuert werden!
            if (protocolEntry.getSeverity().isLower(SeverityEnum.ERROR)) {
                protocolEntry.setUserAcknowledged(true);
            }
        }
    }

    /**
     * @see IProtocol#addEntry(IProtocolEntry)
     */
    public void addEntry(ProtocolEntry pEntry) {
        if (entryList.contains(pEntry)) {
            return; // add just once
        }
        if (entryList.stream().filter(e -> isSameMessage(pEntry, e)).count() > 0) {
            return; // add just once
        }

        entryList.add(pEntry);
        logEntryViaService(pEntry);
    }

    private boolean isSameMessage(ProtocolEntry pEntry, ProtocolEntry e) {
        IProtocolMessage protocolMessageA = pEntry.getProtocolMessage();
        IProtocolMessage protocolMessageB = e.getProtocolMessage();

        boolean hasSameMessage = protocolMessageA.getTuebKey().equals(protocolMessageB.getTuebKey());
        boolean hasSamePlaceholders =
                Arrays.equals(protocolMessageA.getParameter(), protocolMessageB.getParameter());
        return hasSameMessage && hasSamePlaceholders;
    }

    private void logEntryViaService(IProtocolEntry pEntry) {
        Assert.notNull(pEntry);
        String defaultSprache = uebersetzungService.getDefaultSprache();

        String actionIdTueb = this.actionId.getTuebKey();
        String ausloeserStart = uebersetzungService.getText(actionIdTueb, defaultSprache);
        String ausloeser = ausloeserStart + "-" + logAusloeserDetails;

        SeverityEnum severity = pEntry.getSeverity();
        IProcessId taskId = pEntry.getTaskId();
        String taskIdTueb = taskId.getTuebKey();
        String textTask = uebersetzungService.getText(taskIdTueb, defaultSprache);

        IProtocolMessage protocolMessage = pEntry.getProtocolMessage();
        String tuebKey = protocolMessage.getTuebKey();
        String[] parameter = protocolMessage.getParameter();
        String text = uebersetzungService.getText(tuebKey, parameter, defaultSprache);

        // IUser currentUser = ThreadLocalUserInformation.getUserInformation();
        this.transactionId = loggingService.log(this.transactionId, severity,
                ausloeser, textTask + " - " + text, getUsernameForLogging());
    }

    private static String getUsernameForLogging() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return "anonymous";
        }
        return authentication.getName();
    }

    /**
     * @see IProtocol#getAllEntries()
     */
    public List<ProtocolEntry> getAllEntries() {
        return entryList;
    }

    public String getDebugString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Protocol for action (tueb): ");
        sb.append(actionId.getTuebKey());
        sb.append("\n");
        sb.append(" Protocol-entries: ");
        for (IProtocolEntry iEntry : entryList) {
            sb.append(iEntry.toString());
            sb.append("\n");
        }

        return sb.toString();
    }

    public void setActionId(IProcessId pActionId) {
        this.actionId = pActionId;
    }

}
