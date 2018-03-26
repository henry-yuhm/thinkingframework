package org.jointown.logistics.common.entity;

import org.jointown.logistics.common.entity.support.PackageSign;
import org.jointown.logistics.common.entity.support.PickingDeviceSign;
import org.jointown.logistics.common.entity.support.UpshelfMode;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
public class Area {
    @Id
    @TableGenerator(name = "AreaId", table = "AreaId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "AreaId")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_area_warehouse"))
    private Warehouse warehouse;//仓库

    private String no;//编号

    private String name;//名称

    private String storeCategory;//库别

    private String storeNo;//库房编号

    private PackageSign packageSign;//包装标识

    private String group;//作业分组

    private PickingDeviceSign pickingDeviceSign;//拣货设备标识

    private UpshelfMode upshelfMode;//上架方式

    private boolean prepicking;//是否提前拣货

    private boolean useSorter;//是否使用分拣机

    private String recheckSign;//复核标识

    @Column(nullable = false)
    private BigInteger fullloadToteboxNumber = BigInteger.ZERO;//满载周转箱数

    public Area() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getStoreCategory() {
        return storeCategory;
    }

    public void setStoreCategory(String storeCategory) {
        this.storeCategory = storeCategory;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public PackageSign getPackageSign() {
        return packageSign;
    }

    public void setPackageSign(PackageSign packageSign) {
        this.packageSign = packageSign;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public PickingDeviceSign getPickingDeviceSign() {
        return pickingDeviceSign;
    }

    public void setPickingDeviceSign(PickingDeviceSign pickingDeviceSign) {
        this.pickingDeviceSign = pickingDeviceSign;
    }

    public UpshelfMode getUpshelfMode() {
        return upshelfMode;
    }

    public void setUpshelfMode(UpshelfMode upshelfMode) {
        this.upshelfMode = upshelfMode;
    }

    public boolean isPrepicking() {
        return prepicking;
    }

    public void setPrepicking(boolean prepicking) {
        this.prepicking = prepicking;
    }

    public boolean isUseSorter() {
        return useSorter;
    }

    public void setUseSorter(boolean useSorter) {
        this.useSorter = useSorter;
    }

    public String getRecheckSign() {
        return recheckSign;
    }

    public void setRecheckSign(String recheckSign) {
        this.recheckSign = recheckSign;
    }

    public BigInteger getFullloadToteboxNumber() {
        return fullloadToteboxNumber;
    }

    public void setFullloadToteboxNumber(BigInteger fullloadToteboxNumber) {
        this.fullloadToteboxNumber = fullloadToteboxNumber;
    }

    @Override
    public String toString() {
        return "作业区域【" + this.no + "】";
    }
}