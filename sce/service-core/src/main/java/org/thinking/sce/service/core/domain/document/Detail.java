package org.thinking.sce.service.core.domain.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.sce.service.core.domain.BaseDomainEntity;
import org.thinking.sce.service.core.domain.common.Item;
import org.thinking.sce.service.core.domain.common.Lot;
import org.thinking.sce.service.core.domain.common.Warehouse;

import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class Detail extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Item item;//商品

    @ManyToOne(fetch = FetchType.LAZY)
    private Lot lot;//批号
}