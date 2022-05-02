package com.daimler.emst2.fhi.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.daimler.emst2.fhi.model.vorgang.IDbStandardResult;
import com.daimler.emst2.fhi.sendung.model.DbStandardResult;

@Service
public class SendemaskeService {

    // TODO Stored Procedure Dialogmasken_interface.Fuelle_sollabst
    public IDbStandardResult sollabstaendeNeuBerechnen(String user) {
        // TODO Auto-generated method stub
        return null;
    }

    protected IDbStandardResult createDbStandardResult(BigDecimal vorgangId, BigDecimal status) {

        IDbStandardResult result = new DbStandardResult(vorgangId, status);

        if (result.isNok() || result.isCheckFailed()) {
            // refreshVorgang(result);
        }
        return result;
    }
}
