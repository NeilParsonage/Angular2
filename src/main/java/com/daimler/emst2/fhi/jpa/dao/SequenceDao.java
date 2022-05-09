package com.daimler.emst2.fhi.jpa.dao;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

@Component
public class SequenceDao {

    @PersistenceContext
    EntityManager em;

    public Long nextSequenceImpoId() {
        Query nativeQuery = em.createNativeQuery("select IMPO_ID_SEQ.NEXTVAL from dual");
        BigDecimal sequence = (BigDecimal)nativeQuery.getSingleResult();
        return sequence.longValue();
    }
}