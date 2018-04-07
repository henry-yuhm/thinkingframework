package org.thinking.logistics.core.entity;

import org.thinking.logistics.core.entity.support.BusinessType;
import org.thinking.logistics.core.entity.support.StagingareaCategory;
import org.thinking.logistics.core.entity.support.StagingareaType;
import org.thinking.logistics.core.entity.support.TakegoodsMode;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Stagingarea {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String number;//编号

    private String name;//名称

    @Column(nullable = false)
    private StagingareaType type = StagingareaType.NORMAL;//类型

    @Column(nullable = false)
    private StagingareaCategory category;//类别

    @Column(nullable = false)
    private boolean available = true;//是否可用

    @OneToMany
    @JoinTable(joinColumns = @JoinColumn(name = "stagingarea_id"), inverseJoinColumns = @JoinColumn(name = "owner_id"))
    private Set<Owner> owners = new LinkedHashSet<>();//业主

    private BusinessType businessType;//业务类型

    @ManyToOne(fetch = FetchType.LAZY)
    private Direction direction;//方向

    private TakegoodsMode takegoodsMode;//提货方式

    public Stagingarea() {
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StagingareaType getType() {
        return type;
    }

    public void setType(StagingareaType type) {
        this.type = type;
    }

    public StagingareaCategory getCategory() {
        return category;
    }

    public void setCategory(StagingareaCategory category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Set<Owner> getOwners() {
        return owners;
    }

    public void setOwners(Set<Owner> owners) {
        this.owners = owners;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public TakegoodsMode getTakegoodsMode() {
        return takegoodsMode;
    }

    public void setTakegoodsMode(TakegoodsMode takegoodsMode) {
        this.takegoodsMode = takegoodsMode;
    }
}