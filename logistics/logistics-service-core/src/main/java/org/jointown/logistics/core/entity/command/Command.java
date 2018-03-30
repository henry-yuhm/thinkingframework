package org.jointown.logistics.core.entity.command;

import org.jointown.logistics.core.entity.Warehouse;
import org.jointown.logistics.core.entity.support.*;

import javax.persistence.*;
import java.sql.Time;

@MappedSuperclass
public abstract class Command {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private PackageSign sign;//包装标识

    @Column(nullable = false)
    private BusinessType type;//业务类型

    @Column(nullable = false)
    private TaskCategory category;//作业类别

    @Column(nullable = false)
    private TaskStage stage;//任务阶段

    private TaskMode mode;//作业方式

    @Column(nullable = false)
    private Time creationTime;//创建时间

    @Column(nullable = false)
    private Time modificationTime;//修改时间

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
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

    public Time getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Time creationTime) {
        this.creationTime = creationTime;
    }

    public Time getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Time modificationTime) {
        this.modificationTime = modificationTime;
    }
}