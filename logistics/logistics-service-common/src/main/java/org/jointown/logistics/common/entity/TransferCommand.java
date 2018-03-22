package org.jointown.logistics.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class TransferCommand extends Command {
    @Column(nullable = false, precision = 12, scale = 5)
    private BigDecimal availableQuantity;
}