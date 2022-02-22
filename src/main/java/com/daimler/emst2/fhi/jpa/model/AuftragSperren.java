package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@Table(name = "V_AUF_SPERRE")
@NamedQuery(name = "AuftragSperren.findAll", query = "SELECT s FROM AuftragSperren s")
public class AuftragSperren implements Serializable {
    private static final long serialVersionUID = 1L;

    /*Aktuell werden nicht alle Spalten eingelesen, nur die wichtigsten   */

    @Id
    @Column(name = "Vasp_Id")
    private String vaspId;

    @Column(name = "PNR")
    private String pnr;

    @Column(name = "Sperrgrund")
    private String sperrgrund;

    /* Freigabetermin*/

    @Column(name = "Sperrtyp")
    private String sperrtyp;

    @Column(name = "Sperrart")
    private String sperrart;

    @Column(name = "Sperrcode")
    private String sperrcode;

    @Column(name = "Sperrcodetext")
    private String sperrcodetext;

    @Column(name = "Begruendung")
    private String begruendung;

}
