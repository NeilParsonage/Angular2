package com.daimler.emst2.fhi.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daimler.emst2.fhi.dto.StoredProcedureResultDTO;
import com.daimler.emst2.fhi.jpa.dao.VorgaengeDao;
import com.daimler.emst2.fhi.jpa.model.Vorgaenge;
import com.daimler.emst2.fhi.jpa.model.VorgaengeMeldungen;

@Service
public class VorgangService {

    @Autowired
    VorgaengeDao vorgangDao;

    private List<VorgaengeMeldungen> getVorgaengeMeldungenFromStoredProcedureResult(StoredProcedureResultDTO result) {
        Optional<Vorgaenge> vorgangOptional = this.vorgangDao.findById(result.vorgangId);
        if (!vorgangOptional.isPresent()) {
            return Collections.EMPTY_LIST;
        }
        Vorgaenge vorgang = vorgangOptional.get();
        return vorgang.getVorgaengeMeldungens();
    }

    public List<VorgaengeMeldungen> getFailMessages(StoredProcedureResultDTO result) {
        if (result.status <= 2) { // ignore success
            return Collections.EMPTY_LIST;
        }
        List<VorgaengeMeldungen> meldungen = getVorgaengeMeldungenFromStoredProcedureResult(result);
        return meldungen;
    }

}