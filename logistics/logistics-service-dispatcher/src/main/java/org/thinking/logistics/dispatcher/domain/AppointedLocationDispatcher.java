package org.thinking.logistics.dispatcher.domain;

import org.thinking.logistics.core.entity.Employee;
import org.thinking.logistics.core.entity.bill.OutboundHeader;

public class AppointedLocationDispatcher extends AbstractDispatcher {
    public AppointedLocationDispatcher(Employee operator, OutboundHeader header) {
        super(operator, header);
    }

    @Override
    public void releaseBill() throws Exception {
        this.header.isResended();

        this.header.setWave("0000000006");

        super.releaseBill();
    }
}