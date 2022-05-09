package com.daimler.emst2.fhi.model;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class TuebMessageWithParams implements IProtocolMessage {

    //    private static final String[] EMPTY_STRING_ARRAY = new String[] {};

    private final String tuebKey;

    private final String[] params;

    public TuebMessageWithParams(String pTuebKey, String[] pParams) {
        tuebKey = pTuebKey;
        params = pParams;
    }

    @Override
    public Object getId() {
        return getTuebKey();
    }

    @Override
    public String getTuebKey() {
        return tuebKey;
    }

    @Override
    public String[] getParameter() {
        return params;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(tuebKey)
        .append(params)
        .toHashCode();
    }

    @Override
    public boolean equals(Object pObj) {
        if (this == pObj) {
            return true;
        }
        else if (pObj == null) {
            return false;
        }
        else if (pObj.getClass() == this.getClass()) {
            TuebMessageWithParams protocolObj = (TuebMessageWithParams)pObj;
            if (ObjectUtils.equals(this.tuebKey, protocolObj.getTuebKey())) {
                return ObjectUtils.equals(this.params, protocolObj.getParameter());
            }
        }
        return false;
    }
}
