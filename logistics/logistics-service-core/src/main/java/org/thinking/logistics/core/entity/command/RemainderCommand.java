package org.thinking.logistics.core.entity.command;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.core.entity.barcode.RemainderBarcode;
import org.thinking.logistics.core.entity.task.RemainderTask;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class RemainderCommand extends OutboundCommand {
    @ManyToOne(fetch = FetchType.LAZY)
    private RemainderCommand parent;//父指令

    @ManyToOne(fetch = FetchType.LAZY)
    private RemainderTask task;//作业任务

    @ManyToOne(fetch = FetchType.LAZY)
    private RemainderBarcode barcode;//作业条码

    private BigDecimal remainder = BigDecimal.ZERO;//余量
}