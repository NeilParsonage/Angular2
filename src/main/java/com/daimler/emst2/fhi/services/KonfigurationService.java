package com.daimler.emst2.fhi.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import com.daimler.emst2.fhi.constants.FhiSystemGruppeKeyEnum;
import com.daimler.emst2.fhi.constants.FhiSystemwertKeyEnum;
import com.daimler.emst2.fhi.jpa.dao.SystemgruppenDao;
import com.daimler.emst2.fhi.jpa.dao.SystemwertDao;
import com.daimler.emst2.fhi.jpa.model.Systemgruppen;
import com.daimler.emst2.fhi.jpa.model.Systemgruppenzuo;
import com.daimler.emst2.fhi.jpa.model.Systemwert;

@Service
public class KonfigurationService {

    @Autowired
    private SystemwertDao systemwertDao;

    @Autowired
    private SystemgruppenDao systemgruppenDao;

    private static final String SYSTEMWERT_LOCALE = "LOCALE";

    private static final String SYSTEMWERT_WERKSID = "FHI_WERK";
    
    private static final String SYSTEMWERT_UMGEBUNG = "SYSTEM.ENV";

    public static final String NOT_FOUND_STRING = "<not found>";

    public static final Long NOT_FOUND_LONG = Long.MIN_VALUE;

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
        Systemwert result = systemwertDao.findByWertName(name);
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

    public Map<String, Systemwert> getKonfigurationGruppeWithReload(FhiSystemGruppeKeyEnum auftragLfdNummern) {
        String systemgruppeName = auftragLfdNummern.getKey();
        //final ISystemgruppe systemgruppe = getSystemgruppeByName(systemgruppeName);

        Systemgruppen systemgruppe = this.systemgruppenDao.findByGruppeName(systemgruppeName);

        final HashMap<String, Systemwert> result = new HashMap<String, Systemwert>();
        for (Systemgruppenzuo membership : systemgruppe.getSystemgruppenzuos()) {
            Systemwert iSystemwert = membership.getSystemwert();
            final String key = iSystemwert.getWertName();
            result.put(key, iSystemwert);

            // und aktualisiere die gecachten Systemwerte - falls bereits initialisiert
            // addSystemwert(iSystemwert);
        }

        return result;
    }

    public Long getKonfigurationAsLong(FhiSystemwertKeyEnum systemWert) {
        return getKonfigurationAsLong(systemWert, NOT_FOUND_LONG);
    }

    public Long getKonfigurationAsLong(FhiSystemwertKeyEnum systemWert, Long defaultValue) {

        Class<?> classString = systemWert.getValueType();
        Assert.isAssignable(Long.class, classString);

        Systemwert iSystemwert = systemwertDao.findByWertName(systemWert.getKey());

        Long systemWertAsLong = defaultValue;
        if (null != iSystemwert && null != iSystemwert.getWertNum()) {
            systemWertAsLong = iSystemwert.getWertNum().longValue();
        }
        return systemWertAsLong;
    }

    public String getKonfigurationAsString(FhiSystemwertKeyEnum systemWert) {
        return getKonfigurationAsString(systemWert, NOT_FOUND_STRING);
    }

    public String getKonfigurationAsString(FhiSystemwertKeyEnum systemWert, String defaultValue) {

        Class<?> classString = systemWert.getValueType();
        Assert.isAssignable(String.class, classString);

        Systemwert iSystemwert = systemwertDao.findByWertName(systemWert.getKey());

        String systemWertAsString = defaultValue;
        if (null != iSystemwert) {
            String systemWertString = iSystemwert.getWertChar();
            if (null != systemWertString) {
                return systemWertString;
            }
        }
        return systemWertAsString;
    }

}
