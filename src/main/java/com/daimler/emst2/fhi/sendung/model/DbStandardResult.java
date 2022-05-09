package com.daimler.emst2.fhi.sendung.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Logger;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
import org.springframework.util.Assert;

import com.daimler.emst2.fhi.model.IUebersetzungstexte;
import com.daimler.emst2.fhi.model.vorgang.FrwVorgang;
import com.daimler.emst2.fhi.model.vorgang.IDbStandardResult;
import com.daimler.emst2.fhi.model.vorgang.IMeldungVorgangContainer;
import com.daimler.emst2.fhi.model.vorgang.ResultEnum;
import com.daimler.emst2.fhi.util.BasisStringUtils;

public class DbStandardResult implements IDbStandardResult, IMeldungVorgangContainer {

    private static final Logger LOG = Logger.getLogger(DbStandardResult.class.getName());

    private static final Integer ROOT_VORGANG_SCHRITT_NR = Integer.valueOf(0);

    static final class FehlertextTransformerClass implements Transformer {
        private final IUebersetzungstexte uebersetzung;

        FehlertextTransformerClass(IUebersetzungstexte pUebersetzung) {
            this.uebersetzung = pUebersetzung;
        }

        @Override
        public Object transform(Object input) {
            IFrwVorgang frwVorgang = (IFrwVorgang)input;
            if (frwVorgang.getIgnore().booleanValue()) {
                /*
                 * Wenn der Vorgang ignoriert wird, wird null geliefert.
                 */
                return null;
            }
            String tname = frwVorgang.getTuebTname();
            String[] params = BasisStringUtils.convertVorgangParamstring(frwVorgang.getParamstring());
            String dbMeldung = frwVorgang.getMeldung();

            // keine Meldung von DB und kein TUEB
            if (BasisStringUtils.isEmptyOrNull(dbMeldung) && BasisStringUtils.isEmptyOrNull(tname)) {
                return null;
            }

            String meldungText = uebersetzung.getText(tname, dbMeldung, params);
            String meldungTyp = frwVorgang.getMeldungTyp();
            return createEntity(meldungTyp, meldungText);
        }

        private CustomLovEntity createEntity(String typ, String meldung) {
            return new CustomLovEntity(typ, meldung);
        }
    }

    static final class IgnorePredicateClass implements Predicate {

        @Override
        public boolean evaluate(Object input) {
            IFrwVorgang frwVorgang = (IFrwVorgang)input;
            return !frwVorgang.getIgnore().booleanValue();
        }

    }

    static final class WarningPredicateClass implements Predicate {

        @Override
        public boolean evaluate(Object input) {
            IFrwVorgang frwVorgang = (IFrwVorgang)input;
            return "W".equals(frwVorgang.getMeldungTyp());
        }

    }

    static final class FehlernummernTransformerClass implements Transformer {

        @Override
        public Object transform(Object input) {
            IFrwVorgang frwVorgang = (IFrwVorgang)input;

            return frwVorgang.getMeldungNr();
        }

    }

    /**
     * Root-Vorgang - ist direkt nach Erzeugen des @see {@link DbStandardResult} null.
     * Wird erst initialisiert durch @see {@link AbstractBackendLogicService#refreshVorgang(IDbStandardResult)}
     */
    private FrwVorgang vorgang;

    /**
     * Erster Vorgangsschritt, der eine "nicht ignorierte" Meldung enthält.
     * 
     * "Meldung"-Vorgang - ist direkt nach Erzeugen des @see {@link DbStandardResult} null.
     * Wird erst initialisiert durch @see {@link AbstractBackendLogicService#refreshVorgang(IDbStandardResult)}
     */
    private FrwVorgang meldungVorgang;

    /**
     * Collection aller Vorgangsschritte des gesamten Vorgangs.
     * Wird erst initialisiert durch @see {@link AbstractBackendLogicService#refreshVorgang(IDbStandardResult)}
     */
    private Collection<? extends FrwVorgang> vorgaenge;

    public DbStandardResult(BigDecimal vorgangId, BigDecimal status) {
        this(vorgangId.longValue(), status.intValue());
    }

    public DbStandardResult(long vorgangId, int status) {
        this(Long.valueOf(vorgangId), Integer.valueOf(status));
    }

    public DbStandardResult(Long vorgangId, Integer status) {
        this.vorgang = new FrwVorgang(vorgangId, status);
    }

    /**
     * @deprecated
     */
    @Override
    @Deprecated
    public boolean isOk() {
        return getResult().isOk() || getResult().isAsynch();
    }

    /**
     * @deprecated
     */
    @Override
    @Deprecated
    public boolean isWorking() {
        return getResult().isAsynch();
    }

    @Override
    public boolean isSyncOk() {
        return getResult().isOk();
    }

    @Override
    public boolean isNok() {
        return getResult().isNok();
    }

    @Override
    public boolean isAsync() {
        return getResult().isAsynch();
    }

