package org.thinking.sce.order.dispatcher.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.sce.service.core.domain.BusinessBase;
import org.thinking.sce.service.core.domain.CompositeException;
import org.thinking.sce.service.core.domain.document.ShipmentOrderHeader;
import org.thinking.sce.service.core.domain.employee.Employee;
import org.thinking.sce.service.core.domain.support.ShipmentStatus;
import org.thinking.sce.service.core.service.document.ShipmentOrderService;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractDispatcher extends BusinessBase implements Dispatcher {
    private ShipmentOrderHeader header;

    private List<ShipmentOrderHeader> headers;

    @Resource
    private ShipmentOrderService orderService;

    public AbstractDispatcher(Employee operator, ShipmentOrderHeader header) {
        super(operator);
        this.header = header;
    }

    public AbstractDispatcher(Employee operator, List<ShipmentOrderHeader> headers) {
        super(operator);
        this.headers = headers;
    }

    @Override
    public void arrangeWave() throws Exception {
        //region 系统截单时间处理
        Instant currentTime = Instant.now();
        Instant trimTime = this.getInstantParameter(this.header.getWarehouse(), "截单时间");
        if (currentTime.isAfter(trimTime)) {
            throw CompositeException.getException("当前时间大于系统截单时间【" + trimTime.toString() + "】，不允许再安排波次");
        }
        //endregion

        if (this.headers.stream().filter(header -> header.getShipmentStatus().compareTo(ShipmentStatus.ARRANGED) >= 0).findAny() != null) {
            throw CompositeException.getException("有单据已经安排波次，不能重复安排波次");
        }

        this.headers.forEach(header -> {
            header.setShipmentStatus(ShipmentStatus.ARRANGED);
            header.setDispatchers(this.getOperator());
            header.setDispatcherTime(Instant.now());
        });

        this.orderService.getRepository().saveAll(this.headers);
    }

    @Override
    public void cancelWave() throws Exception {
        if (this.headers.stream().filter(header -> header.getShipmentStatus().compareTo(ShipmentStatus.RELEASED) >= 0).findAny() != null) {
            throw CompositeException.getException("波次已有单据下发，不能取消");
        }

        this.headers.forEach(header -> {
            header.setShipmentStatus(ShipmentStatus.INITIALIZED);
            header.setWave(null);
            header.setDispatchers(null);
            header.setDispatcherTime(null);
        });

        this.orderService.getRepository().saveAll(this.headers);
    }

    @Override
    public void modifyWave() throws Exception {
        if (this.header.getShipmentStatus().compareTo(ShipmentStatus.RELEASED) >= 0) {
            throw CompositeException.getException("单据已经下发，不能修改");
        }

        this.header.setShipmentStatus(ShipmentStatus.INITIALIZED);
        this.header.setWave(null);
        this.header.setDispatchers(null);
        this.header.setDispatcherTime(null);

        this.orderService.getRepository().save(this.header);
    }

    @Override
    public void releaseWave() throws Exception {
        this.headers.forEach(header -> {
            header.setShipmentStatus(ShipmentStatus.RELEASED);
            header.setReleaseTime(Instant.now());
        });

        this.orderService.getRepository().saveAll(this.headers);
    }

    @Override
    public void releaseOrder() throws Exception {
        this.header.setShipmentStatus(this.header.getShipmentStatus() == ShipmentStatus.SUSPENDED ? ShipmentStatus.RESEND : ShipmentStatus.RELEASED);
        this.header.setDispatchers(this.getOperator());
        this.header.setDispatcherTime(Instant.now());
        this.header.setReleaseTime(Instant.now());

        this.orderService.getRepository().save(this.header);
    }
}