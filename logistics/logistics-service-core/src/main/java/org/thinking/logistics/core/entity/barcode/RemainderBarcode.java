package org.thinking.logistics.core.entity.barcode;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.core.domain.support.GroupageMode;
import org.thinking.logistics.core.entity.command.RemainderCommand;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class RemainderBarcode extends OutboundBarcode {
    @Column(nullable = false)
    private String groupageNo;//拼箱号

    @Column(nullable = false)
    private GroupageMode groupageMode;//拼箱方式

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "barcode_id"), inverseJoinColumns = @JoinColumn(name = "command_id"))
    private Set<RemainderCommand> commands = new LinkedHashSet<>();//指令
}