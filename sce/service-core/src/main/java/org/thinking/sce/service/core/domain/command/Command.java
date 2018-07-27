package org.thinking.sce.service.core.domain.command;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.sce.service.core.domain.BaseDomainEntity;
import org.thinking.sce.service.core.domain.common.Item;
import org.thinking.sce.service.core.domain.common.Warehouse;
import org.thinking.sce.service.core.domain.support.CommandCategory;
import org.thinking.sce.service.core.domain.support.CommandStatus;
import org.thinking.sce.service.core.domain.support.CommandType;
import org.thinking.sce.service.core.domain.support.PackageType;
import org.thinking.sce.service.core.domain.support.WorkMode;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class Command extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Item item;//商品

    @Column(nullable = false)
    private PackageType packageType;//包装类型

    @Column(nullable = false)
    private CommandType commandType;//指令类型

    @Column(nullable = false)
    private CommandCategory commandCategory;//指令类别

    @Column(nullable = false)
    private CommandStatus commandStatus;//指令状态

    @Column(insertable = false)
    private WorkMode workMode;//作业方式
}