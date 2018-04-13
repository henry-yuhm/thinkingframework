package org.thinking.logistics.services.core.domain.support;

//复核暂存位类型
public enum RecheckBufferKind {
    NORMAL("1", 1),
    APPENDANT("2", 2);

    RecheckBufferKind(String name, int ordinal) {
    }
}