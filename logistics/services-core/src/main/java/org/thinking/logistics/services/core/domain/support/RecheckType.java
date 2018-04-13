package org.thinking.logistics.services.core.domain.support;

//复核类型
public enum RecheckType {
    ROLLER("1", 1),
    FLUENCY("2", 2),
    VIRTUALIZATION("3", 3),
    NONE("4", 4);

    RecheckType(String name, int ordinal) {
    }
}