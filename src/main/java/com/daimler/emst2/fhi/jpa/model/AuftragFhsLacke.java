package com.daimler.emst2.fhi.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@Table(name = "V_E2_FHS_Lacke")
@NamedQuery(name = "AuftragFhsLacke.findAll", query = "SELECT t FROM AuftragFhsLacke t")
public class AuftragFhsLacke implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "LACK_ID")
    private String id;


    @Column(name = "PNR")
    private String pnr;

    @Column(name = "LACKSCHL")
    private String lackschl;

    @Column(name = "LACKSCHLNR")
    private Long lackSchlNr;

    @Column(name = "LACK_LANGTEXT")
    private String lackLangText;

    @Column(name = "LACKZ_LANGTEXT")
    private String lackzLangText;

    @Column(name = "LACKZUS")
    private String lackzus;

}