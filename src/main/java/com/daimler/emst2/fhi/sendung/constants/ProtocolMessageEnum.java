package com.daimler.emst2.fhi.sendung.constants;


public enum ProtocolMessageEnum {

    AUFTRAG_OUTDATED("auftrag.outdated"),
    AUFTRAG_SENDUNG_NICHT_OFFEN_ERR("error.sendung.nichtoffen"),
    AUFTRAG_STORNO_NICHT_OFFEN_ERR("error.cancelsend.nichtoffen"),
    MANUELLE_REIHENFOLGE_LOESCHEN_WARNING("manuelle.reihenfolge.loeschen.warn"),
    PROCESSSTEP_INFO_OK("processstep.info.ok"),
    PROCESS_INFO_OK("process.info.ok"),
    RESTRIKTIONEN_OUTDATED("restriktionen.outdated"),
    SOLLABSTAND_VERLETZT_WARN("sollabstand.verletzt"),
    TAKT_TELEGRAM_INSERT("takt.telegram.insert"),
    TECHNICAL_ERR("error.technical"),
    // 2 parameter: sendType, band
    ERR_NO_SYSTEMWERT_LFD_NR("error.no.systemwert.lfdnr"),
    TECHNICAL_ERR_WITH_PARAM("error.technical.oneparam"),
    TEILCANCEL_IMPLICIT("teilcancel.implizit"),
    TEILSEND_IMPLICIT("teilsend.implizit"),
    WARN_STORNO_RUECKFRAGE("warn.storno.rueckfrage"),
    AUFTRAG_ANKUENDIGUNG_VORHANDEN ("warn.send.auftrag.ankuendigung.vorhanden"),
    AUFTRAG_SPERRE_VORHANDEN ("error.send.aufrag.sperre.vorhanden"),
    ORT_STORNO_ERR("ort.storno.forbidden"),
    ORT_STORNO_AUSFALLIMPULS ("warn.storno.ausfallimpuls"),
    ORT_STORNO_STORNOIMPULS ("warn.storno.stornoimpuls"),
    ORT_SENDUNG_ERR ("ort.send.forbidden"),
    PREPARE_HISTORY_PROBLEM_DEBUG("debug.preparehist.orterr"),
    SENDUNG_DONE_INFO("info.sendung.done"),
    STORNO_DONE_INFO("info.storno.done"),
    ANZAHL_FREIE_VERLETZT_WARN("warn.anzahlfreie.verletzt"),

    AUFTRAG_SPERREN_VERLETZT_SINGULAR_LEER_WARN("warn.anzahlSperrenFuerBereich.verletzt.single.leer"),
    AUFTRAG_SPERREN_VERLETZT_SINGULAR_FHI_OR_RHM_WARN("warn.anzahlSperrenFuerBereich.verletzt.single.fhi_or_rhm"),
    AUFTRAG_SPERREN_VERLETZT_SEVERAL_AREAS_WARN("warn.anzahlSperrenFuerBereich.verletzt.multiple.areas"),
    ;

    private String tuebKey;

    public static final String DEFAULT_TUEB_PREFIX = "protocol.msg.";

    private ProtocolMessageEnum(String pTuebKey) {
        tuebKey = pTuebKey;
    }

    public String getDefaultTuebKey() {
        return DEFAULT_TUEB_PREFIX + tuebKey;
    }
}
