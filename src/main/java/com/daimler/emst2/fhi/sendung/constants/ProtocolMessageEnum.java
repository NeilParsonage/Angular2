package com.daimler.emst2.fhi.sendung.constants;


public enum ProtocolMessageEnum {

    AUFTRAG_OUTDATED("protocol.msg.auftrag.outdated"),

    AUFTRAG_SENDUNG_NICHT_OFFEN_KOMPLETT_ERR("error.sendepruef.auftrag.nichtoffen.komplett"),
    AUFTRAG_SENDUNG_NICHT_OFFEN_FHI_ERR("error.sendepruef.auftrag.nichtoffen.fhi"),
    AUFTRAG_SENDUNG_NICHT_OFFEN_RHM_ERR("error.sendepruef.auftrag.nichtoffen.rhm"),

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
    ORT_SENDUNG_ERR("error.sendepruef.auftrag.ortspruefung"),
    PREPARE_HISTORY_PROBLEM_DEBUG("protocol.msg.debug.preparehist.orterr"),
    SENDUNG_DONE_INFO("protocol.msg.info.sendung.done"),
    STORNO_DONE_INFO("protocol.msg.info.storno.done"),
    ANZAHL_FREIE_VERLETZT_WARN("warning.sendepruef.anzahlfreie"),

    AUFTRAG_SPERREN_VERLETZT_SINGULAR_LEER_WARN("warning.sendepruef.auftrag.sperren.keinbereich"), // dbmessage.senden.30027
    AUFTRAG_SPERREN_VERLETZT_SINGULAR_FHI_OR_RHM_WARN("warning.sendepruef.auftrag.sperren.fhioderrhm"), // dbmessage.senden.30030
    AUFTRAG_SPERREN_VERLETZT_SEVERAL_AREAS_WARN("warning.sendepruef.auftrag.sperren.fhiundrrhm"), // dbmessage.senden.30031

    AUFTRAG_SEQNR_OBERGRENZE_VERLETZT_ERR("error.sendepruef.auftrag.seqnrobergrenze"), // dbmessage.senden.20066

    AUFTRAG_VORSENDUNGEN_VERLETZT_ERR("error.sendepruef.auftrag.vorsendungen"), // dbmessage.senden.20068

    AUFTRAG_ERREICHBARKEIT_VERLETZT_WARN("warning.sendepruef.auftrag.erreichbarkeit"), // dbmessage.senden.20095
    WARTESCHLANGE_VERLETZT_ERR("error.sendepruef.auftrag.warteschlange"), // dbmessage.senden.30073
    UMLAUF_OBERGRENZE_NICHT_DEFINIERT_ERR("error.sendepruef.auftrag.umlaufgrenze.nicht.definiert"), // dbmessage.senden.20082
    UMLAUF_OBERGRENZE_UEBERSCHRITTEN_WARN("warning.sendepruef.auftrag.umlaufgrenze.ueberschritten"), // dbmessage.senden.30020
    AUFTRAG_GASSENSPERRE_VERLETZT_WARN("warning.sendepruef.auftrag.gassensperre"), // dbmessage.senden.30012
    AUFTRAG_GASSE_4_VERLETZT_WARN("warning.sendepruef.auftrag.anzahl.gasse4.ueberschritten"), // dbmessage.senden.30013
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
