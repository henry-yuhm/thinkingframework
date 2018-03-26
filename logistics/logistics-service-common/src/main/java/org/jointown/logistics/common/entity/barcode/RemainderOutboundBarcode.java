package org.jointown.logistics.common.entity.barcode;

import org.jointown.logistics.common.entity.command.RemainderCommand;
import org.jointown.logistics.common.entity.support.GroupageMode;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class RemainderOutboundBarcode extends OutboundBarcode {
    private String groupageNo;//拼箱号

    private GroupageMode groupageMode;//拼箱方式

    @ManyToMany
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