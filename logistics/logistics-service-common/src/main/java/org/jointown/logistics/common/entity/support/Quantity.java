package org.jointown.logistics.common.entity.support;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.math.BigInteger;

@Embeddable
public class Quantity {
    private BigDecimal quantity = BigDecimal.ZERO;//数量

    private BigInteger pieces = BigInteger.ZERO;//件数

    private BigDecimal remainder = BigDecimal.ZERO;//余数

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigInteger getPieces() {
        return pieces;
    }

    public void setPieces(BigInteger pieces) {
        this.pieces = pieces;
    }

    public BigDecimal getRemainder() {
        return remainder;
    }

    public void setRemainder(BigDecimal remainder) {
        this.remainder = remainder;
    }
}