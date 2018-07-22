package org.thinking.sce.data.servo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.sce.service.core.domain.BusinessBase;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractReceiver extends BusinessBase implements Receiver {
    @Override
    public void receive() throws Exception {
        this.verify();

        this.save();
    }
}