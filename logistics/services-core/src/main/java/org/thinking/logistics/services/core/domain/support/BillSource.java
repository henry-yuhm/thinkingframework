package org.thinking.logistics.services.core.domain.support;

//单据来源
public enum BillSource {
    ERP("ERP", 1),
    WMS("WMS", 2),
    TPL("TPL", 3),
    TMS("TMS", 4);

    BillSource(String name, int ordinal) {
    }
}