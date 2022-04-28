package com.daimler.emst2.fhi.services;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daimler.emst2.fhi.constants.LogsatzEnum;
import com.daimler.emst2.fhi.jpa.dao.DebugLogsaetzeDao;
import com.daimler.emst2.fhi.jpa.dao.LogsaetzeDao;
import com.daimler.emst2.fhi.jpa.model.DebugLogsaetze;
import com.daimler.emst2.fhi.jpa.model.Logsaetze;
import com.daimler.emst2.fhi.model.SeverityEnum;
import com.daimler.emst2.fhi.util.DateTimeHelper;

@Service
public class LoggingService {

    private static final String QUERY_NEXT_TRANSACTION_SEQUENCE = "SELECT TRANSAKTION_SEQ.NEXTVAL FROM DUAL";

    private static final int MAX_LAENGE_TEXT = 250;

    private static final int MAX_LAENGE_AUSLOESER = 30;

    @Autowired
    LogsaetzeDao logsatzDao;

    @Autowired
    DebugLogsaetzeDao debugLogsatzDao;

    @PersistenceContext
    EntityManager em;

    // private SequenceHelperDao sequenceHelperDao;

    public LoggingService() {
        super();
    }

    protected Long insertLogsatz(String ausloeser, String text, LogsatzEnum logsatzEnum) {
        return insertLogsatz(null, ausloeser, text, logsatzEnum);
    }

    /**
     * Schreibt in die Tabelle Logsaetze.
     */
    protected Long insertLogsatz(Long trxSeqNumber, String ausloeser, String text, LogsatzEnum logsatzEnum) {
        String nachrichtId = logsatzEnum.getNachrichtId();
        Logsaetze satz = new Logsaetze();

        if ((ausloeser != null) && (ausloeser.length() > MAX_LAENGE_AUSLOESER)) {
            ausloeser = ausloeser.substring(ausloeser.length() - MAX_LAENGE_AUSLOESER + 1);
        }
        satz.setAusloeser(ausloeser);

        if (text.length() > MAX_LAENGE_TEXT) {
            text = text.substring(0, MAX_LAENGE_TEXT - 1);
        }
        if (trxSeqNumber == null) {
            trxSeqNumber = getNextLoggingTrxNumber();
        }
        satz.setTransaktionSeq(trxSeqNumber);
        satz.setText(text);
        satz.setNachrichtId(nachrichtId);
        satz.setZeitstempel(DateTimeHelper.getAktuellesDatum());

        this.logsatzDao.save(satz);
        return trxSeqNumber;
    }

    private Long getNextLoggingTrxNumber() {
        Query nativeQuery = em.createNativeQuery(QUERY_NEXT_TRANSACTION_SEQUENCE);
        BigDecimal result = (BigDecimal)nativeQuery.getSingleResult();
        return result.longValue();
    }

    protected Long insertDebugLogsatz(String ausloeser, String text, LogsatzEnum logsatzEnum) {
        return insertDebugLogsatz(null, ausloeser, text, logsatzEnum);
    }

    /**
     * Schreibt in die Tabelle DebugLogsaetze.
     */
    protected Long insertDebugLogsatz(Long trxSeqNumber, String ausloeser, String text, LogsatzEnum logsatzEnum) {
        String nachrichtId = logsatzEnum.getNachrichtId();
        DebugLogsaetze satz = new DebugLogsaetze();

        if ((ausloeser != null) && (ausloeser.length() > MAX_LAENGE_AUSLOESER)) {
            ausloeser = ausloeser.substring(ausloeser.length() - MAX_LAENGE_AUSLOESER + 1);
        }
        satz.setAusloeser(ausloeser);

        if (trxSeqNumber == null) {
            trxSeqNumber = getNextLoggingTrxNumber();
        }
        satz.setTransaktionSeq(trxSeqNumber);
        if (text.length() > MAX_LAENGE_TEXT) {
            text = text.substring(0, MAX_LAENGE_TEXT - 1);
        }
        satz.setText(text);
        satz.setNachrichtId(nachrichtId);
        satz.setZeitstempel(DateTimeHelper.getAktuellesDatum());

        this.debugLogsatzDao.save(satz);
        return trxSeqNumber;
    }

