package org.thinking.logistics.workflow.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Monitor implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String machineId;

    @Lob
    private String stateContext;
}