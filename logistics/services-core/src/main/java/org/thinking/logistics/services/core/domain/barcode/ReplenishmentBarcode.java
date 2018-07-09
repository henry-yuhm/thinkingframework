package org.thinking.logistics.services.core.domain.barcode;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.command.ReplenishmentCommand;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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