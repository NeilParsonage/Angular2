package com.daimler.emst2.fhi.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.daimler.emst2.fhi.jpa.dao.SystemwerteDao;
import com.daimler.emst2.fhi.jpa.model.Systemwerte;

@Service
public class KonfigurationService {

    @Autowired
    private SystemwerteDao systemwerteDao;

    private static final String SYSTEMWERT_LOCALE = "LOCALE";

    private static final String SYSTEMWERT_WERKSID = "WERKS_ID";
    
    private static final String SYSTEMWERT_UMGEBUNG = "SYSTEM.ENV";

    public String getLocale() {
        return getSystemwert(SYSTEMWERT_LOCALE);
    }

    public String getWerksId(boolean required) {
        return getSystemwert(SYSTEMWERT_WERKSID, required);
    }
    
    public String getUmgebung(boolean required) {
        return getSystemwert(SYSTEMWERT_UMGEBUNG, required);
    }

    private String getSystemwert(String name) {
        return getSystemwert(name, false);
    }

    private String getSystemwert(String name, boolean required) {
        Systemwerte result = systemwerteDao.findByWertName(name);
        if (ObjectUtils.isEmpty(result)) {
            if (required) {
                throw new RuntimeException(String.format("Systemwert %s nicht gefunden!", name));
            }
            return StringUtils.EMPTY;
        }
        String locale = result.getWertChar();
        if ((!(locale instanceof String)) || StringUtils.isBlank((locale))) {
            if (required) {
                throw new RuntimeException(String.format("Systemwert %s vorhanden, jedoch ohne Inhalt (wert_char)!", name));
            }
            return StringUtils.EMPTY;
        }
        return locale;
    }

}
