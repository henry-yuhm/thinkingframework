package org.thinking.logistics.services.core.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Transferline {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String no;//编号

    @Column(nullable = false)
    private String name;//名称
}