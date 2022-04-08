package com.daimler.emst2.fhi.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TUEB")
public class Tueb {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "SYSTEM")
    private String system;

    @Column(name = "PROGNAME")
    private String progname;

    @Column(name = "TSPRACHE")
    private String tsprache;

    @Column(name = "TNAME")
    private String tname;

    @Column(name = "TLAE")
    private String tlae;

    @Column(name = "TTEXT")
    private String ttext;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getProgname() {
        return progname;
    }

    public void setProgname(String progname) {
        this.progname = progname;
    }

    public String getTsprache() {
        return tsprache;
    }

    public void setTsprache(String tsprache) {
        this.tsprache = tsprache;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTlae() {
        return tlae;
    }

    public void setTlae(String tlae) {
        this.tlae = tlae;
    }

    public String getTtext() {
        return ttext;
    }

    public void setTtext(String ttext) {
        this.ttext = ttext;
    }

}
