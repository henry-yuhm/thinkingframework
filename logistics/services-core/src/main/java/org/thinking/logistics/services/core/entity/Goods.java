package org.thinking.logistics.services.core.entity;

import lombok.Data;
import org.thinking.logistics.services.core.domain.support.SaleClassification;
import org.thinking.logistics.services.core.domain.support.SplittingGranularity;
import org.thinking.logistics.services.core.domain.support.StorageClassification;
import org.thinking.logistics.services.core.entity.dictionary.GoodsCategory;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(schema = "wms", uniqueConstraints = @UniqueConstraint(name = "uk_goods", columnNames = {"owner_id", "number"}))
@Data
public class Goods {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @Column(nullable = false, length = 50)
    private String number;//编号

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
    private int wholePackageQuantity;//大包装数量

    @Column(nullable = false)
    private int mediumPackageQuantity;//中包装数量

    @Column(nullable = false)
    private int smallPackageQuantity;//小包装数量

    @Column(nullable = false)
    private String packageUnit;//包装单位

    @Column(nullable = false)
    private String approval;//批准文号

    private Date approvalValidUntil;//批准文号效期

    @Column(nullable = false)
    private SplittingGranularity splittingGranularity;//拆分粒度

    @Column(nullable = false)
    private int invoiceUnit = 1;//最小开票单位

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
    private StorageClassification storageClassification;//存储分类

    @Column(nullable = false)
    private String storageCondition;//存储条件

    @Column(nullable = false)
    private String storageRequest;//存储要求
}