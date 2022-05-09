package com.daimler.emst2.fhi.jpa.dao.sp;

import java.math.BigDecimal;

import javax.persistence.StoredProcedureQuery;

import com.daimler.emst2.fhi.constants.ConstDbParameter;
import com.daimler.emst2.fhi.model.vorgang.IDbStandardResult;
import com.daimler.emst2.fhi.sendung.model.DbStandardResult;

public class AbstractStoredProcedureBase {

    protected IDbStandardResult createDbStandardResult(StoredProcedureQuery query) {
        BigDecimal vorgangId = (BigDecimal)query.getOutputParameterValue(ConstDbParameter.OUTPARAMETER_VORGANG_ID);
        BigDecimal status = (BigDecimal)query.getOutputParameterValue(ConstDbParameter.OUTPARAMETER_STATUS);
        return new DbStandardResult(vorgangId, status);
    }

}
