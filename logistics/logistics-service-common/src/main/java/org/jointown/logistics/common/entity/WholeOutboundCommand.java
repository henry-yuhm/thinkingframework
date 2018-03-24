package org.jointown.logistics.common.entity;

import javax.persistence.*;

@Entity
public class WholeOutboundCommand extends OutboundCommand {
    @OneToOne(fetch = FetchType.LAZY)
    private Pallet pallet;//托盘

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_obc_pfm"))
    private Platform platform;//站台
}