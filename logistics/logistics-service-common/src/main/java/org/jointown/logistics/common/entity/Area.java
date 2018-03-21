package org.jointown.logistics.common.entity;

import javax.persistence.*;

@Entity
public class Area {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_area_warehouse"))
    private Warehouse warehouse;

    private String no;

    private String name;

    private String storeCategory;

    private String storeNo;

    private String packageSign;

    private String operationGroup;

    private String pickingDeviceSign;

    private String upshelfMode;

    private boolean prepicking;

    private boolean useSorter;

    private String innerRecheckSign;

    private int fullloadToteBoxNumber;

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

    public String getPackageSign() {
        return packageSign;
    }

    public void setPackageSign(String packageSign) {
        this.packageSign = packageSign;
    }

    public String getOperationGroup() {
        return operationGroup;
    }

    public void setOperationGroup(String operationGroup) {
        this.operationGroup = operationGroup;
    }

    public String getPickingDeviceSign() {
        return pickingDeviceSign;
    }

    public void setPickingDeviceSign(String pickingDeviceSign) {
        this.pickingDeviceSign = pickingDeviceSign;
    }

    public String getUpshelfMode() {
        return upshelfMode;
    }

    public void setUpshelfMode(String upshelfMode) {
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

    public String getInnerRecheckSign() {
        return innerRecheckSign;
    }

    public void setInnerRecheckSign(String innerRecheckSign) {
        this.innerRecheckSign = innerRecheckSign;
    }

    public int getFullloadToteBoxNumber() {
        return fullloadToteBoxNumber;
    }

    public void setFullloadToteBoxNumber(int fullloadToteBoxNumber) {
        this.fullloadToteBoxNumber = fullloadToteBoxNumber;
    }

    @Override
    public String toString() {
        return "作业区域【" + this.no + "】";
    }
}