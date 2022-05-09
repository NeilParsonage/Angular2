package com.daimler.emst2.fhi.model;

public interface IUebersetzungstexte {

    /**
     * Same as @see {@link IUebersetzungstexte#getText(String)} but with new parameter type to use from java code.
     * Does not support message parameter replacement.
     * 
     * @return localized message.
     */
    String getText(IMessageKey messageKey);

    /**
     * Liefere den Text für den übergebenen Schlüssel. Verwendet wird dabei die
     * übergebene Sprache.
     * 
     * @param key
     * @return
     */
    public abstract String getText(String key);

    /**
     * Liefere den Text für den übergebenen Schlüssel. Verwendet wird dabei die
     * übergebene Sprache. Wird kein Wert zu Schlüssel und Sprache gefunden,
     * wird der übergeben Defaultwert zurückgegeben.
     * 
     * @param key
     * @param defaultText
     * @return
     */
    public abstract String getText(String key, String defaultText);

    /**
     * Liefere den formatierten Text für den übergebenen Schlüssel und die
     * übergebenen Parameter-Strings. Verwendet wird dabei die übergebene
     * Sprache.
     * 
     * @param key
     * @param oa
     * @return
     */
    public abstract String getText(String key, Object[] oa);

    /**
     * Liefere den formatierten Text für den übergebenen Schlüssel und die
     * übergebenen Parameter-Strings. Verwendet wird dabei die übergebene
     * Sprache. Wird kein Wert zu Schlüssel und Sprache gefunden, wird der
     * übergeben Defaultwert zurückgegeben.
     * 
     * @param key
     * @param defaultText
     * @param oa
     * @return
     */
    public abstract String getText(String key, String defaultText, Object[] oa);

    String getText(IMessageKey messageKey, String[] params);

}
