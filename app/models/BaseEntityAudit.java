package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 * Base Entity Audit
 *
 * @author cem ikta
 */
@MappedSuperclass
public abstract class BaseEntityAudit extends BaseEntity {

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    public Date createdAt;

    @Size(max = 20)
    @Column(name = "created_by", length = 20)
    public String createdBy;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    public Date updatedAt;

    @Size(max = 20)
    @Column(name = "updated_by", length = 20)
    public String updatedBy;

    /**
     * Sets createdAt before insert
     */
    @PrePersist
    public void setCreationDate() {
        this.createdAt = new Date();
    }

    /**
     * Sets updatedAt before update
     */
    @PreUpdate
    public void setChangeDate() {
        this.updatedAt = new Date();
    }

}