package org.thinking.logistics.stagingarea.allocation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.BusinessBase;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.common.Direction;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderDetail;
import org.thinking.logistics.services.core.domain.document.ShipmentOrderHeader;
import org.thinking.logistics.services.core.domain.stagingarea.Stagingarea;
import org.thinking.logistics.services.core.domain.stagingarea.StagingareaConfiguration;
import org.thinking.logistics.services.core.domain.stagingarea.VirtualConfiguration;
import org.thinking.logistics.services.core.domain.support.*;
import org.thinking.logistics.services.core.service.document.ShipmentOrderService;
import org.thinking.logistics.services.core.service.stagingarea.PhysicalConfigurationService;
import org.thinking.logistics.services.core.service.stagingarea.StagingareaConfigurationService;
import org.thinking.logistics.services.core.service.stagingarea.StagingareaService;
import org.thinking.logistics.services.core.service.stagingarea.VirtualConfigurationService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractAllocator extends BusinessBase implements Allocator {
    private ShipmentOrderHeader header;

    private StagingareaConfiguration configuration;

    private Stagingarea stagingarea = new Stagingarea();

    private List<Stagingarea> stagingareas = new LinkedList<>();

    private boolean trial;

    private int quantity = 0;

    @Resource
    private ShipmentOrderService orderService;

    @Resource
    private StagingareaService stagingareaService;

    @Resource
    private StagingareaConfigurationService stagingareaConfigurationService;

    @Resource
    private PhysicalConfigurationService physicalConfigurationService;

    @Resource
    private VirtualConfigurationService virtualConfigurationService;

    AbstractAllocator(ShipmentOrderHeader header) {
        this(header, false);
    }

    AbstractAllocator(ShipmentOrderHeader header, boolean trial) {
        this.header = header;
        this.trial = trial;
    }

    @Override
    public void verify() throws Exception {
        if (this.header.getShipmentStatus().compareTo(ShipmentStatus.INITIALIZED) < 0) {
            throw CompositeException.getException("单据未初始化，不能分配月台", this.header, this.header.getOwner());
        }

        if (this.header.getShipmentStatus().compareTo(ShipmentStatus.ARRANGED) < 0) {
            throw CompositeException.getException("单据未安排波次，不能分配月台", this.header, this.header.getOwner());
        }

        if (!this.trial && this.header.getShipmentStatus().compareTo(ShipmentStatus.RELEASED) < 0) {
            throw CompositeException.getException("单据未下发，不能分配月台", this.header, this.header.getOwner());
        }

        if (this.header.getShipmentStatus().compareTo(ShipmentStatus.STAGINGAREA_ALLOCATED) >= 0) {
            throw CompositeException.getException("月台已经分配", this.header, this.header.getOwner());
        }

        for (ShipmentOrderDetail detail : this.header.getDetails()) {
            //校验包装数
            if (Optional.of(detail.getItem().getLargePackageQuantity()).orElse(BigDecimal.ZERO).compareTo(BigDecimal.ZERO) == 0) {
                throw CompositeException.getException("商品大包装数未设定", this.header, this.header.getOwner(), detail.getItem());
            }

            //校验长宽高
            if (Optional.of(detail.getItem().getLargePackageVolume()).orElse(BigDecimal.ZERO).compareTo(BigDecimal.ZERO) == 0) {
                throw CompositeException.getException("商品长宽高未设定", this.header, this.header.getOwner(), detail.getItem());
            }
        }
    }

    @Override
    public void acquirePhysicalConfiguration() throws Exception {
        this.configuration = this.stagingareaConfigurationService.acquire(this.header, true);

        this.stagingarea.setType(this.physicalConfigurationService.acquire(this.header, true).getStagingareaType());
    }

    @Override
    public void acquireVirtualConfiguration() throws Exception {
        this.acquireVirtualConfiguration(null);
    }

    @Override
    public void acquireVirtualConfiguration(Direction direction) throws Exception {
        VirtualConfiguration configuration = this.virtualConfigurationService.acquire(this.header, this.stagingarea.getCategory(), direction);

        if (configuration != null) {
            this.stagingarea = configuration.getStagingarea();
        }
    }

    @Override
    public void acquireCategory() throws Exception {
        BigDecimal standardPieceVolume = this.getDecimalParameter(this.header.getWarehouse(), "标准件体积");
        if (standardPieceVolume.toString().isEmpty()) {
            throw CompositeException.getException("标准件体积参数未设定", this.header, this.header.getOwner());
        }

        BigDecimal smallQuantity = this.getDecimalParameter(this.header.getWarehouse(), "小型月台暂存区件数");
        BigDecimal mediumQuantity = this.getDecimalParameter(this.header.getWarehouse(), "中型月台暂存区件数");
        BigDecimal largeQuantity = this.getDecimalParameter(this.header.getWarehouse(), "大型月台暂存区件数");

        if (this.configuration.getAllocationMode() == StagingareaAllocateMode.VOLUMETRIC) {
            BigDecimal volume = this.header.getDetails().stream().map(detail -> detail.getActualQuantity().multiply(detail.getItem().getSmallPackageVolume())).reduce(BigDecimal::multiply).get();

            //中药单据体积不能过大
            if (this.header.getItemClass() == ItemClass.TRADITIONAL_CHINESE_MEDICINE) {
                volume = volume.min(standardPieceVolume.multiply(this.getDecimalParameter(this.header.getWarehouse(), "中药月台标准件数上限")).multiply(mediumQuantity));
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

        if (this.configuration.getAllocationMode() == StagingareaAllocateMode.PIECEMEAL) {
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
            this.stagingarea.setDocumentType(this.header.getDocumentType());
            this.stagingarea.setPickupMode(this.header.getPickupMode() == PickupMode.GREEN_CHANNEL ? PickupMode.SELF_SERVICE : this.header.getPickupMode());
            this.stagingarea.setDirection(this.header.getAddress().getDirection());

            List<String> numbers = this.stagingareaService.acquireAvailableArea(this.stagingarea);

            for (int i = 0; i < numbers.size(); i++) {
                if (i + this.quantity - 1 > numbers.size()) {
                    this.stagingareas.clear();
                    break;
                }

                this.stagingareas = this.stagingareaService.acquireAvailableArea(numbers.get(i), numbers.get(i + this.quantity - 1), this.stagingarea);

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
        this.header.setShipmentStatus(ShipmentStatus.STAGINGAREA_ALLOCATED);
        this.header.setSourceStagingarea(this.stagingareas.get(0));
        this.header.setTargetStagingarea(this.stagingareas.get(this.stagingareas.size() - 1));
        this.orderService.getRepository().save(this.header);

        this.stagingareas.forEach(s -> s.setAvailable(false));
        this.stagingareaService.getRepository().saveAll(this.stagingareas);
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