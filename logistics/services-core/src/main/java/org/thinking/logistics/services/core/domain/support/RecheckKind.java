package org.thinking.logistics.services.core.domain.support;

//复核类型
public enum RecheckKind {
    ROLLER("1", 1),
    FLUENCY("2", 2),
    VIRTUALIZATION("3", 3),
    NONE("4", 4);

    RecheckKind(String name, int ordinal) {
    }
}