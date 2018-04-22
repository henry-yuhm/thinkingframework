package org.thinking.logistics.stagingarea.allocation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.BusinessBase;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.support.*;
import org.thinking.logistics.services.core.entity.Direction;
import org.thinking.logistics.services.core.entity.bill.OutboundDetail;
import org.thinking.logistics.services.core.entity.bill.OutboundHeader;
import org.thinking.logistics.services.core.entity.stagingarea.PhysicalStagingareaConfiguration;
import org.thinking.logistics.services.core.entity.stagingarea.Stagingarea;
import org.thinking.logistics.services.core.entity.stagingarea.StagingareaConfiguration;
import org.thinking.logistics.services.core.entity.stagingarea.VirtualStagingareaConfiguration;
import org.thinking.logistics.services.core.repository.bill.OutboundHeaderRepository;
import org.thinking.logistics.services.core.repository.stagingarea.PhysicalStagingareaConfigurationRepository;
import org.thinking.logistics.services.core.repository.stagingarea.StagingareaConfigurationRepository;
import org.thinking.logistics.services.core.repository.stagingarea.StagingareaRepository;
import org.thinking.logistics.services.core.repository.stagingarea.VirtualStagingareaConfigurationRepository;

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
    private StagingareaConfigurationRepository configurationRepository;

    @Resource
    private PhysicalStagingareaConfigurationRepository physicalConfigurationRepository;

    @Resource
    private VirtualStagingareaConfigurationRepository virtualConfigurationRepository;

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
        this.configuration = this.configurationRepository.getOne(new StagingareaConfiguration.PrimaryKey(this.header.getWarehouse(), this.header.getOwner(), this.header.getTakegoodsMode()));

        if (this.configuration.getSmallQuantity().compareTo(BigDecimal.ZERO) == 0 || this.configuration.getLargeQuantity().compareTo(BigDecimal.ZERO) == 0) {
            throw CompositeException.getException("提货方式【" + this.header.getTakegoodsMode().name() + "】对应的月台件数未设定", this.header, this.header.getOwner());
        }

        this.stagingarea.setType(this.physicalConfigurationRepository.getOne(new PhysicalStagingareaConfiguration.PrimaryKey(this.header.getWarehouse(), this.header.getOwner())).getConfigurations().stream().filter(cfg -> cfg.getBillCategory() == this.header.getCategory()).findFirst().get().getStagingareaType());

        if (this.stagingarea.getType() == null) {
            throw CompositeException.getException("物理月台配置资料未设置单据对应的业主与类别", this.header, this.header.getOwner());
        }
    }

    @Override
    public void acquireVirtualConfiguration() throws Exception {
        this.acquireVirtualConfiguration(null);
    }

    @Override
    public void acquireVirtualConfiguration(Direction direction) throws Exception {
        Optional<VirtualStagingareaConfiguration.Configuration> configuration = this.virtualConfigurationRepository.getOne(new VirtualStagingareaConfiguration.PrimaryKey(this.header.getWarehouse(), this.header.getOwner())).getConfigurations().stream().filter(cfg -> cfg.isAvailable() && cfg.getBillCategory() == this.header.getCategory() && cfg.getTakegoodsMode() == this.header.getTakegoodsMode() && cfg.getSaleType() == this.header.getSaleType() && cfg.getStagingareaCategory() == this.stagingarea.getCategory() && cfg.getDirection() == direction).findAny();

        if (configuration != null) {
            this.stagingarea = configuration.get().getStagingarea();
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

        if (this.configuration.getMode() == StagingareaAllocationMode.VOLUMETRIC) {
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

        if (this.configuration.getMode() == StagingareaAllocationMode.PIECEMEAL) {
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