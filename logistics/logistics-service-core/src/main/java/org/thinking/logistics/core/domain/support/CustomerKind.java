package org.thinking.logistics.core.domain.support;

//客户类型
public enum CustomerKind {
    PURCHASE("1", 1),
    SALE("2", 2);

    CustomerKind(String name, int ordinal) {
    }
}