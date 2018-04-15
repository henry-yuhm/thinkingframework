package org.thinking.logistics.data.servo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.BusinessAdapter;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractReceiver extends BusinessAdapter implements Receiver {
    @Override
    public void verify() throws Exception {

    }

    @Override
    public void save() throws Exception {

    }

    @Override
    public void receive() throws Exception {
        this.verify();

        this.save();
    }
}