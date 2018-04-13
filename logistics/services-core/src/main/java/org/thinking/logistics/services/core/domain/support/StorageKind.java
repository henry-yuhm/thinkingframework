package org.thinking.logistics.services.core.domain.support;

//存放类型
public enum StorageKind {
    SINGLE_GOODS("1", 1),
    SINGLE_BATCH("2", 2),
    MULTIPLE_GOODS("3", 3);

    StorageKind(String name, int ordinal) {
    }
}