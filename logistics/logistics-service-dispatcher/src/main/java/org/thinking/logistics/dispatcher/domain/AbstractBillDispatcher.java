package org.thinking.logistics.dispatcher.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.thinking.logistics.core.domain.BusinessAdapter;
import org.thinking.logistics.core.domain.support.OutboundStage;
import org.thinking.logistics.core.entity.Employee;
import org.thinking.logistics.core.entity.bill.OutboundHeader;
import org.thinking.logistics.core.repository.OutboundHeaderRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

public abstract class AbstractBillDispatcher extends BusinessAdapter implements BillDispatcher {
    OutboundHeader header;

    Set<OutboundHeader> headers;

    @Autowired
    private OutboundHeaderRepository headerRepository;

    public AbstractBillDispatcher(Employee operator, OutboundHeader header) {
        this.operator = operator;
        this.header = header;
    }

    public AbstractBillDispatcher(Employee operator, Set<OutboundHeader> headers) {
        this.operator = operator;
        this.headers = headers;
    }

    @Override
    public void arrangeWave() throws Exception {
        //region 系统截单时间处理
        Date currentTime = Date.valueOf(LocalDate.now());
        Date trimTime = this.getDateParameter(this.header.getWarehouse(), "JD_TIME");
        if (currentTime.after(trimTime)) {
            throw this.getException("当前时间大于系统截单时间【" + trimTime.toString() + "】，不允许再安排波次");
        }
        //endregion

        if (this.headers.stream().filter(header -> header.getStage().compareTo(OutboundStage.ARRANGED) >= 0).findAny() != null) {
            throw this.getException("有单据已经安排波次，不能重复安排波次");
        }

        this.headers.forEach(header -> {
            header.setStage(OutboundStage.ARRANGED);
            header.setDispatcher(this.operator);
            header.setDispatchTime(Date.valueOf(LocalDate.now()));
        });

        this.headerRepository.save(this.headers);
    }

    @Override
    public void cancelWave() throws Exception {
        if (this.headers.stream().filter(header -> header.getStage().compareTo(OutboundStage.RELEASED) >= 0).findAny() != null) {
            throw this.getException("波次已有单据下发，不能取消");
        }

        this.headers.forEach(header -> {
            header.setStage(OutboundStage.INITIALIZED);
            header.setWave(null);
            header.setDispatcher(null);
            header.setDispatchTime(null);
        });

        this.headerRepository.save(this.headers);
    }

    @Override
    public void modifyWave() throws Exception {
        if (this.header.getStage().compareTo(OutboundStage.RELEASED) >= 0) {
            throw this.getException("单据已经下发，不能修改");
        }

        this.header.setStage(OutboundStage.INITIALIZED);
        this.header.setWave(null);
        this.header.setDispatcher(null);
        this.header.setDispatchTime(null);

        this.headerRepository.save(this.header);
    }

    @Override
    public void releaseWave() throws Exception {
        this.headers.forEach(header -> {
            header.setStage(OutboundStage.RELEASED);
            header.setReleaseTime(Date.valueOf(LocalDate.now()));
        });

        this.headerRepository.save(this.headers);
    }

    @Override
    public void releaseBill() throws Exception {
        this.header.setStage(this.header.getStage() == OutboundStage.SUSPENDED ? OutboundStage.RESEND : OutboundStage.RELEASED);
        this.header.setDispatcher(this.operator);
        this.header.setDispatchTime(Date.valueOf(LocalDate.now()));
        this.header.setReleaseTime(Date.valueOf(LocalDate.now()));

        this.headerRepository.save(this.header);
    }
}