package org.thinking.logistics.statemachine.dispatcher.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
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