package org.thinking.logistics.data.servo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseReturnReceiver extends AbstractReceiver {
    @Override
    public void verify() throws Exception {
        super.verify();
    }

    @Override
    public void save() throws Exception {
        super.save();
    }
}