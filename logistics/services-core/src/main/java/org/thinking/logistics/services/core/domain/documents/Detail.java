package org.thinking.logistics.services.core.domain.documents;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;
import org.thinking.logistics.services.core.domain.core.Batch;
import org.thinking.logistics.services.core.domain.core.Goods;
import org.thinking.logistics.services.core.domain.core.Warehouse;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class Detail extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Goods goods;//商品

    @ManyToOne(fetch = FetchType.LAZY)
    private Batch batch;//批号
}