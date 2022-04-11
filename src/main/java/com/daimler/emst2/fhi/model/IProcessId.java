package com.daimler.emst2.fhi.model;


public interface IProcessId extends ITuebObject {

	/**
	 * Valid types are SendEnumType and CancelEnumType
	 * @return
	 */
    public ITuebObject getTyp();

    /**
     * Returns an Ordernumber defined by specification for SendEnumType or CancelEnumType
     * 
     * @return
     */
    public Integer getOrderNum();

}
