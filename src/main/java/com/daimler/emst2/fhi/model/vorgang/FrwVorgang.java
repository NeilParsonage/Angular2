package com.daimler.emst2.fhi.model.vorgang;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;


public class FrwVorgang { // implements IFrwVorgang {

    private Long vorgangId;

    private Integer status;

    private final Integer schrittNr;

    private final Integer meldungNr;

    private final String meldungTyp;

    private final String meldung;

    private final boolean vorgangRoot;

    public FrwVorgang(Long pVorgangId, Integer pStatus) {
        super();
        Assert.notNull(pVorgangId);
        Assert.notNull(pStatus);
        setVorgangId(pVorgangId);
        setStatus(pStatus);
        schrittNr = 0;
        meldungNr = -1;
        meldungTyp = "I";
        meldung = StringUtils.EMPTY;
        vorgangRoot = true;
    }

    public ResultEnum getResult() {
        int intStatus = getStatus() != null ? getStatus().intValue() : -1;
        return ResultEnum.getResultEnum(intStatus);
    }

    /**
     * @return the vorgangId
     */
    public Long getVorgangId() {
        return vorgangId;
    }

    /**
     * @param pVorgangId the vorgangId to set
     */
    public void setVorgangId(Long pVorgangId) {
        vorgangId = pVorgangId;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param pStatus the status to set
     */
    public void setStatus(Integer pStatus) {
        status = pStatus;
    }

    /**
     * @return the schrittNr
     */
    public Integer getSchrittNr() {
        return schrittNr;
    }

    /**
     * @return the meldung
     */
    public String getMeldung() {
        return meldung;
    }

    public Integer getMeldungNr() {
        return meldungNr;
    }

    public String getMeldungTyp() {
        return meldungTyp;
    }

    public String getParamstring() {
        throw new UnsupportedOperationException("operation not supported in FrwVorgang");
    }

    public String[] getMessageParams() {
        throw new UnsupportedOperationException("operation not supported in FrwVorgang");
    }

    public String getTuebTname() {
        throw new UnsupportedOperationException("operation not supported in FrwVorgang");
    }

    public String getTuebSystem() {
        throw new UnsupportedOperationException("operation not supported in FrwVorgang");
    }

    public Boolean getIgnore() {
        throw new UnsupportedOperationException("operation not supported in FrwVorgang");
    }

    public String getTuebProgname() {
        throw new UnsupportedOperationException("operation not supported in FrwVorgang");
    }

    public boolean isVorgangRoot() {
        return vorgangRoot;
    }
}
