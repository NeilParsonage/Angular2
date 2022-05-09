package com.daimler.emst2.fhi.collections;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.daimler.emst2.fhi.util.BasisStringUtils;

public class HashMapDefault<K> extends HashMap<K, String> {

    private static final Logger LOG = Logger.getLogger(HashMapDefault.class.getName());

    private static final String TRENNZEICHEN = "§";

    public HashMapDefault() {
        super();
    }

    /**
     * Liefert den technischen Schlüssel für den Zugriff auf die HashMapDefault
     * zusammengesetzt aus übergebenem key, TRENNZEICHEN und defaultText.
     * 
     * @param key
     * @param defaultText
     * @return
     */
    public static String erstelleKeyMitDefaultWert(String key, String defaultText) {
        if (key == null) {
            key = "";
        }
        String result = key;
        int indexOfTrennzeichen = key.indexOf(TRENNZEICHEN);
        if (indexOfTrennzeichen >= 0) {
            if (BasisStringUtils.isEmptyOrNull(defaultText)) {
                defaultText = key.substring(indexOfTrennzeichen);
            }
            else {
                LOG.warning("Im Key "
                        + key
                        + " ist ein Default-Text enthalten und es wurde explizit ein DefaultText zur Ermittlung mitgegeben. "
                        + defaultText + ". Verwende letzteren.");
            }
            key = key.substring(0, indexOfTrennzeichen);
        }
        if ((defaultText != null) && (defaultText.length() > 0)) {
            if (defaultText.indexOf(TRENNZEICHEN) >= 0) {
                throw new RuntimeException("Trennzeichen wurde im defaultText verwendet. (" + defaultText
                        + "). Dies ist nicht erlaubt - bitte defaultText korrigieren");
            }
            result = key + TRENNZEICHEN + defaultText;
        }
        return result;
    }

    /**
     * Liefert den Eintrag aus der Map oder den Defaulteintrag, falls unter dem
     * Key nichts gefunden wird. Aufruf erfolgt mit "KEY§Default text". Der
     * Defaulttext und Trennzeichen ist optional. Wird der Key nicht gefunden
     * und der Default wert ist leer ist das Ergebnis: "???KEY".
     * 
     * @param key
     * @return Liefert den gefunden Eintrag oder den Defaulttext
     */
    @Override
    public String get(Object key) {
        if (key instanceof String) {

            String strKey = (String)key;
            int pos = strKey.indexOf(TRENNZEICHEN);
            String defaultText = "???" + key;

            if (pos > -1) {
                key = strKey.substring(0, pos);
                defaultText = strKey.substring(pos + 1);
            }
            strKey = ((String)key).toUpperCase();

            String value = super.get(strKey);

            if (value != null) {
                return value;
            }

            int posPunkt = strKey.indexOf(".");

            if (posPunkt > -1) {
                strKey = strKey.substring(posPunkt);
                strKey = "ALLE" + strKey;

                value = super.get(strKey);

                if (value != null) {
                    return value;
                }
            }

            strKey = ((String)key).toUpperCase();
            strKey = "ALLE." + strKey;

            value = super.get(strKey);

            if (value != null) {
                return value;
            }
            else {
                if (LOG.isLoggable(Level.INFO) && !((String)key).startsWith("test.")) {
                    LOG.info("*** No value found for key: '" + key + "'");
                }
                return defaultText;
            }
        }
        else {
            return super.get(key);
        }
    }

}
