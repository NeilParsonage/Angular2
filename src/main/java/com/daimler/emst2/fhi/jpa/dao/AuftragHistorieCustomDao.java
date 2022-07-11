package com.daimler.emst2.fhi.jpa.dao;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daimler.emst2.fhi.jpa.model.Auftrag;
import com.daimler.emst2.fhi.jpa.model.AuftragHistorie;
import com.daimler.emst2.fhi.util.DateTimeHelper;

@Component
public class AuftragHistorieCustomDao {
    @Autowired
    AuftragHistorieDao dao;

    public void saveAuftragHistorie(Auftrag auftrag, String melder, String meldeKennung, String meldungenText,
            Date sendeTermin) {
        saveAuftragHistorie(auftrag.getPnr(), melder, meldeKennung, meldungenText, sendeTermin);
    }

    public void saveAuftragHistorie(String pPnr, String melder, String meldeKennung, String meldungenText,
            Date sendeTermin) {
        meldungenText = StringUtils.substring(meldungenText, 0, AuftragHistorie.MAX_LEN_MELDUNG);

        AuftragHistorie auftragHistorie = new AuftragHistorie();

        Date zeitStempel = DateTimeHelper.getAktuellesDatum();
        auftragHistorie.setAufPnr(pPnr);
        auftragHistorie.setMelder(melder); //Sendung oder Storno
        auftragHistorie.setMeldkenn(meldeKennung); //SendTypeEnum
        auftragHistorie.setMeldung(meldungenText);
        auftragHistorie.setZeit(zeitStempel);
        auftragHistorie.setSendetermin(sendeTermin);

        this.dao.save(auftragHistorie);
    }
}
