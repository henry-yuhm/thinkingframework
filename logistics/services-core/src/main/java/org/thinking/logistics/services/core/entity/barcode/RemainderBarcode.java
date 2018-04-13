package org.thinking.logistics.services.core.entity.barcode;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.support.GroupageKind;
import org.thinking.logistics.services.core.entity.command.RemainderCommand;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class RemainderBarcode extends OutboundBarcode {
    @Column(nullable = false)
    private String groupage;//拼箱

    @Column(nullable = false)
    private GroupageKind groupageKind;//拼箱类型

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "barcode_id"), inverseJoinColumns = @JoinColumn(name = "command_id"))
    private Set<RemainderCommand> commands = new LinkedHashSet<>();//指令
}