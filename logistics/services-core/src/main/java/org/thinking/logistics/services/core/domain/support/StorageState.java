package org.thinking.logistics.services.core.domain.support;

//存放状态
public enum StorageState {
    QUALIFICATION("1", 1),//合格
    DISQUALIFICATION("2", 2);//不合格

    StorageState(String name, int ordinal) {
    }
}