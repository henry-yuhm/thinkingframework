package org.thinking.logistics.operation.audit.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class AuditType implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, nullable = false, updatable = false, length = 100)
    private String name;

    private String description;
}