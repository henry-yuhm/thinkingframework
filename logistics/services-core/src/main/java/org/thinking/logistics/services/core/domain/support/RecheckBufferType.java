package org.thinking.logistics.services.core.domain.support;

//复核暂存位类型
public enum RecheckBufferType {
    NORMAL("1", 1),
    APPENDANT("2", 2);

    RecheckBufferType(String name, int ordinal) {
    }
}