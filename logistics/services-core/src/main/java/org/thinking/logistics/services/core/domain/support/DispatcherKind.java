package org.thinking.logistics.services.core.domain.support;

//调度类型
public enum DispatcherKind {
    AUTOMATIC("1", 1),
    APPOINTED("2", 2);

    DispatcherKind(String name, int ordinal) {
    }
}