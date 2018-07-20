package org.thinking.sce.operation.auditor.domain;

import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
public class AuditType implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, nullable = false, updatable = false, length = 100)
    private String name;

    private String description;
}