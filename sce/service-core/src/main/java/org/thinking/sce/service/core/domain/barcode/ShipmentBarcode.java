package org.thinking.sce.service.core.domain.barcode;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.sce.service.core.domain.command.ShipmentCommand;
import org.thinking.sce.service.core.domain.common.Sorter;
import org.thinking.sce.service.core.domain.support.GroupageType;

import javax.persistence.*;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShipmentBarcode extends WorkBarcode {
    @Column(nullable = false)
    private boolean available;//可用

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Sorter sorter;//分拣机

    @Column(nullable = false)
    private boolean gathered;//集货

    @Column(nullable = false)
    private String groupage;//拼箱

    @Column(nullable = false)
    private GroupageType groupageType;//拼箱类型

    @OneToMany
    @JoinTable(joinColumns = @JoinColumn(name = "barcode_id"), inverseJoinColumns = @JoinColumn(name = "command_id"))
    private Set<ShipmentCommand> commands;//指令
}