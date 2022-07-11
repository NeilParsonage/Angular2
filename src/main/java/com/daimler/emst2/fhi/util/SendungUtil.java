package com.daimler.emst2.fhi.util;

import com.daimler.emst2.fhi.constants.BooleanEnum;
import com.daimler.emst2.fhi.jpa.model.Auftrag;
import com.daimler.emst2.fhi.sendung.constants.SendStatusEnum;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;

public class SendungUtil {

    public static boolean isSendbar(Auftrag auftrag) {
        boolean isOffen = SendungUtil.isSendungOffen(auftrag, SendTypeEnum.FHI);
        isOffen |= SendungUtil.isSendungOffen(auftrag, SendTypeEnum.LMT);
        isOffen |= SendungUtil.isSendungOffen(auftrag, SendTypeEnum.UBM);
        isOffen |= SendungUtil.isSendungOffen(auftrag, SendTypeEnum.RHM);
        return isOffen;
    }

    public static boolean isSendungOffen(Auftrag auftrag, SendTypeEnum pSendTypeEnum) {
        BooleanEnum soll = getSendStatusSoll(auftrag, pSendTypeEnum);
        SendStatusEnum ist = SendungUtil.getSendStatusIst(auftrag, pSendTypeEnum);

        boolean check = soll.booleanValue();
        check &= !ist.isGesendet();
        return check;
    }

    private static BooleanEnum getSendStatusSoll(Auftrag auftrag, SendTypeEnum pSendTypeEnum) {
        String sendekennung = null;
        switch (pSendTypeEnum) {
            case LMT:
                sendekennung = auftrag.getLmtSendung();
                break;
            case FHI:
                sendekennung = auftrag.getFhiSendung();
                break;
            case RHM:
                sendekennung = auftrag.getRhmSendung();
                break;
            case UBM:
                sendekennung = auftrag.getUbmSendung();
                break;
            default:
                throw new RuntimeException("getSendStatusSoll called with invalid parameter: " + pSendTypeEnum);
        }
        BooleanEnum kennung = BooleanEnum.createFromString(sendekennung);
        return kennung;
    }

    public static SendStatusEnum getSendStatusIst(Auftrag auftrag, SendTypeEnum pSendTypeEnum) {
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


}
