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
}