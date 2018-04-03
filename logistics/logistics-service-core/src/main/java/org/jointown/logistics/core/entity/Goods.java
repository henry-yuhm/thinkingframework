package org.jointown.logistics.core.entity;

import org.jointown.logistics.core.entity.dictionary.GoodsCategory;
import org.jointown.logistics.core.entity.support.SaleClassification;
import org.jointown.logistics.core.entity.support.SplitGranularity;
import org.jointown.logistics.core.entity.support.StorageSign;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(schema = "wms", uniqueConstraints = @UniqueConstraint(name = "uk_goods", columnNames = {"owner_id", "no"}))
public class Goods {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @Column(nullable = false, length = 50)
    private String no;//编号

    @Column(nullable = false)
    private String name;//名称

    private String universalName;//通用名

    private String mnemonicCode;//助记码

    @Column(nullable = false)
    private String specification;//规格

    @Column(nullable = false)
    private String manufacturer;//生产厂家

    @Column(nullable = false)
    private String producingArea;//产地

    @Column(nullable = false)
    private BigInteger wholePackageQuantity;//大包装数量

    @Column(nullable = false)
    private BigInteger mediumPackageQuantity;//中包装数量

    @Column(nullable = false)
    private BigInteger smallPackageQuantity;//小包装数量

    @Column(nullable = false)
    private String packageUnit;//包装单位

    @Column(nullable = false)
    private String approval;//批准文号

    private Date approvalValidUntil;//批准文号效期

    @Column(nullable = false)
    private SplitGranularity granularity;//拆分粒度

    @Column(nullable = false)
    private BigInteger invoiceUnit;//最小开票单位

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private GoodsCategory category;//类别

    private String wholePackageBarcode;//大包装条码

    private String mediumPackageBarcode;//中包装条码

    private String smallPackageBarcode;//小包装条码

    @Column(nullable = false)
    private SaleClassification wholeClassification;//整件分类

    @Column(nullable = false)
    private SaleClassification remainderClassification;//零货分类

    @Column(nullable = false)
    private BigDecimal length;//长

    @Column(nullable = false)
    private BigDecimal width;//宽

    @Column(nullable = false)
    private BigDecimal height;//高

    @Column(nullable = false)
    private BigDecimal wholePackageVolume;//大包装体积

    @Column(nullable = false)
    private BigDecimal mediumPackageVolume;//中包装体积

    @Column(nullable = false)
    private BigDecimal smallPackageVolume;//小包装体积

    @Column(nullable = false)
    private BigDecimal goodsWeight;//商品重量

    @Column(nullable = false)
    private BigDecimal packageWeight;//包装重量

    @Column(nullable = false)
    private StorageSign storageSign;//存储标识

    @Column(nullable = false)
    private String storageCondition;//存储条件

    @Column(nullable = false)
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

    public BigInteger getSmallPackageQuantity() {
        return smallPackageQuantity;
    }

    public void setSmallPackageQuantity(BigInteger smallPackageQuantity) {
        this.smallPackageQuantity = smallPackageQuantity;
    }

    public String getPackageUnit() {
        return packageUnit;
    }

    public void setPackageUnit(String packageUnit) {
        this.packageUnit = packageUnit;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public Date getApprovalValidUntil() {
        return approvalValidUntil;
    }

    public void setApprovalValidUntil(Date approvalValidUntil) {
        this.approvalValidUntil = approvalValidUntil;
    }

    public SplitGranularity getGranularity() {
        return granularity;
    }

    public void setGranularity(SplitGranularity granularity) {
        this.granularity = granularity;
    }

    public BigInteger getInvoiceUnit() {
        return invoiceUnit;
    }

    public void setInvoiceUnit(BigInteger invoiceUnit) {
        this.invoiceUnit = invoiceUnit;
    }

    public GoodsCategory getCategory() {
        return category;
    }

    public void setCategory(GoodsCategory category) {
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

    public String getSmallPackageBarcode() {
        return smallPackageBarcode;
    }

    public void setSmallPackageBarcode(String smallPackageBarcode) {
        this.smallPackageBarcode = smallPackageBarcode;
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

    public BigDecimal getSmallPackageVolume() {
        return smallPackageVolume;
    }

    public void setSmallPackageVolume(BigDecimal smallPackageVolume) {
        this.smallPackageVolume = smallPackageVolume;
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