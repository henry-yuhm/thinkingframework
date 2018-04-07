package org.thinking.logistics.core.domain.support;

public enum StorageStatus {
    QUALIFICATION("1", 1),//合格
    DISQUALIFICATION("2", 2);//不合格

    StorageStatus(String name, int ordinal) {
    }
}