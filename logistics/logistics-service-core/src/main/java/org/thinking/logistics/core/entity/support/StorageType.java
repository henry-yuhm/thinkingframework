package org.thinking.logistics.core.entity.support;

public enum StorageType {
    SINGLE_GOODS("1", 1),
    SINGLE_BATCH("2", 2),
    MULTIPLE_GOODS("3", 3);

    StorageType(String name, int ordinal) {
    }
}