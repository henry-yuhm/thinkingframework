package org.thinking.logistics.audit.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class AuditData implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, updatable = false)
    private String username;

    @Column(nullable = false, updatable = false)
    private String applicationName;

    @Column(nullable = false, updatable = false)
    private String moduleName;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_audit_data_audit_type"))
    private AuditType auditType;

    @Column(nullable = false, updatable = false)
    private Date auditTime;

    public AuditData() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public AuditType getAuditType() {
        return auditType;
    }

    public void setAuditType(AuditType auditType) {
        this.auditType = auditType;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }
}