package org.thinking.logistics.services.core.entity.container;

import lombok.Data;
import org.thinking.logistics.services.core.entity.Warehouse;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Container {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String number;//编号

    @Column(nullable = false)
    private boolean available = false;//可用
}