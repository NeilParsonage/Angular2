package com.daimler.emst2.fhi.sendung.processcommon;

import java.util.Date;
import java.util.Map;

import org.springframework.util.Assert;

import com.daimler.emst2.fhi.jpa.model.Auftrag;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.util.AuftragUtil;

public class CalcSendeterminForHistUtil {

    private enum CalcSendeterminTypeEnum {
        CALC_SEND, CALC_STORNO;
    }

    public static Date calculateSendeterminHistForSendung(Auftrag auftrag, Date processTimestamp) {
        Date result = calculateSendetermin(auftrag, processTimestamp, CalcSendeterminTypeEnum.CALC_SEND);
        if (result == null) {
            // zweifelhaft - entspricht aber der Anforderung: Ist dies die erste (Teil-)Sendung des Auftrags,
            // so wird der aktuelle Zeitpunkt als "Letzter Sendetermin" protokolliert.
            result = processTimestamp;
        }
        return result;
    }

    public static Date calculateSendeterminHistForStorno(Auftrag auftrag, Date processTimestamp) {
        return calculateSendetermin(auftrag, processTimestamp, CalcSendeterminTypeEnum.CALC_STORNO);
    }

    /**
     * Einfache Berechnung des Sendetermins.
     * 
     * @param auftrag
     * @return "größter" Sendetermin. null wenn alle termine null sind.
     */
    public static Date calculateSimpleSendetermin(Auftrag auftrag) {
        Map<SendTypeEnum, Date> sendeterminMap = AuftragUtil.getSendeterminMap(auftrag);
        Date dateFound = null;
        for (Map.Entry<SendTypeEnum, Date> entry : sendeterminMap.entrySet()) {
            Date refDate = entry.getValue();
            if (refDate == null) {
                continue;
            }
            if (dateFound == null || refDate.after(dateFound)) {
                dateFound = refDate;
            }
        }
        return dateFound;
    }

    private static Date calculateSendetermin(Auftrag auftrag, Date processTimestamp, CalcSendeterminTypeEnum pType) {
        Assert.notNull(processTimestamp);
        long procTime = processTimestamp.getTime();
        Map<SendTypeEnum, Date> sendeterminMap = AuftragUtil.getSendeterminMap(auftrag);
        long timeFound = -1;
        Date dateFound = null;
        for (Map.Entry<SendTypeEnum, Date> entry : sendeterminMap.entrySet()) {
            Date refDate = entry.getValue();
            if (refDate == null) {
                continue;
            }
            long refTime = refDate.getTime();
            if (checkTimeFound(timeFound, refTime, procTime, pType)) {
                timeFound = refTime;
                dateFound = refDate;
            }
        }
        return dateFound;
    }

    private static final boolean checkTimeFound(long pTimeFound, long pRefTime, long pProcTime, CalcSendeterminTypeEnum pType) {
        switch (pType) {
            case CALC_SEND:
                return (pRefTime <= pProcTime && pRefTime > pTimeFound);
            case CALC_STORNO:
                return (pRefTime < pProcTime && pRefTime > pTimeFound);
            default:
                throw new RuntimeException("invalid internal Calculation type for Sendetermin." + pType);
        }
    }
}
