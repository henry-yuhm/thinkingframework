package org.thinking.sce.statemachine.dispatcher.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
public class Monitor {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String machineId;

    @Column(nullable = false)
    private String state;
}