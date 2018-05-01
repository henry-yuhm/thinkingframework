package org.thinking.logistics.services.core.entity.barcode;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.thinking.logistics.services.core.domain.support.GroupageType;
import org.thinking.logistics.services.core.entity.SorterSlide;
import org.thinking.logistics.services.core.entity.command.OutboundCommand;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OutboundBarcode extends TaskBarcode {
    @Column(nullable = false)
    private boolean available = true;//可用

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private SorterSlide slide;//分拣滑道

    @Column(nullable = false)
    private boolean gathered = false;//集货

    @Column(nullable = false)
    private String groupage;//拼箱

    @Column(nullable = false)
    private GroupageType groupageType;//拼箱类型

    @OneToMany
    @JoinTable(joinColumns = @JoinColumn(name = "barcode_id"), inverseJoinColumns = @JoinColumn(name = "command_id"))
    private Set<OutboundCommand> commands = new LinkedHashSet<>();//指令
}