package org.thinking.sce.service.core.domain.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.sce.service.core.domain.BaseDomainEntity;
import org.thinking.sce.service.core.domain.dictionary.ItemCategory;
import org.thinking.sce.service.core.domain.support.SaleClassification;
import org.thinking.sce.service.core.domain.support.SplittingGranularity;
import org.thinking.sce.service.core.domain.support.StorageClassification;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Optional;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_item", columnNames = {"owner_id", "no"}))
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class Item extends BaseDomainEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;//业主

    @Column(nullable = false, updatable = false, length = 50)
    private String no;//编号

    @Column(nullable = false, updatable = false, length = 200)
    private String name;//名称

    @Column(length = 200)
    private String universalName;//通用名

    @Column(length = 200)
    private String mnemonicCode;//助记码

    @Column(nullable = false, length = 200)
    private String specification;//规格

    @Column(nullable = false, length = 200)
    private String manufacturer;//生产厂家

    @Column(nullable = false, length = 200)
    private String producingArea;//产地

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal largePackageQuantity;//大包装数量

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal mediumPackageQuantity;//中包装数量

    @Column(nullable = false, precision = 22, scale = 5)
    private BigDecimal smallPackageQuantity;//小包装数量

    @Column(nullable = false, length = 10)
    private String packageUnit;//包装单位

    @Column(nullable = false, length = 100)
    private String approval;//批准文号

    private Instant approvalValidUntil;//批准文号效期

    @Column(nullable = false)
    private SplittingGranularity splittingGranularity;//拆分粒度

    @Column(nullable = false)
    private int invoiceUnit = 1;//最小开票单位

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ItemCategory category;//类别

    @Column(length = 13)
    private String largePackageBarcode;//大包装条码

    @Column(length = 13)
    private String mediumPackageBarcode;//中包装条码

    @Column(length = 13)
    private String smallPackageBarcode;//小包装条码

    @Column(nullable = false)
    private SaleClassification casesClassification;//整件分类

    @Column(nullable = false)
    private SaleClassification remainderClassification;//零货分类

    @Column(nullable = false, precision = 22, scale = 2)
    private BigDecimal length;//长

    @Column(nullable = false, precision = 22, scale = 2)
    private BigDecimal width;//宽

    @Column(nullable = false, precision = 22, scale = 2)
    private BigDecimal height;//高

    @Column(nullable = false, precision = 22, scale = 2)
    private BigDecimal largePackageVolume;//大包装体积

    @Column(nullable = false, precision = 22, scale = 2)
    private BigDecimal mediumPackageVolume;//中包装体积

    @Column(nullable = false, precision = 22, scale = 2)
    private BigDecimal smallPackageVolume;//小包装体积

    @Column(nullable = false, precision = 22, scale = 2)
    private BigDecimal itemWeight;//商品重量

    @Column(nullable = false, precision = 22, scale = 2)
    private BigDecimal packageWeight;//包装重量

    @Column(nullable = false)
    private StorageClassification storageClassification;//存储分类

    @Column(nullable = false)
    private String storageCondition;//存储条件

    @Column(nullable = false)
    private String storageRequest;//存储要求

    @Column(precision = 22, scale = 5)
    private BigDecimal tcmOutboundQuantity;//中药大件数量

    @Column(nullable = false, precision = 22, scale = 2)
    private BigDecimal volumeRatio;//体积系数

    public final BigDecimal getCases(BigDecimal quantity) {
        if (Optional.of(this.largePackageQuantity).orElse(BigDecimal.ZERO) == BigDecimal.ZERO) {
            return BigDecimal.ZERO;
        } else {
            if (this.storageClassification == StorageClassification.REMAINDER_ONLY) {
                return BigDecimal.ZERO;
            } else {
                return new BigDecimal(quantity.signum()).multiply(quantity.abs().divide(this.largePackageQuantity, RoundingMode.FLOOR));
            }
        }
    }

    public final BigDecimal getRemainder(BigDecimal quantity) {
        if (Optional.of(this.largePackageQuantity).orElse(BigDecimal.ZERO) == BigDecimal.ZERO) {
            return BigDecimal.ZERO;
        } else {
            if (this.storageClassification == StorageClassification.REMAINDER_ONLY) {
                return new BigDecimal(quantity.signum()).multiply(quantity.abs());
            } else {
                return new BigDecimal(quantity.signum()).multiply(quantity.abs().remainder(this.largePackageQuantity));
            }
        }
    }
}