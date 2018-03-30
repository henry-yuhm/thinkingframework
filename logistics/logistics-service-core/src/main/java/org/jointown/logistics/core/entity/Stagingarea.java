package org.jointown.logistics.core.entity;

import org.jointown.logistics.core.entity.support.BusinessType;
import org.jointown.logistics.core.entity.support.StagingAreaCategory;
import org.jointown.logistics.core.entity.support.StagingAreaType;
import org.jointown.logistics.core.entity.support.TakegoodsMode;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Stagingarea {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String no;//编号

    private String name;//名称

    @Column(nullable = false)
    private StagingAreaType type;//类型

    @Column(nullable = false)
    private StagingAreaCategory category;//类别

    @Column(nullable = false)
    private boolean available;//是否可用

    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "stagingarea_id")}, inverseJoinColumns = {@JoinColumn(name = "owner_id")})
    private Set<Owner> owners;//业主

    private BusinessType businessType;//业务类型

    @OneToOne(fetch = FetchType.LAZY)
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

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StagingAreaType getType() {
        return type;
    }

    public void setType(StagingAreaType type) {
        this.type = type;
    }

    public StagingAreaCategory getCategory() {
        return category;
    }

    public void setCategory(StagingAreaCategory category) {
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