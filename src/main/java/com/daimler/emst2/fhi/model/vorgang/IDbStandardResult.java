package com.daimler.emst2.fhi.model.vorgang;

import java.math.BigDecimal;
import java.util.Collection;

public interface IDbStandardResult {

    /**
     * @deprecated
     */
    @Deprecated
    public boolean isOk();

    /**
     * @deprecated
     */
    @Deprecated
    boolean isWorking();

    /**
     * @deprecated
     */
    @Deprecated
    public boolean isSync();

    public boolean isSyncOk();

    public boolean isNok();

    public boolean isAsync();

    public BigDecimal getVorgangID();

    public Long getVorgangIdLong();

    // public Collection<ICustomLovEntity> getTexte(IUebersetzungstexte uebersetzung);

    boolean isCheckFailed();

    Integer getFehlernr();

    /**
     * @deprecated bitte getAlleWarnungsNr() verwenden. Entfällt beim nächsten Vollmond.
     */
    @Deprecated
    Collection<Integer> getAllFehlernr();

    Collection<Integer> getAlleWarnungsNr();
}