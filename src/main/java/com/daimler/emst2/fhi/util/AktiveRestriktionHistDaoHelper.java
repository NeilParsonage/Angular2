package com.daimler.emst2.fhi.util;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import com.daimler.emst2.fhi.jpa.dao.AktiveRestriktionHistDao;
import com.daimler.emst2.fhi.jpa.model.AktiveRestriktion;
import com.daimler.emst2.fhi.jpa.model.AktiveRestriktionHist;
import com.daimler.emst2.fhi.jpa.model.Auftrag;
import com.daimler.emst2.fhi.model.BandAuswahl;

public class AktiveRestriktionHistDaoHelper {

    public static final AktiveRestriktionHistDaoHelper create() {
        return new AktiveRestriktionHistDaoHelper();
    }

    /**
     * @see com.dcx.server.fhi.dao.IHistRestriktionDao#saveAktiveRestriktionInHist(com.dcx.common.fhi.model.IAktiveRestriktion, java.lang.String)
     */
    public void saveAktiveRestriktionInHist(AktiveRestriktionHistDao aktiveRestriktionHistDao,
            AktiveRestriktion pAktiveRestriktion, String pPnr) {

        //HistRestriktion toSave = this.create();
        AktiveRestriktionHist toSave = createAktiveHistRestriktion();

        toSave.setRestId(pAktiveRestriktion.getRestId());
        toSave.setAbstand(pAktiveRestriktion.getAbstand()); // Abstand
        toSave.setAufPnr(pPnr);
        toSave.setBereich(pAktiveRestriktion.getBereich());
        toSave.setCode1(pAktiveRestriktion.getCode1());
        toSave.setCode2(pAktiveRestriktion.getCode2());
        toSave.setCode3(pAktiveRestriktion.getCode3());
        toSave.setHintergundFarbe(pAktiveRestriktion.getHintergundFarbe());
        toSave.setKnzCodeAnzeige(pAktiveRestriktion.getKnzCodeAnzeige()); // CodeAnzeige
        toSave.setKnzSternAnzeige(pAktiveRestriktion.getKnzSternAnzeige());
        toSave.setKnzSternMarkierung(pAktiveRestriktion.getKnzSternMarkierung());
        toSave.setKriterium(pAktiveRestriktion.getKriterium());
        toSave.setSollabsFenster(pAktiveRestriktion.getSollabsFenster());
        toSave.setSollabsIntervall(pAktiveRestriktion.getSollabsIntervall());
        toSave.setSternPos(pAktiveRestriktion.getSternPos());

        aktiveRestriktionHistDao.save(toSave);
    }

    public AktiveRestriktionHist createAktiveHistRestriktion() {
        AktiveRestriktionHist entity = new AktiveRestriktionHist();

        entity.setKnzSternMarkierung(BooleanUtils.toInteger(Boolean.FALSE));

        entity.setKnzSternAnzeige(BooleanUtils.toInteger(Boolean.FALSE));

        entity.setKnzCodeAnzeige(BooleanUtils.toInteger(Boolean.FALSE));

        return entity;
    }

    public int deleteOldRestriktionenForBereich(AktiveRestriktionHistDao aktiveRestriktionHistDao, Auftrag pAuftrag,
            BandAuswahl pBandauswahl) {
        String pnr = pAuftrag.getPnr();
        String bereichLikePattern = null;
        if (pBandauswahl.isBereichFhi()) {
            bereichLikePattern = pBandauswahl.getBereich();
        }
        else if (pBandauswahl.isLmtBandauswahl()) {
            bereichLikePattern = StringUtils.substring(pBandauswahl.getBereich(), 0, 1);
            bereichLikePattern += "%";
        }
        else {
            throw new RuntimeException("Unsupported Bandauswahl for delete history: " + pBandauswahl);
        }

        int numDeletes = aktiveRestriktionHistDao.deleteOldRestriktionenForBereich(pnr, bereichLikePattern);

        /* SQLQuery query = getSession().createSQLQuery(SQL_DELETE_OLD_HIST);
        query.setString(0, pnr);
        query.setString(1, bereichLikePattern);
        int numDeletes = query.executeUpdate(); */
        return numDeletes;
    }

}
