package org.jointown.logistics.core.entity.barcode;

import org.jointown.logistics.core.entity.command.RemainderCommand;
import org.jointown.logistics.core.entity.support.GroupageMode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class RemainderBarcode extends OutboundBarcode {
    private String groupageNo;//拼箱号

    private GroupageMode groupageMode;//拼箱方式

    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "barcode_id")}, inverseJoinColumns = {@JoinColumn(name = "command_id")})
    private Set<RemainderCommand> commands;//指令

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