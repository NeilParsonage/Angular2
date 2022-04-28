package com.daimler.emst2.fhi.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;

import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.jpa.model.MvAlleRelCode;
import com.daimler.emst2.fhi.sendung.constants.OrtTypEnum;
import com.daimler.emst2.fhi.sendung.constants.SendStatusEnum;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;

public class AuftragUtil {

    public static final String NUMERIC_TRUE_STRING = "1";

    public static final String NUMERIC_FALSE_STRING = "0";

    public static final String getOrt(Auftraege pAuftrag, OrtTypEnum pOrtType) {
        switch (pOrtType) {
            case FHS:
                return pAuftrag.getOrtOrginal();
            case RHM:
                return pAuftrag.getOrtRhm();
        }
        throw new RuntimeException("Auftrag kennt keinen Ortstyp: " + pOrtType);
    }

    public static Map<SendTypeEnum, SendStatusEnum> getSendStatusIstMap(Auftraege auftrag) {
        Map<SendTypeEnum, SendStatusEnum> result = new HashMap<SendTypeEnum, SendStatusEnum>();
        List<SendTypeEnum> allTeilSendTypes = SendTypeEnum.getAllTeilSendTypes();
        for (SendTypeEnum sendTypeEnum : allTeilSendTypes) {
            result.put(sendTypeEnum, getSendStatusIst(auftrag, sendTypeEnum));
        }
        return result;
    }

    public static SendStatusEnum getSendStatusIst(Auftraege auftrag, SendTypeEnum pSendTypeEnum) {
        String sendestatus = null;

        switch (pSendTypeEnum) {
            case LMT:
                sendestatus = auftrag.getLmtSendStatus();
                break;
            case FHI:
                sendestatus = auftrag.getFhiSendStatus();
                break;
            case RHM:
                sendestatus = auftrag.getRhmSendStatus();
                break;
            case UBM:
                sendestatus = auftrag.getUbmSendStatus();
                break;
            default:
                break;
        }
        SendStatusEnum status = SendStatusEnum.getSendStatus(sendestatus);
        return status;
    }

    public static void setSendStatusIst(Auftraege auftrag, SendTypeEnum pSendTypeEnum, SendStatusEnum pSendStatus) {
        switch (pSendTypeEnum) {
            case LMT:
                auftrag.setLmtSendStatus(pSendStatus.getStringValue());
                break;
            case FHI:
                auftrag.setFhiSendStatus(pSendStatus.getStringValue());
                break;
            case RHM:
                auftrag.setRhmSendStatus(pSendStatus.getStringValue());
                break;
            case UBM:
                auftrag.setUbmSendStatus(pSendStatus.getStringValue());
                break;
            default:
                break;
        }
    }

    public static Map<SendTypeEnum, Date> getSendeterminMap(Auftraege auftrag) {
        Map<SendTypeEnum, Date> resultMap = new HashMap<SendTypeEnum, Date>();
        resultMap.put(SendTypeEnum.LMT, auftrag.getSendetermin());
        resultMap.put(SendTypeEnum.FHI, auftrag.getSendeterminFhi());
        resultMap.put(SendTypeEnum.RHM, auftrag.getSendeterminRhm());
        resultMap.put(SendTypeEnum.UBM, auftrag.getSendeterminUbm());
        return resultMap;
    }

    public static Integer getTransientSollabstandLmt(Auftraege pAuftrag) {
        if (getLmtSendStatusEnum(pAuftrag).isGesendet()) {
            return null;
        }
        return pAuftrag.getSendeVorschlagDetails().getSollabsLmt();
    }

    public static SendStatusEnum getLmtSendStatusEnum(Auftraege auftrag) {
        return SendStatusEnum.getSendStatus(auftrag.getLmtSendStatus());
    }

    public static void setSendetermin(Auftraege auftrag, SendTypeEnum sendTypeEnum, Date aktuellesDatum) {
        switch (sendTypeEnum) {
            case LMT:
                auftrag.setSendetermin(aktuellesDatum);
                break;
            case FHI:
                auftrag.setSendeterminFhi(aktuellesDatum);
                break;
            case RHM:
                auftrag.setSendeterminRhm(aktuellesDatum);
                break;
            case UBM:
                auftrag.setSendeterminUbm(aktuellesDatum);
                break;
            default:
                throw new RuntimeException("setSendetermin called for invalid sendtype: " + sendTypeEnum);
        }
    }

    public static void setZugebundenBool(Auftraege auftrag, boolean pZugebunden) {
        if (pZugebunden) {
            auftrag.setZugebunden(NUMERIC_TRUE_STRING);
        }
        else {
            auftrag.setZugebunden(NUMERIC_FALSE_STRING);
        }
    }

    public static void setLfdNr(Auftraege auftrag, SendTypeEnum pSendTypeEnum, Long pLfdNr) {
        switch (pSendTypeEnum) {
            case FHI:
                auftrag.setLfdNrFhi(pLfdNr);
                break;
            case LMT:
                auftrag.setLfdNrLmt(pLfdNr);
                break;
            case RHM:
                auftrag.setLfdNrRhm(pLfdNr);
                break;
            case UBM:
                auftrag.setLfdNrUbm(pLfdNr);
                break;
            case UNDEFINED:
                auftrag.setLfdNrGes(pLfdNr);
                break;
            case KOMPLETT:
                // fall through
            default:
                throw new RuntimeException("Lfd Nummer fuer "
                                           + pSendTypeEnum.name()
                                           + " ist am Auftrag nicht definiert.");
        }
    }

    public static String getCodesBand(Auftraege auftrag) {
        Hibernate.initialize(auftrag.getAlleRelCode());
        List<MvAlleRelCode> alleRelCode = auftrag.getAlleRelCode();
        if (alleRelCode.size() > 0) {
            return auftrag.getAlleRelCode().get(0).getCodesBand();
        }
        return null;
    }

}
