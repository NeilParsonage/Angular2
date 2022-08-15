package com.daimler.emst2.fhi.sendung.constants;

import java.util.HashMap;
import java.util.Set;

public class SendungsTypeMap {

    /*
     * Converts the Constants deined in the Warteschlane Table for SEND_TYP to our standard 
     * SendTypeEnum Values
     */

    public static HashMap<SendTypeEnum, String> sendungsTypMapping;
    static {
        sendungsTypMapping = new HashMap<>();
        sendungsTypMapping.put(SendTypeEnum.KOMPLETT, "komplsend");
        sendungsTypMapping.put(SendTypeEnum.FHI, "teilsendfhi");
        sendungsTypMapping.put(SendTypeEnum.RHM, "teilsendrhm");
    }

    public static SendTypeEnum getSendTypeEnumForString(final String sendTypeAsString) {

        Set<SendTypeEnum> keySet = sendungsTypMapping.keySet();
        for (SendTypeEnum key : keySet) {
            if (sendungsTypMapping.get(key).equalsIgnoreCase(sendTypeAsString)) {
                return key;
            }
        }
        return null;
    }

}
