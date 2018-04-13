package org.thinking.logistics.services.core.entity.command;

import lombok.Data;
import org.thinking.logistics.services.core.domain.support.*;
import org.thinking.logistics.services.core.entity.Goods;
import org.thinking.logistics.services.core.entity.Warehouse;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Command {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Goods goods;//商品

    @Column(nullable = false)
    private PackageKind packageKind;//包装类型

    @Column(nullable = false)
    private CommandKind commandKind;//指令类型

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