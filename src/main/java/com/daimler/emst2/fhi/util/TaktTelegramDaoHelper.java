package com.daimler.emst2.fhi.util;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.daimler.emst2.fhi.aspects.dao.BenutzerverwaltungDao;
import com.daimler.emst2.fhi.jpa.dao.W73rimpoDao;
import com.daimler.emst2.fhi.model.TaktTelegram;

public class TaktTelegramDaoHelper {

    private static final Logger LOG = Logger.getLogger(BenutzerverwaltungDao.class.getName());

    public static final TaktTelegramDaoHelper create() {
        return new TaktTelegramDaoHelper();
    }

    public void saveTelegram(W73rimpoDao taktTelegramDao, TaktTelegram pTelegram) {
        if (LOG.isLoggable(Level.FINE)) {
            LOG.fine(MessageFormat
                    .format("Takt-Telegramm Nachricht: '{0}' vor Bearbeitung - Länge {1} Zeichen.",
                            pTelegram.getMessage(), pTelegram.getMessage()
                                    .length()));
        }
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
