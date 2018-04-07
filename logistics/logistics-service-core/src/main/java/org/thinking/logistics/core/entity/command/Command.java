package org.thinking.logistics.core.entity.command;

import org.thinking.logistics.core.entity.Goods;
import org.thinking.logistics.core.entity.Warehouse;
import org.thinking.logistics.core.entity.support.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Command {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Goods goods;//商品

    @Column(nullable = false)
    private PackageSign sign;//包装标识

    @Column(nullable = false)
    private BusinessType type;//业务类型

    @Column(nullable = false)
    private TaskCategory category;//作业类别

    @Column(nullable = false)
    private TaskStage stage = TaskStage.CREATED;//作业阶段

    private TaskMode mode;//作业方式

    @Column(nullable = false)
    private Date creationTime = Date.valueOf(LocalDate.now());//创建时间

    @Column(nullable = false)
    private Date modificationTime = Date.valueOf(LocalDate.now());//修改时间

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public PackageSign getSign() {
        return sign;
    }

    public void setSign(PackageSign sign) {
        this.sign = sign;
    }

    public BusinessType getType() {
        return type;
    }

    public void setType(BusinessType type) {
        this.type = type;
    }

    public TaskCategory getCategory() {
        return category;
    }

    public void setCategory(TaskCategory category) {
        this.category = category;
    }

    public TaskStage getStage() {
        return stage;
    }

    public void setStage(TaskStage stage) {
        this.stage = stage;
    }

    public TaskMode getMode() {
        return mode;
    }

    public void setMode(TaskMode mode) {
        this.mode = mode;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }
}