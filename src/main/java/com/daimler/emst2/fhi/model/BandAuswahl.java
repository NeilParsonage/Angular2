/**
 * 
 */
package com.daimler.emst2.fhi.model;

/*
 * *
 * @author bjb
 */
public enum BandAuswahl implements ITuebObject {
    FHI("FHI", -1, "FHI", "text.bandtueb.fhi"),
    EINS("1", 1, "MO1", "text.bandtueb.band1"),
    ZWEI("2", 2, "MO2", "text.bandtueb.band2"),
    DREI("3", 3, "MO3", "text.bandtueb.band3"),
    NICHTCBU("Nicht CBU", -2, "FHI","text.bandtueb.ckdet"),
    LOGBUCH("logbuch", -8, "", "text.bandtueb.logbuch"),
    GESPERRT("gesperrt", -7, "FHI", "text.bandtueb.gesperrt"),
    VORMONTAGE("vormontage", -6, "VO", "text.bandtueb.vormontage"),
    GESAMT("gesamt", -10, "", "text.bandtueb.gesamt"),
    UNDEFINED("keine", -9, "", "text.bandtueb.undefined");

    private final String label;
    private final Integer value;
    private final String bereich;
    private final String tuebKey;


    private BandAuswahl(String pLabel, Integer pValue, String pBereich, String pTueb) {
        this.label = pLabel;
        this.value = pValue;
        this.bereich = pBereich;
        this.tuebKey = pTueb;
    }

    /**
     * Liefert für Band<X> -> "MO<X>" für alle nicht-Band-Nummern (<> 1, 2,3) wird Bereich "FHI" geliefert.
     * @param pValue
     * @return
     */
    public static final String getBereichFromBand(Integer pValue) {
        for (BandAuswahl bandAuswahl : BandAuswahl.values()) {
            if (bandAuswahl.getValue().equals(pValue)) {
                return bandAuswahl.getBereich();
            }
        }
        return BandAuswahl.FHI.getBereich();
    }

    public boolean isLmtBandauswahl() {
        boolean result = EINS.equals(this);
        result |= ZWEI.equals(this);
        result |= DREI.equals(this);
        return result;
    }

    public boolean isSternenhimmelBand() {
        boolean result = EINS.equals(this);
        result |= ZWEI.equals(this);
        result |= DREI.equals(this);
        result |= VORMONTAGE.equals(this);
        return result;
    }

    public boolean isVormontage() {
        boolean result = VORMONTAGE.equals(this);
        return result;
    }

    public static final String getLmtBandauswahlGui() {
        return "LMT";
    }

    /**
     * Liefert den zum übergebenen Bereich passenden BandAuswahl-Enumwert. Aus "FHI" wird immer BandAuswahl.FHI. NichtCBU hat
     * zwar auch den Bereich FHI - für diese inverse Operation soll aber Eindeutigkeit her!
     * hier
     * @param pBereich
     * @return
     */
    public static final BandAuswahl getBandAuswahl(String pBereich) {
        for (BandAuswahl bandAuswahl : BandAuswahl.values()) {
            if (bandAuswahl.getBereich().equals(pBereich)) {
                return bandAuswahl;
            }
        }
        return BandAuswahl.UNDEFINED;
    }

    public static final BandAuswahl getBandAuswahlFromBandNr(int pBandnr) {
        for (BandAuswahl bandAuswahl : BandAuswahl.values()) {
            if (bandAuswahl.getValue() == pBandnr) {
                return bandAuswahl;
            }
        }
        return BandAuswahl.UNDEFINED;

    }

    public boolean isBereichFhi() {
        return FHI.equals(this);
    }

    public boolean isLogbuch() {
        return LOGBUCH.equals(this);
    }


    public boolean isUndefined() {
        return UNDEFINED.equals(this);
    }

    public boolean isGesperrt() {
        return GESPERRT.equals(this);
    }

    public boolean isGesamt() {
        return GESAMT.equals(this);
    }

    public Integer getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public String getBereich() {
        return bereich;
    }

    @Override
    public String getTuebKey() {
        return tuebKey;
    }

    @Override
    public Object getId() {
        return label;
    }

}
