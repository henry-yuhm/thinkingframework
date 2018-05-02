package org.thinking.logistics.order.dispatcher.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.BusinessBase;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.bill.OutboundHeader;
import org.thinking.logistics.services.core.domain.employee.Employee;
import org.thinking.logistics.services.core.domain.support.OutboundStage;
import org.thinking.logistics.services.core.repository.bill.OutboundHeaderRepository;

import javax.annotation.Resource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractDispatcher extends BusinessBase implements Dispatcher {
    protected OutboundHeader header;

    protected List<OutboundHeader> headers;

    @Resource
    private OutboundHeaderRepository headerRepository;

    public AbstractDispatcher(Employee operator, OutboundHeader header) {
        super(operator);
        this.header = header;
    }

    public AbstractDispatcher(Employee operator, List<OutboundHeader> headers) {
        super(operator);
        this.headers = headers;
    }

    @Override
    public void arrangeWave() throws Exception {
        //region 系统截单时间处理
        Date currentTime = Date.valueOf(LocalDate.now());
        Date trimTime = this.getDateParameter(this.header.getWarehouse(), "JD_TIME");
        if (currentTime.after(trimTime)) {
            throw CompositeException.getException("当前时间大于系统截单时间【" + trimTime.toString() + "】，不允许再安排波次");
        }
        //endregion

        if (this.headers.stream().filter(header -> header.getStage().compareTo(OutboundStage.ARRANGED) >= 0).findAny() != null) {
            throw CompositeException.getException("有单据已经安排波次，不能重复安排波次");
        }

        this.headers.forEach(header -> {
            header.setStage(OutboundStage.ARRANGED);
            header.setDispatchers(this.operator);
            header.setDispatcherTime(Date.valueOf(LocalDate.now()));
        });

        this.headerRepository.saveAll(this.headers);
    }

    @Override
    public void cancelWave() throws Exception {
        if (this.headers.stream().filter(header -> header.getStage().compareTo(OutboundStage.RELEASED) >= 0).findAny() != null) {
            throw CompositeException.getException("波次已有单据下发，不能取消");
        }

        this.headers.forEach(header -> {
            header.setStage(OutboundStage.INITIALIZED);
            header.setWave(null);
            header.setDispatchers(null);
            header.setDispatcherTime(null);
        });

        this.headerRepository.saveAll(this.headers);
    }

    @Override
    public void modifyWave() throws Exception {
        if (this.header.getStage().compareTo(OutboundStage.RELEASED) >= 0) {
            throw CompositeException.getException("单据已经下发，不能修改");
        }

        this.header.setStage(OutboundStage.INITIALIZED);
        this.header.setWave(null);
        this.header.setDispatchers(null);
        this.header.setDispatcherTime(null);

        this.headerRepository.save(this.header);
    }

    @Override
    public void releaseWave() throws Exception {
        this.headers.forEach(header -> {
            header.setStage(OutboundStage.RELEASED);
            header.setReleaseTime(Date.valueOf(LocalDate.now()));
        });

        this.headerRepository.saveAll(this.headers);
    }

    @Override
    public void releaseOrder() throws Exception {
        this.header.setStage(this.header.getStage() == OutboundStage.SUSPENDED ? OutboundStage.RESEND : OutboundStage.RELEASED);
        this.header.setDispatchers(this.operator);
        this.header.setDispatcherTime(Date.valueOf(LocalDate.now()));
        this.header.setReleaseTime(Date.valueOf(LocalDate.now()));

        this.headerRepository.save(this.header);
    }
}