package org.thinking.logistics.order.inversion.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.BusinessBase;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractInverser extends BusinessBase implements Inverser {
    @Override
    public void audit() {

    }
}