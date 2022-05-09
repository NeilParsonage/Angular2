package com.daimler.emst2.fhi.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daimler.emst2.fhi.aspects.UserInDbContextAnnotation;


@Service
public class LogsaetzeService {

    @PersistenceContext
    EntityManager em;

    @Transactional
    @UserInDbContextAnnotation
    public void writeInfoLogsatz(String ausloeser, String text) {
        writeLogsatz(ausloeser, text, "I");
    }

    @Transactional
    @UserInDbContextAnnotation
    public void writeErrorLogsatz(String ausloeser, String text) {
        writeLogsatz(ausloeser, text, "E");
    }

    @Transactional
    @UserInDbContextAnnotation
    public void writeWarningLogsatz(String ausloeser, String text) {
        writeLogsatz(ausloeser, text, "W");
    }

    @Transactional
    @UserInDbContextAnnotation
    public void writeDebugLogsatz(String ausloeser, String text) {
        writeLogsatz(ausloeser, text, "D");
    }

    private void writeLogsatz(String ausloeser, String text, String logLevel) {
        Query nativeQuery = em.createNativeQuery("call Loggen.L(?, ?, ?)");
        nativeQuery.setParameter(1, ausloeser);
        nativeQuery.setParameter(2, text);
        nativeQuery.setParameter(3, logLevel);
        nativeQuery.executeUpdate();
    }
}