package com.daimler.emst2.fhi.sendung.constants;


public enum ProtocolMessageEnum {

    AUFTRAG_OUTDATED("protocol.msg.auftrag.outdated"),
    AUFTRAG_SENDUNG_NICHT_OFFEN_ERR("protocol.msg.error.sendung.nichtoffen"),
    AUFTRAG_STORNO_NICHT_OFFEN_ERR("protocol.msg.error.cancelsend.nichtoffen"),
    MANUELLE_REIHENFOLGE_LOESCHEN_WARNING("protocol.msg.manuelle.reihenfolge.loeschen.warn"),
    PROCESSSTEP_INFO_OK("protocol.msg.processstep.info.ok"),
    PROCESS_INFO_OK("protocol.msg.process.info.ok"),
    RESTRIKTIONEN_OUTDATED("protocol.msg.restriktionen.outdated"),
    SOLLABSTAND_VERLETZT_WARN("protocol.msg.sollabstand.verletzt"),
    TAKT_TELEGRAM_INSERT("protocol.msg.takt.telegram.insert"),
    TECHNICAL_ERR("protocol.msg.error.technical"),
    // 2 parameter: sendType, band
    ERR_NO_SYSTEMWERT_LFD_NR("protocol.msg.error.no.systemwert.lfdnr"),
    TECHNICAL_ERR_WITH_PARAM("protocol.msg.error.technical.oneparam"),
    TEILCANCEL_IMPLICIT("protocol.msg.teilcancel.implizit"),
    TEILSEND_IMPLICIT("protocol.msg.teilsend.implizit"),
    WARN_STORNO_RUECKFRAGE("protocol.msg.warn.storno.rueckfrage"),
    AUFTRAG_ANKUENDIGUNG_VORHANDEN("protocol.msg.warn.send.auftrag.ankuendigung.vorhanden"),
    AUFTRAG_SPERRE_VORHANDEN("protocol.msg.error.send.aufrag.sperre.vorhanden"),
    ORT_STORNO_ERR("protocol.msg.ort.storno.forbidden"),
    ORT_STORNO_AUSFALLIMPULS("protocol.msg.warn.storno.ausfallimpuls"),
    ORT_STORNO_STORNOIMPULS("protocol.msg.warn.storno.stornoimpuls"),
    ORT_SENDUNG_ERR("protocol.msg.ort.send.forbidden"),
    PREPARE_HISTORY_PROBLEM_DEBUG("protocol.msg.debug.preparehist.orterr"),
    SENDUNG_DONE_INFO("protocol.msg.info.sendung.done"),
    STORNO_DONE_INFO("protocol.msg.info.storno.done"),
    ANZAHL_FREIE_VERLETZT_WARN("warning.sendepruef.anzahlfreie"),

    AUFTRAG_SPERREN_VERLETZT_SINGULAR_LEER_WARN("warning.sendepruef.auftragssperren.keinbereich"), // dbmessage.senden.30027
    AUFTRAG_SPERREN_VERLETZT_SINGULAR_FHI_OR_RHM_WARN("warning.sendepruef.auftragssperren.fhioderrhm"), // dbmessage.senden.30030
    AUFTRAG_SPERREN_VERLETZT_SEVERAL_AREAS_WARN("warning.sendepruef.auftragssperren.fhiundrrhm"), // dbmessage.senden.30031

    AUFTRAG_SEQNR_OBERGRENZE_VERLETZT_ERR("error.sendepruef.auftrag.seqnrobergrenze"), // dbmessage.senden.20066
    ;

    private String tuebKey;

    public static final String DEFAULT_TUEB_PREFIX = "protocol.msg.";

    private ProtocolMessageEnum(String pTuebKey) {
        tuebKey = pTuebKey;
    }

    public String getDefaultTuebKey() {
        // DEFAULT_TUEB_PREFIX +
        return tuebKey;
    }

    public static final ProtocolMessageEnum getEnum(String name) {
        return ProtocolMessageEnum.valueOf(name);
    }
}
