package org.thinking.logistics.services.core.domain.support;

//拣货设备
public enum OperationDevice {
    PC("PC", 1),
    PDA("PDA", 2),
    DPS("DPS", 3),
    TPC("TPC", 4);

    OperationDevice(String name, int ordinal) {
    }
}