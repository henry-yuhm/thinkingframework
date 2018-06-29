package org.thinking.logistics.services.core.domain;

import lombok.Data;
import org.thinking.logistics.services.core.domain.employee.Employee;

import javax.persistence.*;
import java.time.Instant;

@MappedSuperclass
@Data
public abstract class BaseDomainEntity {
    @Id
    @TableGenerator(name = "idGenerator", table = "idGenerator", schema = "wms", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "idGenerator")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Employee addBy;//添加者

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Instant addTime;//添加时间

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee editBy;//编辑者

    @Temporal(TemporalType.DATE)
    private Instant editTime;//编辑时间
}