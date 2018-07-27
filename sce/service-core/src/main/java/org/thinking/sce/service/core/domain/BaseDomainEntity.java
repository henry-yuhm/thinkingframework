package org.thinking.sce.service.core.domain;

import lombok.Data;
import org.thinking.sce.service.core.domain.employee.Employee;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;
import java.time.Instant;

@MappedSuperclass
@Data
public abstract class BaseDomainEntity {
    @Id
    @TableGenerator(name = "idGenerator", table = "idGenerator", schema = "wms", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "idGenerator")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee addBy;//添加者

    private Instant addTime;//添加时间

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee editBy;//编辑者

    private Instant editTime;//编辑时间
}