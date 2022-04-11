package com.daimler.emst2.fhi.services;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daimler.emst2.fhi.collections.HashMapDefault;
import com.daimler.emst2.fhi.jpa.dao.TuebDao;
import com.daimler.emst2.fhi.jpa.model.Tueb;
import com.daimler.emst2.fhi.model.IMessageKey;

@Service
public class UebersetzungService {

    private static final Logger LOG = Logger.getLogger(UebersetzungService.class.getName());

    private static final String WARN_MSG_INVALID_PARAMETERS =
            "Invalid number of parameters for text key [{0}] : expected ({1}) / found ({2})";

    private static final int PAGESIZE_TO_LOAD_ALL_ENTRIES = 10000;

    private static final String[] EMPTY_PARAMS = {};

    private static final String LANGUAGE_DEUTSCH = "DEUT";

    private static final String LANGUAGE_ENGLISH = "ENGL";

    private static final String LANGUAGE_TURKISH = "TURK";

    private static final String SYSTEM_FHI = "FHI";

    @Autowired
    TuebDao tuebDao;

    public boolean isValidSprache(String pSprache) {
        return (LANGUAGE_DEUTSCH.equals(pSprache)
                || LANGUAGE_ENGLISH.equals(pSprache)
                || LANGUAGE_TURKISH
                        .equals(pSprache));
    }

    public String getDefaultSprache() {
        return LANGUAGE_DEUTSCH;
    }

    public String[] getUnterstuetzteSprachen() {
        return new String[] { LANGUAGE_DEUTSCH, LANGUAGE_ENGLISH, LANGUAGE_TURKISH };
    }

    /**
     * Enthält für alle unterstützten Sprachen als Schlüssel eine
     * {@see HashMapDefault} mit den vorhandenen Übersetzungen.
     */
    private final Map<String, Map<String, String>> texteAlleSprachenMap = new HashMap<String, Map<String, String>>();

    public UebersetzungService() {
        super();
    }

    public void reinitUebersetzungen() {
        texteAlleSprachenMap.clear();
    }

    /**
     * @see IUebersetzungService#getText(String, String, String)
     */
    public String getText(String key, String defaultText, String sprache) {
        String technicalKey = HashMapDefault.erstelleKeyMitDefaultWert(key, defaultText);
        return getText(technicalKey, sprache);
    }

    /**
     * @see IUebersetzungService#getText(String, String)
     */
    public String getText(String key, String sprache) {
        return getSprachMap(sprache).get(key);
    }

    /**
     * @see IUebersetzungService#getText(String, String, Object[], String)
     */
    public String getText(String key, String defaultText, Object[] oa, String sprache) {
        String technicalKey = HashMapDefault.erstelleKeyMitDefaultWert(key, defaultText);
        return getText(technicalKey, oa, sprache);
    }

    public String getText(IMessageKey messageKey, String sprache) {
        return getText(messageKey, EMPTY_PARAMS, sprache);
    }

    public String getText(IMessageKey messageKey, String[] params, String sprache) {
        int numParams = params == null ? 0 : params.length;
        if ((messageKey.getNumParameters() != numParams)) {
            LOG.warning(MessageFormat.format(
                    WARN_MSG_INVALID_PARAMETERS,
                    new Object[] { messageKey.getKey(), messageKey.getNumParameters(), numParams }));
        }

        return getText(messageKey.getKey(), params, sprache);
    }

    /**
     * @see IUebersetzungService#getText(String, Object[], String)
     */
    public String getText(String key, Object[] oa, String sprache) {
        String msg = getSprachMap(sprache).get(key);
        MessageFormat form = new MessageFormat(msg);
        msg = form.format(oa);

        return msg;
    }

    /**
     * @see IUebersetzungService#getTexte(String)
     */
    public Map<String, String> getTexte(String sprache) {
        return getSprachMap(sprache);
    }

    /**
     * @see IUebersetzungService#getProgrammtext(String, String, String, String)
     */
    public String getProgrammtext(String pProgramm, String pTname, String defaultText, String sprache) {
        String key = getKey(pProgramm, pTname);
        return getText(key, defaultText, sprache);
    }

    /**
     * @see IUebersetzungService#initMap(String)
     */
    protected Map<String, String> initMap(String language) {
        Map<String, String> uebersetzungsMap = new HashMapDefault<String>();
//        IUebersetzungFilter filter = (IUebersetzungFilter)createFilter();
//        filter.setTsprache(language);
//        // filter.setSystem(systemname);
//        // damit werden max. 10000 Uebersetzungstexte geladen - das sollte
//        // reichen...
//        ((ISearchBaseServerFacade)filter).setPagesize(PAGESIZE_TO_LOAD_ALL_ENTRIES);
//
//        ISearchResult<IUebersetzung> res = getUebersetzungDao().findByFilter(filter);
        List<Tueb> tuebList = this.tuebDao.findTuebsBySystemAndSprache(SYSTEM_FHI, LANGUAGE_DEUTSCH);

        //   List<IUebersetzung> list = res.getEntities();
        tuebList.forEach(ueb -> {
            String key = getKey(ueb.getProgname(), ueb.getTname());
            uebersetzungsMap.put(key, ueb.getTtext());
        });
        //        for (int i = 0; i < list.size(); i++) {
        //            Tueb ueb = list.get(i);
        //            String key = getKey(ueb.getProgname(), ueb.getTname());
        //            uebersetzungsMap.put(key, ueb.getTtext());
        //        }

        if (!uebersetzungsMap.containsKey(null)) {
            // Einfügen von null als gültigen Key.
            uebersetzungsMap.put(null, uebersetzungsMap.get("<key is null>"));
        }

        this.texteAlleSprachenMap.put(language, uebersetzungsMap);

        return uebersetzungsMap;
    }

    protected String getKey(String programmName, String tName) {
        return programmName.toUpperCase() + "." + tName.toUpperCase();
    }

    //    public ISearchResult<Uebersetzung> findByFilter(IUebersetzungFilter filterObj) {
    //        return getUebersetzungDao().findByFilter(filterObj);
    //    }

    private Map<String, String> getSprachMap(String sprache) {
        Map<String, String> sprachMap = this.texteAlleSprachenMap.get(sprache);
        if (sprachMap == null) {
            sprachMap = initMap(sprache);
        }
        return sprachMap;
    }

    public Map getTooltip(String pSprache) {
        return new HashMap();
    }
}
