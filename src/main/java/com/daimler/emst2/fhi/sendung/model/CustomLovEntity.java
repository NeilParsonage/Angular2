package com.daimler.emst2.fhi.sendung.model;

import java.io.Serializable;

import org.dom4j.tree.AbstractEntity;


public class CustomLovEntity extends AbstractEntity implements Serializable { // ICustomLovEntity,

    private String stringAttribute;

    private String stringAttribute2;

    private String stringAttribute3 = null;

    private Integer intAttribute = null;

    public CustomLovEntity() {
        super();
    }

    public CustomLovEntity(Integer pIntAttribute) {
        super();
        this.intAttribute = pIntAttribute;
    }

    public CustomLovEntity(String pStringAttribute, String pStringAttribute2) {
        super();
        this.stringAttribute = pStringAttribute;
        this.stringAttribute2 = pStringAttribute2;
    }

    public CustomLovEntity(String pStringAttribute, String pStringAttribute2, String pStringAttribute3) {
        super();
        this.stringAttribute = pStringAttribute;
        this.stringAttribute2 = pStringAttribute2;
        this.stringAttribute3 = pStringAttribute3;
    }

    /**
     * @return the stringAttribute
     */
    public String getStringAttribute() {
        return stringAttribute;
    }

    /**
     * @param pStringAttribute the stringAttribute to set
     */
    public void setStringAttribute(String pStringAttribute) {
        this.stringAttribute = pStringAttribute;
    }

    //    public boolean dataEquals(IEntity pEntity) {
    //        // empty dummy implementation - this is just a fake entity
    //        return false;
    //    }

    public String getStringAttribute2() {
        return stringAttribute2;
    }

    public void setStringAttribute2(String pStringAttribute2) {
        this.stringAttribute2 = pStringAttribute2;
    }

    public String getStringAttribute3() {
        return stringAttribute3;
    }

    public void setStringAttribute3(String pStringAttribute3) {
        this.stringAttribute3 = pStringAttribute3;
    }

    public Object getAttribute(AttributeInfo attributeInfo) {
        throw new RuntimeException("Methode ist nicht Implementiert! [" + this + "]");
    }

    public void setAttributeToNull(AttributeInfo attributeInfo) {
        throw new RuntimeException("Methode ist nicht Implementiert! [" + this + "]");
    }

    public Integer getIntAttribute() {
        return intAttribute;
    }

    public void setIntAttribute(Integer intAttribute) {
        this.intAttribute = intAttribute;
    }

}
