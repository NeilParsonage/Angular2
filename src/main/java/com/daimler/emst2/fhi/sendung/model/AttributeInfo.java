package com.daimler.emst2.fhi.sendung.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class AttributeInfo implements Serializable {
    private final String key;

    private final String name;

    private Class<?> attributeClass;

    private final boolean sortableAttribute;

    private boolean isTableRowModelInfo = false;

    public AttributeInfo(String pName, Class<?> pAttributeClass, String pKey) {
        this(pName, pAttributeClass, pKey, true);
    }

    public AttributeInfo(boolean pIsTableRowModelInfo, String pName, Class<?> pAttributeClass, String pKey) {
        this(pIsTableRowModelInfo, pName, pAttributeClass, pKey, true);
    }

    public AttributeInfo(String pName, Class<?> pAttributeClass, String pKey, boolean pSortable) {
        this(false, pName, pAttributeClass, pKey, pSortable);
    }

    public AttributeInfo(boolean pIsTableRowModelInfo, String pName, Class<?> pAttributeClass, String pKey,
            boolean pSortable) {
        Assert.notNull(pName, "Name von AttributeInfo darf nicht 'null' sein, "
                              + "sonnst gibt es beim Aufruf von hashCode() einen NullPointer...!");

        this.name = pName;
        this.key = pKey;
        this.sortableAttribute = pSortable;
        this.setAttributeClass(pAttributeClass);
        this.isTableRowModelInfo = pIsTableRowModelInfo;
    }

    public String getKey() {
        return this.key;
    }

    public String getName() {
        return this.name;
    }

    public boolean isSortableAttribute() {
        return this.sortableAttribute;
    }

    public Class<?> getAttributeClass() {
        return this.attributeClass;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (hashCode() == obj.hashCode() && obj instanceof AttributeInfo) {
            final AttributeInfo attributeInfo = (AttributeInfo)obj;
            return StringUtils.equals(getName(), attributeInfo.getName())
                    && StringUtils.equals(getKey(), attributeInfo.getKey())
                    && ObjectUtils.equals(getAttributeClass(), attributeInfo.getAttributeClass())
                    && isSortableAttribute() == attributeInfo.isSortableAttribute();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        return getName() + " ( Key: " + getKey() + " Klasse: "
                + (getAttributeClass() == null ? "unbekannt" : ClassUtils.getShortClassName(getAttributeClass()))
                + " ~ sortableAttribute:" + isSortableAttribute() + ") [" + super.toString() + "]";
    }

    /**
     * @return the isTableRowModelInfo
     */
    public boolean isTableRowModelInfo() {
        return isTableRowModelInfo;
    }

    /**
     * @param pIsTableRowModelInfo the isTableRowModelInfo to set
     */
    public void setTableRowModelInfo(boolean pIsTableRowModelInfo) {
        isTableRowModelInfo = pIsTableRowModelInfo;
    }

    public Boolean isOPAttributeClass() {
        return isOPAttributeClass(getAttributeClass());
    }

    private Boolean isOPAttributeClass(Class<?> pClass) {
        if (Long.class.equals(pClass) || Integer.class.equals(pClass) || Date.class.equals(pClass)
                || DcxDateTime.class.equals(pClass)
                || Float.class.equals(pClass) || Double.class.equals(pClass)) {
            return true;
        }
        return false;

    }

    private Boolean isValidAttributeClass(Class<?> pClass) {
        if (isOPAttributeClass(pClass) || String.class.equals(pClass) || Boolean.class.equals(pClass)) {
            return true;
        }
        return false;
    }

    /**
     * @param pAttributeClass the attributeClass to set
     */
    private void setAttributeClass(Class<?> pAttributeClass) {
        if (!isValidAttributeClass(pAttributeClass)) {
            throw new IllegalArgumentException(
                    "Commited Classtype " + pAttributeClass.toString()
                            + " is not suitable for attribute AttributeClass. Please chose a framework class!");
        }
        attributeClass = pAttributeClass;
    }

}
