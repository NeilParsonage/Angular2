package com.daimler.emst2.fhi.config;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.daimler.emst2.fhi.services.KonfigurationService;

@Component
public class AppStartupRunner implements ApplicationRunner {

    private final Logger log = Logger.getLogger(this.getClass().getName());

    @Autowired
    private KonfigurationService configService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String localeStr = configService.getLocale();
        if (StringUtils.isEmpty(localeStr)) {
            logDefaultLocaleWithPreMessage(Level.WARNING, "Using default locale set by environment");
            return;
        }
        Locale locale;
        try {
            locale = LocaleUtils.toLocale(localeStr.replace('-', '_'));
        } catch (IllegalArgumentException e) {
            log.severe(String.format("Locale parse error (=%s)", localeStr));
            return;
        }
        Locale.setDefault(locale);
        logDefaultLocaleWithPreMessage(Level.INFO, String.format("Using locale set by SYSTEMWERT LOCALE (=%s) => Locale", localeStr));
    }

    private void logDefaultLocaleWithPreMessage(Level level, String message) {
        Locale defaultLocale = Locale.getDefault();
        log.log(level, String.format("%s (language=%s, country=%s)", message, defaultLocale.getISO3Language(), defaultLocale.getISO3Country()));
    }

}
