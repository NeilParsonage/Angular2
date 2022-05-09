package com.daimler.emst2.fhi.util;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.daimler.emst2.fhi.aspects.dao.BenutzerverwaltungDao;
import com.daimler.emst2.fhi.jpa.dao.SequenceDao;
import com.daimler.emst2.fhi.jpa.dao.W73rimpoDao;
import com.daimler.emst2.fhi.jpa.model.W73rimpo;
import com.daimler.emst2.fhi.model.TaktTelegram;

public class TaktTelegramDaoHelper {

    private static final Logger LOG = Logger.getLogger(BenutzerverwaltungDao.class.getName());

    public static final TaktTelegramDaoHelper create() {
        return new TaktTelegramDaoHelper();
    }

    /*
    private static final String SQL_INSERT_SEND_TO_TAKT = "Insert into W73rimpo ("
            .concat("Impo_Quelle_System").concat(",").concat("Impo_Id")
            .concat(",").concat("Impo_Status").concat(",")
            .concat("Impo_System_Id").concat(",")
            .concat("Impo_Eingangsimpuls_Id").concat(",").concat("Impo_Pnr")
            .concat(",").concat("Impo_Rueckmelde_Zeitpunkt").concat(",")
            .concat("Impo_Variable_Daten").concat(") VALUES (").concat("'FHI'")
            .concat(",") // Impo_Quelle_System
            .concat("Impo_Id_Seq.NEXTVAL").concat(",") // Impo_Id
            .concat("'O'").concat(",") // Impo_Status
            .concat("'FHIN'").concat(",") // Impo_System_Id
            .concat("?").concat(",") // Impo_Eingangsimpuls_Id :: Pi_Impuls_Id
            .concat("?").concat(",") // Impo_Pnr :: Pi_Pnr
            .concat("?").concat(",") // Impo_Rueckmelde_Zeitpunkt :: Pi_Zeit
            .concat("?").concat(")"); // Impo_Variable_Daten :: Pi_Variable_Daten
    */

    public void saveTelegram(W73rimpoDao taktTelegramDao, TaktTelegram pTelegram, SequenceDao seqDao) {
        if (LOG.isLoggable(Level.FINE)) {
            LOG.fine(MessageFormat
                    .format("Takt-Telegramm Nachricht: '{0}' vor Bearbeitung - Länge {1} Zeichen.",
                            pTelegram.getMessage(), pTelegram.getMessage()
                                    .length()));
        }

        Long newImpoId = seqDao.nextSequenceImpoId();

        W73rimpo telegram = new W73rimpo();
        telegram.setImpoId(newImpoId);
        telegram.setImpoStatus("O");
        telegram.setImpoQuelleSystem("FHI");
        telegram.setImpoSystemId("FHIN");
        telegram.setImpoEingangsimpulsId(getEingangsimpulsId(pTelegram.getMessage()));
        telegram.setImpoPnr(getPnr(pTelegram.getMessage()));
        telegram.setImpoVariableDaten(getVariableDaten(pTelegram.getMessage()));
        telegram.setImpoRueckmeldeZeitpunkt(DateTimeHelper.getAktuellesDatum());
        taktTelegramDao.save(telegram);

        /*Object[] insertArgs = new Object[] {
                getEingangsimpulsId(pTelegram.getMessage()),
                getPnr(pTelegram.getMessage()), new Date(),
                getVariableDaten(pTelegram.getMessage()) };
        
        int result = getJdbcTemplate().update(SQL_INSERT_SEND_TO_TAKT,
                insertArgs);*/

        LOG.info("Takt-Telegramm eingestellt mit Nachricht: '"
                 + pTelegram.getMessage()
                 + "'");
    }

    private String getEingangsimpulsId(String message) {
        return message.substring(10, 10 + 14).trim();
    }

    private String getPnr(String message) {
        // 25,8 -> 24,8
        return message.substring(24, 24 + 8).trim();
    }

    private String getVariableDaten(String message) {
        // 52
        return message.substring(51).trim();
    }
}
