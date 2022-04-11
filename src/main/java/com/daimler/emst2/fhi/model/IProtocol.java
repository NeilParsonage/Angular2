package com.daimler.emst2.fhi.model;

import java.util.List;

/**
 * IProtocol is a container for IProtocolEntries created by Actions or Checks
 * @author thi
 */
public interface IProtocol {

    /**
     * Gibt den Identifier der vom Benutzer intendierte Sendung zurueck
     */
    public IProcessId getActionForProtocol();

    /**
     * Fuegt einen Protokoll Eintrag {@link IProtocolEntry} zu diesem Protokoll hinzu
     * @param pEntry
     */
    public void addEntry(IProtocolEntry pEntry);

    /**
     * Gibt alle Protokolleintraege zurueck
     */
    public List<IProtocolEntry> getAllEntries();

    /**
     * Konvertiert das Protokoll als SearchResult, damit es direkt in Tabellenmodellen verwendet werden kann
     */
    // public ISearchResult<IProtocolEntryEntity> getAsSearchResult(IUebersetzungstexte pUebersetzter, boolean pDeliverDebug);

    /**
     * Liefert eine Liste aller Protokolleinträge, die noch nicht vom Benutzer bestätigt worden sind und
     * deren Severity gleich oder höher der angegebenen ist.
     */
    public List<IProtocolEntry> getAllUnacknowlegedEntriesEvenOrWorse(SeverityEnum pSeverity);

    /**
     * Liefert true, falls noch mindestens ein "unacknowledged" ProtocolEntry mit mindestens der angegebenen Severity oder
     * hoeher existiert - false sonst.
     */
    public boolean existsRelevantEntryEvenOrWorse(SeverityEnum pSeverity);

    /**
     * Liefert true, falls mindestens ein ProtocolEntry mit Severity ERROR oder
     * hoeher existiert - false sonst.
     */
    public boolean existsErrorOrWorse();

    /**
     * Setzt den Status aller enthaltenen ProtocolEntries auf "acknowledged".
     */
    public void acknowledgeAll();

    public String getDebugString();
}