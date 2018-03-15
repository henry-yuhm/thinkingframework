package org.jointown.logistics.audit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class AuditType implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, nullable = false, updatable = false)
    private String name;

    private String description;

    public AuditType() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}