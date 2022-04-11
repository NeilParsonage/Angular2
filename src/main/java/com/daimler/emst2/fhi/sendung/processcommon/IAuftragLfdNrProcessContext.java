package com.daimler.emst2.fhi.sendung.processcommon;

import java.util.Map;

import com.daimler.emst2.fhi.jpa.model.Systemwerte;

public interface IAuftragLfdNrProcessContext extends IAuftragProcessContext {
    /**
     * Dient zum Ablegen der ermittelten aktuell vergebenen Laufenden Nummern im Prozess-Context.
     * Key: Systemwert-Name fuer die betreffende laufende Nummer
     * Value: Wert der laufenden Nummer
     */
    public void setCurrentLfdNrMap(Map<String, Systemwerte> pLfdNrMap);

    /**
     * Liefert die im Context abgelegte Map der ermittelten aktuell vergebenen Laufenden Nummern.
     * Format @see #setCurrentLfdNrMap(Map)
     */
    public Map<String, Systemwerte> getCurrentLfdNrMap();
}
