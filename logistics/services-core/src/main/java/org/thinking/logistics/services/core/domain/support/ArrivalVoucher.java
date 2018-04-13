package org.thinking.logistics.services.core.domain.support;

//到货凭证
public enum ArrivalVoucher {
    HOLD("1", 1),
    NONE("2", 2),
    DISQUALIFICATION("3", 3);

    ArrivalVoucher(String name, int ordinal) {
    }
}