    public Long debug(String ausloeser, String text, String pUser) {
        return insertDebugLogsatz(ausloeser, text, LogsatzEnum.DEBUG);
    }

    public Long info(String ausloeser, String text, String pUser) {
        insertDebugLogsatz(ausloeser, text, LogsatzEnum.INFORMATION);
        return insertLogsatz(ausloeser, text, LogsatzEnum.INFORMATION);
    }

    public Long warn(String ausloeser, String text, String pUser) {
        insertDebugLogsatz(ausloeser, text, LogsatzEnum.WARNING);
        return insertLogsatz(ausloeser, text, LogsatzEnum.WARNING);
    }

    public Long error(String ausloeser, String text, String pUser) {
        insertDebugLogsatz(ausloeser, text, LogsatzEnum.ERROR);
        return insertLogsatz(ausloeser, text, LogsatzEnum.ERROR);
    }

    public Long stoerung(String ausloeser, String text, String pUser) {
        insertDebugLogsatz(ausloeser, text, LogsatzEnum.STOERUNG);
        return insertLogsatz(ausloeser, text, LogsatzEnum.STOERUNG);
    }

    public Long debug(Long trxSequence, String ausloeser, String text, String pUser) {
        return insertDebugLogsatz(trxSequence, ausloeser, text, LogsatzEnum.DEBUG);
    }

    public Long info(Long trxSequence, String ausloeser, String text, String pUser) {
        insertDebugLogsatz(trxSequence, ausloeser, text, LogsatzEnum.INFORMATION);
        return insertLogsatz(trxSequence, ausloeser, text, LogsatzEnum.INFORMATION);
    }

    public Long warn(Long trxSequence, String ausloeser, String text, String pUser) {
        insertDebugLogsatz(trxSequence, ausloeser, text, LogsatzEnum.WARNING);
        return insertLogsatz(trxSequence, ausloeser, text, LogsatzEnum.WARNING);
    }

    public Long error(Long trxSequence, String ausloeser, String text, String pUser) {
        insertDebugLogsatz(trxSequence, ausloeser, text, LogsatzEnum.ERROR);
        return insertLogsatz(trxSequence, ausloeser, text, LogsatzEnum.ERROR);
    }

    public Long stoerung(Long trxSequence, String ausloeser, String text, String pUser) {
        insertDebugLogsatz(trxSequence, ausloeser, text, LogsatzEnum.STOERUNG);
        return insertLogsatz(trxSequence, ausloeser, text, LogsatzEnum.STOERUNG);
    }

    public Long logException(String sessionId, Exception exception, String pUser) {
        String username = pUser != null ? pUser : "<?>";
        Throwable cause = ExceptionUtils.getRootCause(exception);
        String msg = cause != null ? cause.getMessage() : "<cant find cause>";
        StringBuffer sb = new StringBuffer();
        sb.append("session:").append(sessionId).append(":user:").append(username).append("--").append(msg);
        String messageText = sb.toString();
        insertDebugLogsatz("exception", messageText, LogsatzEnum.ERROR);
        return insertLogsatz("exception", messageText, LogsatzEnum.ERROR);
    }

    public Long log(SeverityEnum severity, String ausloeser, String text, String pUser) {
        return log(null, severity, ausloeser, text, pUser);
    }

    public Long log(Long trxSeqNumber, SeverityEnum severity, String ausloeser, String text, String pUser) {
        Long trxId = null;
        switch (severity) {
            case DEBUG:
                trxId = debug(trxSeqNumber, ausloeser, text, pUser);
                break;
            case INFO:
                trxId = info(trxSeqNumber, ausloeser, text, pUser);
                break;
            case WARNING:
                trxId = warn(trxSeqNumber, ausloeser, text, pUser);
                break;
            case ERROR:
                trxId = error(trxSeqNumber, ausloeser, text, pUser);
                break;
            case FATAL:
                trxId = stoerung(trxSeqNumber, ausloeser, text, pUser);
                break;
            default:
                Long auxTrxId = error("LoggingService", "logging called with undefinde severity - will be logged as error", pUser);
                error(auxTrxId, ausloeser, text, pUser);
                break;
        }
        return trxId;
    }

}