    /**
     * @deprecated
     */
    @Override
    @Deprecated
    public boolean isSync() {
        return getResult().isOk() || getResult().isNok();
    }

    @Override
    public final BigDecimal getVorgangID() {
        return BigDecimal.valueOf(getVorgangIdLong().longValue());
    }

    @Override
    public final Long getVorgangIdLong() {
        return this.vorgang.getVorgangId();
    }

    @Override
    public String toString() {
        return "VorgangId: " + getVorgangIdLong() + " Status: " + getResult() + " [" + super.toString() + "]";
    }

    protected final FrwVorgang getVorgang() {
        return this.vorgang;
    }

    protected final ResultEnum getResult() {
        return this.vorgang.getResult();
    }

    private void setVorgang(FrwVorgang pVorgang) {
        this.vorgang = pVorgang;
    }

    public final void setVorgaenge(Collection<? extends FrwVorgang> pVorgaenge) {
        FrwVorgang vorgangTmp = null;
        // Im Vorgang wird der erste Teilvorgang mit nicht ignorierter Meldung hinterlegt.
        for (FrwVorgang vorgangElem : pVorgaenge) {
            if (vorgangElem.getIgnore() == false && vorgangElem.getMeldungNr() != null) {
                setMeldungVorgang(vorgangElem);
                break;
            }
        }
        // set the fully loaded root vorgang
        for (FrwVorgang vorgangElem : pVorgaenge) {
            if (vorgangElem.isVorgangRoot()) {
                vorgangTmp = vorgangElem;
                break;
            }
        }
        if (vorgangTmp == null) {
            throw new RuntimeException("Vorgang mit der id " + getVorgang().getVorgangId()
                    + " ist auf der DB nicht vorhanden!");
        }
        this.vorgaenge = pVorgaenge;
        setVorgang(vorgangTmp);
    }

    //    @SuppressWarnings("unchecked")
    //    public final Collection<ICustomLovEntity> getTexte(IUebersetzungstexte uebersetzung) {
    //        if (CollectionUtils.isEmpty(vorgaenge)) {
    //            return Collections.emptyList();
    //        }
    //
    //        /*
    //         * Es wird fuer jeden Vorgang ein Eintrag erzeugt.
    //         * null als Eintrag ist moeglich!
    //         */
    //        return CollectionUtils.collect(this.vorgaenge, new FehlertextTransformerClass(uebersetzung),
    //                new ArrayList<String>(this.vorgaenge.size()));
    //    }

    @Override
    public boolean isCheckFailed() {
        return getResult().isCheckFailed();
    }

    @Override
    public Integer getFehlernr() {
        if (isCheckFailed() || isNok()) {
            FrwVorgang frwVorgang = getMeldungVorgang();
            if (frwVorgang != null) {
                return frwVorgang.getMeldungNr();
            }
            LOG.warning("Status ist: " + getResult().toString() + " , aber kein Vorgang mit Meldung gefunden.");

        }
        LOG.warning("getFehlernr() aufgerufen - Vorgang hat aber Status: " + getResult().toString());
        return Integer.valueOf(-1);
    }

    /**
     * @deprecated bitte getAlleWarnungsNr() verwenden.
     */
    @Override
    @SuppressWarnings("unchecked")
    @Deprecated
    public Collection<Integer> getAllFehlernr() {
        if (isCheckFailed()) {
            Collection<IFrwVorgang> nichtIgnorierteVorgaenge = CollectionUtils.select(vorgaenge,
                    new IgnorePredicateClass());

            Collection<Integer> fehlernummern = CollectionUtils.collect(nichtIgnorierteVorgaenge,
                    new FehlernummernTransformerClass(), new HashSet<Integer>());

            fehlernummern.remove(null);

            return fehlernummern;
        }
        throw new RuntimeException("Fehlerhafter Zustand!");
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Integer> getAlleWarnungsNr() {
        if (isCheckFailed()) {
            final Collection<IFrwVorgang> nichtIgnorierteVorgaenge = CollectionUtils.select(vorgaenge,
                    new IgnorePredicateClass());

            final Collection<IFrwVorgang> rueckfrageVorgaenge = CollectionUtils.select(nichtIgnorierteVorgaenge,
                    new WarningPredicateClass());
            final Collection<Integer> fehlernummern = CollectionUtils.collect(rueckfrageVorgaenge,
                    new FehlernummernTransformerClass(), new HashSet<Integer>());

            fehlernummern.remove(null);

            Assert.isTrue(!fehlernummern.isEmpty(), "Vorgang [" + getVorgangIdLong() + "] hat kein Rückfragen!");

            return fehlernummern;
        }
        throw new RuntimeException("Fehlerhafter Zustand!");
    }

    @Override
    public FrwVorgang getMeldungVorgang() {
        return meldungVorgang;
    }

    private void setMeldungVorgang(FrwVorgang pMeldungVorgang) {
        meldungVorgang = pMeldungVorgang;
    }
}
