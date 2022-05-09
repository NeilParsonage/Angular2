package com.daimler.emst2.fhi.model;

public interface ITuebObject extends IIdObject {

    /**
     * Liefert den i18n String eines Objects.
     * 
     * @return i18n String des Entities.
     */
    public String getTuebKey();
}