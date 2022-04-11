package com.daimler.emst2.fhi.model;


/**
 * A wrapper interface to use an IProtocolEntry in the dcx framework in filtered tables
 * 
 * @author thi
 */
public interface IProtocolEntryEntity extends IProtocolEntry {

    public static final String STYLE_ATTRIBUTE_TASKID = "taskid";
    public static final String STYLE_ATTRIBUTE_SEVERITY = "severity";
    public static final String STYLE_ATTRIBUTE_MESSAGE = "message";
    public static final String STYLE_ATTRIBUTE_TIMESTAMP = "timestamp";
    
    public String getTaskIdGui();

    public String getProtocolMessageGui();

    public String getSeverityGui();

}
