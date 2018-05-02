package org.thinking.logistics.services.core.domain.table;

import lombok.Data;
import org.thinking.logistics.services.core.domain.Warehouse;
import org.thinking.logistics.services.core.domain.support.RecheckBufferType;

import javax.persistence.*;

@Entity
@Data
public class RecheckBuffer {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String no;//编号

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private RecheckTable table;//复核台

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private RecheckSlide slide;//复核滑道

    @Column(nullable = false)
    private RecheckBufferType type = RecheckBufferType.NORMAL;//类型

    @Column(nullable = false)
    private boolean available = true;//可用
}