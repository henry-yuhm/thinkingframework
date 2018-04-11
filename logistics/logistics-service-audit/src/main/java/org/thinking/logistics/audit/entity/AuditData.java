package org.thinking.logistics.audit.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

@Entity
@Data
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

    @ManyToOne
    private AuditKind kind;

    private HashMap<String, String> info;

    @Column(nullable = false, updatable = false)
    private Date auditTime;
}