package org.thinking.logistics.stagingarea.allocation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.BusinessBase;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.support.*;
import org.thinking.logistics.services.core.entity.Direction;
import org.thinking.logistics.services.core.entity.bill.OutboundDetail;
import org.thinking.logistics.services.core.entity.bill.OutboundHeader;
import org.thinking.logistics.services.core.entity.stagingarea.PhysicalConfiguration;
import org.thinking.logistics.services.core.entity.stagingarea.Stagingarea;
import org.thinking.logistics.services.core.entity.stagingarea.StagingareaConfiguration;
import org.thinking.logistics.services.core.entity.stagingarea.VirtualConfiguration;
import org.thinking.logistics.services.core.entity.stagingarea.dsl.QPhysicalConfiguration;
import org.thinking.logistics.services.core.entity.stagingarea.dsl.QStagingareaConfiguration;
import org.thinking.logistics.services.core.entity.stagingarea.dsl.QVirtualConfiguration;
import org.thinking.logistics.services.core.repository.bill.OutboundHeaderRepository;
import org.thinking.logistics.services.core.repository.stagingarea.StagingareaRepository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractAllocator extends BusinessBase implements Allocator {
    protected OutboundHeader header;

    protected StagingareaConfiguration configuration;

    protected Stagingarea stagingarea = new Stagingarea();

    protected List<Stagingarea> stagingareas = new LinkedList<>();

    protected boolean trial;

    protected int quantity = 0;

    @Resource
    private OutboundHeaderRepository headerRepository;

    @Resource
    private StagingareaRepository stagingareaRepository;

    AbstractAllocator(OutboundHeader header) {
        this(header, false);
    }

    AbstractAllocator(OutboundHeader header, boolean trial) {
        this.header = header;
        this.trial = trial;
    }

    @Override
    public void verify() throws Exception {
        if (this.header.getStage().compareTo(OutboundStage.INITIALIZED) < 0) {
            throw CompositeException.getException("单据未初始化，不能分配月台", this.header, this.header.getOwner());
        }

        if (this.header.getStage().compareTo(OutboundStage.ARRANGED) < 0) {
            throw CompositeException.getException("单据未安排波次，不能分配月台", this.header, this.header.getOwner());
        }

        if (!this.trial && this.header.getStage().compareTo(OutboundStage.RELEASED) < 0) {
            throw CompositeException.getException("单据未下发，不能分配月台", this.header, this.header.getOwner());
        }

        if (this.header.getStage().compareTo(OutboundStage.STAGINGAREA_ALLOCATED) >= 0) {
            throw CompositeException.getException("月台已经分配", this.header, this.header.getOwner());
        }

        for (OutboundDetail detail : this.header.getDetails()) {
            //校验包装数
            if (Optional.of(detail.getGoods().getLargePackageQuantity()).orElse(BigDecimal.ZERO).compareTo(BigDecimal.ZERO) == 0) {
                throw CompositeException.getException("商品大包装数未设定", this.header, this.header.getOwner(), detail.getGoods());
            }

            //校验长宽高
            if (Optional.of(detail.getGoods().getLargePackageVolume()).orElse(BigDecimal.ZERO).compareTo(BigDecimal.ZERO) == 0) {
                throw CompositeException.getException("商品长宽高未设定", this.header, this.header.getOwner(), detail.getGoods());
            }
        }
    }

    @Override
    public void acquirePhysicalConfiguration() throws Exception {
        QStagingareaConfiguration configuration = QStagingareaConfiguration.stagingareaConfiguration;
        this.configuration = this.queryFactory
            .selectFrom(configuration)
            .where(
                configuration.warehouse.eq(this.header.getWarehouse())
                    .and(configuration.owner.eq(this.header.getOwner()))
                    .and(configuration.takegoodsMode.eq(this.header.getTakegoodsMode())))
            .fetchOne();
        if (this.configuration == null) {
            throw CompositeException.getException("月台配置参数未设定", this.header, this.header.getOwner());
        }

        if (this.configuration.getSmallQuantity().compareTo(BigDecimal.ZERO) == 0 || this.configuration.getLargeQuantity().compareTo(BigDecimal.ZERO) == 0) {
            throw CompositeException.getException("提货方式【" + this.header.getTakegoodsMode().name() + "】对应的月台件数未设定", this.header, this.header.getOwner());
        }

        QPhysicalConfiguration cfg = QPhysicalConfiguration.physicalConfiguration;
        PhysicalConfiguration physicalConfiguration = this.queryFactory.selectFrom(cfg)
            .where(
                cfg.warehouse.eq(this.header.getWarehouse())
                    .and(cfg.owner.eq(this.header.getOwner()))
                    .and(cfg.billCategory.eq(this.header.getCategory())))
            .fetchOne();
        if (physicalConfiguration == null) {
            throw CompositeException.getException("物理月台配置资料未设置单据对应的业主与类别", this.header, this.header.getOwner());
        }
    }

    @Override
    public void acquireVirtualConfiguration() throws Exception {
        this.acquireVirtualConfiguration(null);
    }

    @Override
    public void acquireVirtualConfiguration(Direction direction) throws Exception {
        QVirtualConfiguration cfg = QVirtualConfiguration.virtualConfiguration;
        VirtualConfiguration configuration = this.queryFactory.selectFrom(cfg)
            .where(
                cfg.warehouse.eq(this.header.getWarehouse())
                    .and(cfg.owner.isNull().or(cfg.owner.eq(this.header.getOwner())))
                    .and(cfg.available.eq(true))
                    .and(cfg.billCategory.isNull().or(cfg.billCategory.eq(this.header.getCategory())))
                    .and(cfg.takegoodsMode.isNull().or(cfg.takegoodsMode.eq(this.header.getTakegoodsMode())))
                    .and(cfg.saleType.isNull().or(cfg.saleType.eq(this.header.getSaleType())))
                    .and(cfg.stagingareaCategory.isNull().or(cfg.stagingareaCategory.eq(this.stagingarea.getCategory())))
                    .and(cfg.direction.isNull().or(cfg.direction.eq(direction))))
            .orderBy(cfg.owner.when(this.header.getOwner()).then(0).otherwise(1).asc())
            .orderBy(cfg.billCategory.desc())
            .orderBy(cfg.takegoodsMode.desc())
            .orderBy(cfg.direction.no.desc())
            .fetchOne();

        if (configuration != null) {
            this.stagingarea = configuration.getStagingarea();
        }
    }

    @Override
    public void acquireCategory() throws Exception {
        BigDecimal standardPieceVolume = this.getDecimalParameter(this.header.getWarehouse(), "BZJTJ");
        if (standardPieceVolume.toString().isEmpty()) {
            throw CompositeException.getException("标准件体积参数未设定", this.header, this.header.getOwner());
        }

        BigDecimal smallQuantity = this.getDecimalParameter(this.header.getWarehouse(), "XZCQJS");
        BigDecimal mediumQuantity = this.getDecimalParameter(this.header.getWarehouse(), "ZZCQJS");
        BigDecimal largeQuantity = this.getDecimalParameter(this.header.getWarehouse(), "DZCQJS");

        if (this.configuration.getAllocationMode() == StagingareaAllocationMode.VOLUMETRIC) {
            BigDecimal volume = this.header.getDetails().stream().map(detail -> detail.getFactQuantity().multiply(detail.getGoods().getSmallPackageVolume())).reduce(BigDecimal::multiply).get();

            //中药单据体积不能过大
            if (this.header.getCategory() == BillCategory.TRADITIONAL_CHINESE_MEDICINE) {
                volume = volume.min(standardPieceVolume.multiply(this.getDecimalParameter(this.header.getWarehouse(), "ZYZCQLIMIT")).multiply(mediumQuantity));
            }

            if (volume.divide(standardPieceVolume, RoundingMode.CEILING).compareTo(smallQuantity) <= 0) {
                this.stagingarea.setCategory(StagingareaCategory.MINIATURE);
                this.quantity = volume.divide(smallQuantity.multiply(standardPieceVolume), RoundingMode.CEILING).intValue();
            } else if (volume.divide(standardPieceVolume, RoundingMode.CEILING).compareTo(largeQuantity) > 0) {
                this.stagingarea.setCategory(StagingareaCategory.HEAVY);
                this.quantity = volume.divide(largeQuantity.multiply(standardPieceVolume), RoundingMode.CEILING).intValue();
            } else {
                this.stagingarea.setCategory(StagingareaCategory.MEDIUM);
                this.quantity = volume.divide(mediumQuantity.multiply(standardPieceVolume), RoundingMode.CEILING).intValue();
            }
        }

        if (this.configuration.getAllocationMode() == StagingareaAllocationMode.PIECEMEAL) {
            if (this.header.getEquivalentPieces().compareTo(smallQuantity) <= 0) {
                this.stagingarea.setCategory(StagingareaCategory.MINIATURE);
                this.quantity = this.header.getEquivalentPieces().divide(smallQuantity, RoundingMode.CEILING).intValue();
            } else if (this.header.getEquivalentPieces().compareTo(largeQuantity) > 0) {
                this.stagingarea.setCategory(StagingareaCategory.HEAVY);
                this.quantity = this.header.getEquivalentPieces().divide(largeQuantity, RoundingMode.CEILING).intValue();
            } else {
                this.stagingarea.setCategory(StagingareaCategory.MEDIUM);
                this.quantity = this.header.getEquivalentPieces().divide(mediumQuantity, RoundingMode.CEILING).intValue();
            }
        }
    }

    @Override
    public void acquireAvailableArea() throws Exception {
        if (this.stagingarea.getType() == StagingareaType.NORMAL) {
            this.stagingarea.setBillType(this.header.getType());
            this.stagingarea.setTakegoodsMode(this.header.getTakegoodsMode() == TakegoodsMode.GREEN_CHANNEL ? TakegoodsMode.SELF_SERVICE : this.header.getTakegoodsMode());
            this.stagingarea.setDirection(this.header.getAddress().getDirection());

            List<String> numbers = this.stagingareaRepository.acquireAvailableArea(this.stagingarea.getType(), this.stagingarea.getCategory(), this.stagingarea.getBillType(), this.stagingarea.getTakegoodsMode(), this.stagingarea.getOwners(), this.stagingarea.getDirection());

            for (int i = 0; i < numbers.size(); i++) {
                if (i + this.quantity - 1 > numbers.size()) {
                    this.stagingareas.clear();
                    break;
                }

                this.stagingareas = this.stagingareaRepository.acquireAvailableArea(numbers.get(i), numbers.get(i + this.quantity - 1), this.stagingarea.getType(), this.stagingarea.getCategory(), this.stagingarea.getBillType(), this.stagingarea.getTakegoodsMode(), this.stagingarea.getOwners(), this.stagingarea.getDirection());

                if (this.quantity == this.stagingareas.size()) {
                    break;
                } else {
                    this.stagingareas.clear();
                }
            }
        }

        if (this.stagingarea.getType() == StagingareaType.VIRTUAL) {
            this.stagingareas.add(this.stagingarea);
        }
    }

    @Override
    public void save() {
        this.header.setStage(OutboundStage.STAGINGAREA_ALLOCATED);
        this.header.setSourceStagingarea(this.stagingareas.get(0));
        this.header.setTargetStagingarea(this.stagingareas.get(this.stagingareas.size() - 1));
        this.headerRepository.save(this.header);

        this.stagingareas.forEach(s -> s.setAvailable(false));
        this.stagingareaRepository.saveAll(this.stagingareas);
    }

    @Override
    public void allocate() throws Exception {
        this.verify();

        this.acquirePhysicalConfiguration();

        this.acquireCategory();

        this.acquireVirtualConfiguration();

        this.acquireAvailableArea();

        if (this.trial) {
            return;
        }

        this.save();
    }
}