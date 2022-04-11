package com.daimler.emst2.fhi.aspects.dao;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class BenutzerverwaltungDao {

    private static final String CALL_BENUTZERVERWALTUNG_SETZE_USERNAMEN = "call Benutzerverwaltung.Setze_Usernamen(?)";

    private static final Logger LOG = Logger.getLogger(BenutzerverwaltungDao.class.getName());

    private static final String NO_USER = "-empty-";

    @PersistenceContext
    EntityManager em;

    public void setUsernameInContext(final String pUsername) {
        Query nativeQuery = em.createNativeQuery(CALL_BENUTZERVERWALTUNG_SETZE_USERNAMEN);
        nativeQuery.setParameter(1, pUsername);
        nativeQuery.executeUpdate();
        LOG.fine("Usernamen + '" + pUsername + "' erfolgreich im DB-Context gesetzt.");
    }

    public void clearUsernameInContext() {
        setUsernameInContext(NO_USER);
    }

}