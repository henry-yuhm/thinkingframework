package org.jointown.logistics.core.entity.barcode;

import org.jointown.logistics.core.entity.command.RemainderCommand;
import org.jointown.logistics.core.entity.support.GroupageMode;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class RemainderBarcode extends OutboundBarcode {
    @Column(nullable = false)
    private String groupageNo;//拼箱号

    @Column(nullable = false)
    private GroupageMode groupageMode;//拼箱方式

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "barcode_id"), inverseJoinColumns = @JoinColumn(name = "command_id"))
    private Set<RemainderCommand> commands = new LinkedHashSet<>();//指令

    public RemainderBarcode() {
    }

    public String getGroupageNo() {
        return groupageNo;
    }

    public void setGroupageNo(String groupageNo) {
        this.groupageNo = groupageNo;
    }

    public GroupageMode getGroupageMode() {
        return groupageMode;
    }

    public void setGroupageMode(GroupageMode groupageMode) {
        this.groupageMode = groupageMode;
    }

    public Set<RemainderCommand> getCommands() {
        return commands;
    }

    public void setCommands(Set<RemainderCommand> commands) {
        this.commands = commands;
    }
}