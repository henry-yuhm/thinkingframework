package org.thinking.logistics.core.entity.command;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.core.entity.Platform;
import org.thinking.logistics.core.entity.barcode.WholeBarcode;
import org.thinking.logistics.core.entity.container.Pallet;
import org.thinking.logistics.core.entity.task.WholeTask;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class WholeCommand extends OutboundCommand {
    @ManyToOne(fetch = FetchType.LAZY)
    private WholeCommand parent;//父指令

    @ManyToOne(fetch = FetchType.LAZY)
    private WholeTask task;//作业任务

    @ManyToOne(fetch = FetchType.LAZY)
    private WholeBarcode barcode;//作业条码

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    @ManyToOne(fetch = FetchType.LAZY)
    private Platform platform;//站台
}