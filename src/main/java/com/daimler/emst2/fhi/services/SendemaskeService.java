package com.daimler.emst2.fhi.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.daimler.emst2.fhi.jpa.dao.sp.SollabstaendeVorberechnen;
import com.daimler.emst2.fhi.model.vorgang.IDbStandardResult;

@Service
public class SendemaskeService {

    @PersistenceContext
    EntityManager em;

    public IDbStandardResult sollabstaendeNeuBerechnen(String user) {
        return SollabstaendeVorberechnen.create(em).execute(getUsername());
    }

    private static String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return "ANONYMOUS";
        }
        return authentication.getName();
    }

}
