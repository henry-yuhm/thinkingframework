package org.thinking.logistics.services.core.entity.command;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.entity.Platform;
import org.thinking.logistics.services.core.entity.barcode.WholepiecesBarcode;
import org.thinking.logistics.services.core.entity.container.Pallet;
import org.thinking.logistics.services.core.entity.task.WholeTask;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class WholepiecesCommand extends OutboundCommand {
    @ManyToOne(fetch = FetchType.LAZY)
    private WholepiecesCommand parent;//父指令

    @ManyToOne(fetch = FetchType.LAZY)
    private WholeTask task;//作业任务

    @ManyToOne(fetch = FetchType.LAZY)
    private WholepiecesBarcode barcode;//作业条码

    @ManyToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    @ManyToOne(fetch = FetchType.LAZY)
    private Platform platform;//站台
}