package com.daimler.emst2.fhi.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.daimler.emst2.fhi.constants.FhiSystemGruppeKeyEnum;
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

}
