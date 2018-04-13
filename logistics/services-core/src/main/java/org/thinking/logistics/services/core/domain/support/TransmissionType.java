package org.thinking.logistics.services.core.domain.support;

//传输类型
public enum TransmissionType {
    None("N", 0),//无
    TransferLine("S", 1),//输送线
    Elevator("D", 2);//电梯

    TransmissionType(String name, int ordinal) {
    }
}