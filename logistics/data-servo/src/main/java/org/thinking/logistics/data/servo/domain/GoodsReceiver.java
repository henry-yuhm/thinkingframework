package org.thinking.logistics.data.servo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GoodsReceiver extends AbstractReceiver {
    @Override
    public void verify() throws Exception {
    }

    @Override
    public void save() throws Exception {
    }
}