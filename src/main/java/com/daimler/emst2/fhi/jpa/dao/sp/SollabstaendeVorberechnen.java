package com.daimler.emst2.fhi.jpa.dao.sp;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import com.daimler.emst2.fhi.constants.ConstDbParameter;
import com.daimler.emst2.fhi.model.vorgang.IDbStandardResult;

public class SollabstaendeVorberechnen extends AbstractStoredProcedureBase {

    //    PROCEDURE Fuelle_sollabst (P_user            IN     Applikation_user_fhi.User_id%TYPE,
    //            P_fehler_string   IN     VARCHAR2 DEFAULT NULL,
    //            P_ignore_kzn      IN     NUMBER DEFAULT 0,
    //            P_vorgang_id         OUT Vorgaenge.Vorgang_id%TYPE,
    //            P_status             OUT NUMBER);
    //END Dialogmasken_interface;

    private static final String SQLSP_NAME = "Dialogmasken_interface.Fuelle_sollabst";

    private StoredProcedureQuery query;

    private final EntityManager em;

    public SollabstaendeVorberechnen(EntityManager em) {
        this.em = em;
        initParameters();
    }

    private void initParameters() {
        //super(jdbcTemplate, SQLSP_NAME);
        //setFunction(false);
        // define the stored procedure

        /*
        declareParameter(new SqlParameter(ConstDbParameter.INPARAMETER_P_USER, Types.VARCHAR));
        declareParameter(new SqlParameter(ConstDbParameter.INPARAMETER_P_FEHLER_STRING, Types.VARCHAR));
        declareParameter(new SqlParameter(ConstDbParameter.INPARAMETER_P_IGNORE_KZN, Types.NUMERIC));
        declareParameter(new SqlOutParameter(ConstDbParameter.OUTPARAMETER_VORGANG_ID, Types.NUMERIC));
        declareParameter(new SqlOutParameter(ConstDbParameter.OUTPARAMETER_STATUS, Types.NUMERIC));
        
        compile();
        */
        this.query = this.em.createStoredProcedureQuery(SQLSP_NAME);
        query.registerStoredProcedureParameter(ConstDbParameter.INPARAMETER_P_USER, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(ConstDbParameter.INPARAMETER_P_FEHLER_STRING, String.class,
                ParameterMode.IN);
        query.registerStoredProcedureParameter(ConstDbParameter.INPARAMETER_P_IGNORE_KZN, Long.class,
                ParameterMode.IN);
        query.registerStoredProcedureParameter(ConstDbParameter.OUTPARAMETER_VORGANG_ID, BigDecimal.class,
                ParameterMode.OUT);
        query.registerStoredProcedureParameter(ConstDbParameter.OUTPARAMETER_STATUS, BigDecimal.class,
                ParameterMode.OUT);
    }

    public IDbStandardResult execute(final String username) {
        //        ParameterMapper paraMap = new ParameterMapper() {
        //            @Override
        //            public Map<String, Object> createMap(Connection conn) throws SQLException {
        //
        //                // ParameterMap füllen
        //                HashMap<String, Object> inpar = new HashMap<String, Object>();
        //                inpar.put(ConstDbParameter.INPARAMETER_P_USER, username);
        //                inpar.put(ConstDbParameter.INPARAMETER_P_FEHLER_STRING, null);
        //                inpar.put(ConstDbParameter.INPARAMETER_P_IGNORE_KZN, 0);
        //                return inpar;
        //            }
        //        };
        
        // em.createStoredProcedureQuery(SQLSP_NAME, null)
        // ParameterRegistration#enablePassingNulls(true)
        query.setParameter(ConstDbParameter.INPARAMETER_P_USER, username);
        query.setParameter(ConstDbParameter.INPARAMETER_P_FEHLER_STRING, null);
        query.setParameter(ConstDbParameter.INPARAMETER_P_IGNORE_KZN, 0L);

        query.execute();

        return createDbStandardResult(query);
    }

    public static SollabstaendeVorberechnen create(EntityManager em) {
        return new SollabstaendeVorberechnen(em);
    }

}
