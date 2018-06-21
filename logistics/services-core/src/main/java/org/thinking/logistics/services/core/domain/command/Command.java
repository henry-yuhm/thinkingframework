package org.thinking.logistics.services.core.domain.command;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;
import org.thinking.logistics.services.core.domain.core.Item;
import org.thinking.logistics.services.core.domain.core.Warehouse;
import org.thinking.logistics.services.core.domain.support.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

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
    private CommandStage stage = CommandStage.CREATED;//指令阶段

    private WorkMode workMode;//作业方式

    @Column(nullable = false)
    private Date creationTime = Date.valueOf(LocalDate.now());//创建时间

    @Column(nullable = false)
    private Date modificationTime = Date.valueOf(LocalDate.now());//修改时间
}