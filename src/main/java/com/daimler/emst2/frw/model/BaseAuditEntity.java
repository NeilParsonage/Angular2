package com.daimler.emst2.frw.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@MappedSuperclass
public abstract class BaseAuditEntity extends BaseEntity {

    public static final String ANONYMOUS = "FHIUI";

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "INS_DATE", updatable = false)
    private Date insDate;

    @Column(name = "INS_USER", updatable = false)
    private String insUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPD_DATE")
    private Date updDate;

    @Column(name = "UPD_USER")
    private String updUser;

    /**
     * Used to access current username - has to be mocked in unit tests
     */
    protected String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return ANONYMOUS;
        }
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return currentUserName == null ? ANONYMOUS : currentUserName.toUpperCase();
        }
        return ANONYMOUS;
    }

    /**
     * Sets createdAt before insert
     */
    @PrePersist
    public void setInsDate() {
        super.initVersion();
        this.insDate = new Date();
        this.setInsUser(getCurrentUsername());
        setUpdDate();
    }

    /**
     * Sets updatedAt before update
     */
    @PreUpdate
    public void setUpdDate() {
        this.updDate = new Date();
        this.setUpdUser(getCurrentUsername());
    }

    public Date getInsDate() {
        return this.insDate;
    }

    public void setInsDate(Date insDate) {
        this.insDate = insDate;
    }

    public String getInsUser() {
        return this.insUser;
    }

    public void setInsUser(String insUser) {
        this.insUser = insUser;
    }

    public Date getUpdDate() {
        return this.updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }

    public String getUpdUser() {
        return this.updUser;
    }

    public void setUpdUser(String updUser) {
        this.updUser = updUser;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(insDate, insUser, updDate, updUser);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        // BaseAuditEntity other = (BaseAuditEntity)obj;
        // ignore audit fields in equals
        return true;
    }

    @Override
    public String toString() {
        return "BaseAuditEntity [" + super.toString() + "insDate=" + insDate + ", insUser=" + insUser + ", updDate=" + updDate + ", updUser=" + updUser + "]";
    }
}
