package com.daimler.emst2.fhi.sendung.model;

import com.daimler.emst2.fhi.model.vorgang.ResultEnum;

public interface IFrwVorgang {

    /**
     * @return true if this vorgang is a root vorgang
     */
    public boolean isVorgangRoot();

    /**
     * Getter for property <code>meldungNr</code>.
     */
    public Integer getMeldungNr();

    /**
     * Getter for property <code>meldungTyp</code>.
     */
    public String getMeldungTyp();

    /**
     * Getter for property <code>meldung</code>.
     */
    public String getMeldung();

    /**
     * Getter for property <code>schrittNr</code>.
     */
    public Integer getSchrittNr();

    /**
     * Getter for property <code>vorgangId</code>.
     */
    public Long getVorgangId();

    /**
     * Setter for property <code>vorgangId</code>.
     */
    public void setVorgangId(Long pVorgangId);

    /**
     * Getter for property <code>status</code>.
     */
    public Integer getStatus();

    /**
     * Setter for property <code>status</code>.
     */
    public void setStatus(Integer pStatus);

    public ResultEnum getResult();

    public String getTuebTname();

    public String getTuebSystem();

    public String getTuebProgname();

    public String getParamstring();

    /**
     * @return Message parameters as String[].
     */
    String[] getMessageParams();

    public Boolean getIgnore();
}
