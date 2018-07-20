package org.thinking.sce.service.core.domain.barcode;

import lombok.*;
import org.hibernate.annotations.*;
import org.thinking.sce.service.core.domain.command.ReplenishmentCommand;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class ReplenishmentBarcode extends WorkBarcode {
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "barcode_id"), inverseJoinColumns = @JoinColumn(name = "command_id"))
    private Set<ReplenishmentCommand> commands;//指令
}