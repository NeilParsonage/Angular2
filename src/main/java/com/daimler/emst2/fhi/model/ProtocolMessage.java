package com.daimler.emst2.fhi.model;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.daimler.emst2.fhi.sendung.constants.ProtocolMessageEnum;


public class ProtocolMessage implements IProtocolMessage {

    private static final String[] EMPTY_STRING_ARRAY = new String[] {};

    private final ProtocolMessageEnum protocolMessageEnum;

    private final String[] params;

    public ProtocolMessage(ProtocolMessageEnum pProtocolMessageEnum) {
        this(pProtocolMessageEnum, EMPTY_STRING_ARRAY);
    }

    public ProtocolMessage(ProtocolMessageEnum pProtocolMessageEnum, String[] pParams) {
        protocolMessageEnum = pProtocolMessageEnum;
        params = pParams;
    }

    @Override
    public Object getId() {
        return getTuebKey();
    }

    @Override
    public String getTuebKey() {
        return protocolMessageEnum.getDefaultTuebKey();
    }

    @Override
    public String[] getParameter() {
        return params;
    }

    public ProtocolMessageEnum getProtocolMessageEnum() {
        return protocolMessageEnum;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getProtocolMessageEnum())
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
            ProtocolMessage protocolObj = (ProtocolMessage)pObj;

            if (this.getProtocolMessageEnum() != null) {
                return (this.getProtocolMessageEnum().equals(protocolObj.getProtocolMessageEnum()));
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
}
