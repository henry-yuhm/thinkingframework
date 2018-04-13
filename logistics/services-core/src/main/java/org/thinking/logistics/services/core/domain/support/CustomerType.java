package org.thinking.logistics.services.core.domain.support;

//客户类型
public enum CustomerType {
    PURCHASE("1", 1),
    SALE("2", 2);

    CustomerType(String name, int ordinal) {
    }
}