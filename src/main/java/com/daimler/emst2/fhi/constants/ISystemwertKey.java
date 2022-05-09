package com.daimler.emst2.fhi.constants;

/**
 * Interface für den Container eines Systemwert-Schluessels. Mit diesem kann der
 * <code>Konfigurationsservice</code> nach dem Wert eines Systemwerts befragt werden.
 * @author thb
 *
 */
public interface ISystemwertKey {

    /**
     * Liefert den gesuchten Key für den angefragten Systemwert.
     */
    public String getKey();

    /**
     * Liefert den Typ (Klasse), fuer den Wert des gesuchten Systemwerts.
     */
    public Class<?> getValueType();
}
