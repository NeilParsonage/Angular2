package com.daimler.emst2.fhi.model;

public interface IMessageKey {

    /**
     * @return the key (technical id e.g. tueb.tname: "msg.test.info") representing this message text
     */
    String getKey();

    /**
     * @return the number of parameters the message to this key may substitute - which parameter requires/allows which substitution
     * is part of the key specification.
     */
    int getNumParameters();

}
