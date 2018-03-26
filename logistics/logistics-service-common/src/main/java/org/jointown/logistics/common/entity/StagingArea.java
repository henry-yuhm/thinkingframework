package org.jointown.logistics.common.entity;

import org.jointown.logistics.common.entity.support.BusinessType;
import org.jointown.logistics.common.entity.support.StagingAreaCategory;
import org.jointown.logistics.common.entity.support.StagingAreaType;
import org.jointown.logistics.common.entity.support.TakeMode;

import javax.persistence.*;
import java.util.Set;

@Entity
public class StagingArea {
    @Id
    @TableGenerator(name = "StagingAreaId", table = "StagingAreaId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "StagingAreaId")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_sga_wh"))
    private Warehouse warehouse;//仓库

    private String no;//编号

    private String name;//名称

    private StagingAreaType type;//类型

    private StagingAreaCategory category;//类别

    private boolean available;//是否可用

    @ManyToMany
    @JoinTable(
            foreignKey = @ForeignKey(name = "fk_sga_own_sga"),
            inverseForeignKey = @ForeignKey(name = "fk_sga_own_own")
    )
    private Set<Owner> owners;//业主

    private BusinessType businessType;//业务类型

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_sga_direction"))
    private Direction direction;//方向

    private TakeMode takeMode;//提货方式

    public StagingArea() {
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

    public TakeMode getTakeMode() {
        return takeMode;
    }

    public void setTakeMode(TakeMode takeMode) {
        this.takeMode = takeMode;
    }
}