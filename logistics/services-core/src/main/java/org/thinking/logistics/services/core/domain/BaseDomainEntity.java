package org.thinking.logistics.services.core.domain;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public abstract class BaseDomainEntity {
    @Id
    @TableGenerator(name = "idGenerator", table = "idGenerator", schema = "wms", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "idGenerator")
    private long id;
}