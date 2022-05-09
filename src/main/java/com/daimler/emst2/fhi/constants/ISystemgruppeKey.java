package com.daimler.emst2.fhi.constants;

/**
 * Interface für den Container eines Systemgruppen-Schluessels. Mit diesem kann der
 * <code>Konfigurationsservice</code> nach den Werten einer Systemwert-Gruppe befragt werden.
 * @author thb
 */
public interface ISystemgruppeKey {
    /**
     * Liefert den gesuchten Key für den angefragten Systemwert.
     */
    public String getKey();
}
