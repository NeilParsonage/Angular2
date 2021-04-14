package com.daimler.emst2.frw.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Version;

@MappedSuperclass
public abstract class BaseEntity {

	@Version
	@Column(name = "VERSION")
	private Long version;

    public abstract Long getId();

	public Long getVersion() {
		return this.version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	/**
	 * Sets createdAt before insert
	 */
	@PrePersist
	public void initVersion() {
		this.version = 0L;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		return Objects.equals(getId(), other.getId()) && Objects.equals(version, other.version);
	}

	@Override
	public String toString() {
		return "BaseEntity [id=" + getId() + ",version=" + version + "]";
	}
}
