package org.jointown.logistics.core.entity;

import org.jointown.logistics.core.entity.support.PackageSign;
import org.jointown.logistics.core.entity.support.PickingDevice;
import org.jointown.logistics.core.entity.support.RecheckMode;
import org.jointown.logistics.core.entity.support.UpshelfMode;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
public class Area {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String no;//编号

    @Column(nullable = false)
    private String name;//名称

    @Column(nullable = false)
    private String storeCategory;//库别

    @Column(nullable = false)
    private String storeNo;//库房编号

    @Column(nullable = false)
    private PackageSign sign;//包装标识

    private String region;//大区

    @Column(nullable = false)
    private PickingDevice pickingDevice;//拣货设备

    private UpshelfMode upshelfMode;//上架方式

    @Column(nullable = false)
    private boolean prepicking;//是否提前拣货

    @Column(nullable = false)
    private boolean useSorter;//是否使用分拣机

    @Column(nullable = false)
    private RecheckMode recheckMode;//复核标识

    @Column(nullable = false)
    private BigInteger fullloadToteboxNumber;//满载周转箱数

    public Area() {
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

    public PackageSign getSign() {
        return sign;
    }

    public void setSign(PackageSign sign) {
        this.sign = sign;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public PickingDevice getPickingDevice() {
        return pickingDevice;
    }

    public void setPickingDevice(PickingDevice pickingDevice) {
        this.pickingDevice = pickingDevice;
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

    public RecheckMode getRecheckMode() {
        return recheckMode;
    }

    public void setRecheckMode(RecheckMode recheckMode) {
        this.recheckMode = recheckMode;
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