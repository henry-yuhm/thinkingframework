package org.jointown.logistics.common.entity;

import org.jointown.logistics.common.entity.support.SaleClassification;
import org.jointown.logistics.common.entity.support.SplitGranularity;
import org.jointown.logistics.common.entity.support.StorageSign;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Entity
public class Goods {
    @Id
    @TableGenerator(name = "GoodsId", table = "GoodsId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "GoodsId")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_goods_owner"))
    private Owner owner;//业主

    private String no;//编号

    private String name;//名称

    private String universalName;//通用名

    private String mnemonicCode;//助记码

    private String specification;//规格

    private String manufacturer;//生产厂家

    private String producingArea;//产地

    @Column(nullable = false)
    private BigInteger wholePackageQuantity;//大包装数量

    private BigInteger mediumPackageQuantity;//中包装数量

    private BigInteger tinyPackageQuantity;//小包装数量

    private String packageUnit;//包装单位

    private String approvalNo;//批准文号

    private Date approvalNoValidUntil;//批准文号效期

    private SplitGranularity splitGranularity;//拆分粒度

    private BigInteger minInvoiceUnit = BigInteger.ONE;//最小开票单位

    private String category;//类别

    private String wholePackageBarcode;//大包装条码

    private String mediumPackageBarcode;//中包装条码

    private String tinyPackageBarcode;//小包装条码

    private SaleClassification wholeClassification;//整件分类

    private SaleClassification remainderClassification;//零货分类

    private BigDecimal length;//长

    private BigDecimal width;//宽

    private BigDecimal height;//高

    private BigDecimal wholePackageVolume;//大包装体积

    private BigDecimal mediumPackageVolume;//中包装体积

    private BigDecimal tinyPackageVolume;//小包装体积

    private BigDecimal goodsWeight;//商品重量

    private BigDecimal packageWeight;//包装重量

    private StorageSign storageSign;//存储标识

    private String storageCondition;//存储条件

    private String storageRequest;//存储要求

    public Goods() {
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
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

    public String getUniversalName() {
        return universalName;
    }

    public void setUniversalName(String universalName) {
        this.universalName = universalName;
    }

    public String getMnemonicCode() {
        return mnemonicCode;
    }

    public void setMnemonicCode(String mnemonicCode) {
        this.mnemonicCode = mnemonicCode;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getProducingArea() {
        return producingArea;
    }

    public void setProducingArea(String producingArea) {
        this.producingArea = producingArea;
    }

    public BigInteger getWholePackageQuantity() {
        return wholePackageQuantity;
    }

    public void setWholePackageQuantity(BigInteger wholePackageQuantity) {
        this.wholePackageQuantity = wholePackageQuantity;
    }

    public BigInteger getMediumPackageQuantity() {
        return mediumPackageQuantity;
    }

    public void setMediumPackageQuantity(BigInteger mediumPackageQuantity) {
        this.mediumPackageQuantity = mediumPackageQuantity;
    }

    public BigInteger getTinyPackageQuantity() {
        return tinyPackageQuantity;
    }

    public void setTinyPackageQuantity(BigInteger tinyPackageQuantity) {
        this.tinyPackageQuantity = tinyPackageQuantity;
    }

    public String getPackageUnit() {
        return packageUnit;
    }

    public void setPackageUnit(String packageUnit) {
        this.packageUnit = packageUnit;
    }

    public String getApprovalNo() {
        return approvalNo;
    }

    public void setApprovalNo(String approvalNo) {
        this.approvalNo = approvalNo;
    }

    public Date getApprovalNoValidUntil() {
        return approvalNoValidUntil;
    }

    public void setApprovalNoValidUntil(Date approvalNoValidUntil) {
        this.approvalNoValidUntil = approvalNoValidUntil;
    }

    public SplitGranularity getSplitGranularity() {
        return splitGranularity;
    }

    public void setSplitGranularity(SplitGranularity splitGranularity) {
        this.splitGranularity = splitGranularity;
    }

    public BigInteger getMinInvoiceUnit() {
        return minInvoiceUnit;
    }

    public void setMinInvoiceUnit(BigInteger minInvoiceUnit) {
        this.minInvoiceUnit = minInvoiceUnit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWholePackageBarcode() {
        return wholePackageBarcode;
    }

    public void setWholePackageBarcode(String wholePackageBarcode) {
        this.wholePackageBarcode = wholePackageBarcode;
    }

    public String getMediumPackageBarcode() {
        return mediumPackageBarcode;
    }

    public void setMediumPackageBarcode(String mediumPackageBarcode) {
        this.mediumPackageBarcode = mediumPackageBarcode;
    }

    public String getTinyPackageBarcode() {
        return tinyPackageBarcode;
    }

    public void setTinyPackageBarcode(String tinyPackageBarcode) {
        this.tinyPackageBarcode = tinyPackageBarcode;
    }

    public SaleClassification getWholeClassification() {
        return wholeClassification;
    }

    public void setWholeClassification(SaleClassification wholeClassification) {
        this.wholeClassification = wholeClassification;
    }

    public SaleClassification getRemainderClassification() {
        return remainderClassification;
    }

    public void setRemainderClassification(SaleClassification remainderClassification) {
        this.remainderClassification = remainderClassification;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWholePackageVolume() {
        return wholePackageVolume;
    }

    public void setWholePackageVolume(BigDecimal wholePackageVolume) {
        this.wholePackageVolume = wholePackageVolume;
    }

    public BigDecimal getMediumPackageVolume() {
        return mediumPackageVolume;
    }

    public void setMediumPackageVolume(BigDecimal mediumPackageVolume) {
        this.mediumPackageVolume = mediumPackageVolume;
    }

    public BigDecimal getTinyPackageVolume() {
        return tinyPackageVolume;
    }

    public void setTinyPackageVolume(BigDecimal tinyPackageVolume) {
        this.tinyPackageVolume = tinyPackageVolume;
    }

    public BigDecimal getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(BigDecimal goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public BigDecimal getPackageWeight() {
        return packageWeight;
    }

    public void setPackageWeight(BigDecimal packageWeight) {
        this.packageWeight = packageWeight;
    }

    public StorageSign getStorageSign() {
        return storageSign;
    }

    public void setStorageSign(StorageSign storageSign) {
        this.storageSign = storageSign;
    }

    public String getStorageCondition() {
        return storageCondition;
    }

    public void setStorageCondition(String storageCondition) {
        this.storageCondition = storageCondition;
    }

    public String getStorageRequest() {
        return storageRequest;
    }

    public void setStorageRequest(String storageRequest) {
        this.storageRequest = storageRequest;
    }
}