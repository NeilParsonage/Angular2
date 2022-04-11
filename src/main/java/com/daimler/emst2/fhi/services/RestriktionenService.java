package com.daimler.emst2.fhi.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.daimler.emst2.fhi.jpa.dao.AktiveRestriktionDao;
import com.daimler.emst2.fhi.jpa.model.AktiveRestriktion;
import com.daimler.emst2.fhi.sendung.model.MetaList;
import com.daimler.emst2.fhi.util.DateTimeHelper;

@Service
public class RestriktionenService {

    @Autowired
    AktiveRestriktionDao aktiveRestriktionDao;

    private static final int MAX_ANZAHL_AKTIVE_RESTRIKTIONEN_ALL = 5000;

    public MetaList<AktiveRestriktion> getAllAktiveRestriktionenList() {
        //        List<AktiveRestriktion> aktiveRestriktionen = getUseCaseSendemaskeService()
        //                .findAllAktiveRestriktionen();

        Pageable pageable = PageRequest.of(0, MAX_ANZAHL_AKTIVE_RESTRIKTIONEN_ALL);

        Page<AktiveRestriktion> result = aktiveRestriktionDao.findAll(pageable);
        if (result.getTotalElements() > MAX_ANZAHL_AKTIVE_RESTRIKTIONEN_ALL) {
            throw new RuntimeException(
                    "Grenze von mehr als 5000 aktiven Restriktionen überschritten".format("%d", null));
        }

        List<AktiveRestriktion> aktiveRestriktionen = result.getContent();

        Date maxUpdDate = null;
        for (AktiveRestriktion iAktiveRestriktion : aktiveRestriktionen) {
            Date letzteAktualisierung = iAktiveRestriktion
                    .getLetzteAktualisierung();
            maxUpdDate = DateTimeHelper.max(maxUpdDate,
                    letzteAktualisierung);
        }

        //getUseCaseContext().setAllAktiveRestriktionenList(
        //        aktiveRestriktionen, maxUpdDate);
        //return aktiveRestriktionen;
        MetaList<AktiveRestriktion> metaList = new MetaList<AktiveRestriktion>();
        metaList.daten = aktiveRestriktionen;
        metaList.updatedOn = maxUpdDate;
        return metaList;
    }

}
