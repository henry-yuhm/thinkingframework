package org.thinking.logistics.services.core.domain.dictionary;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Dictionary {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String no;//编号

    @Column(nullable = false)
    private String name;//名称
}