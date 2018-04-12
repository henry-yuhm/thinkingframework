package org.thinking.logistics.core.domain.support;

//包装类型
public enum PackageKind {
    WHOLE_PIECES("1", 1),
    REMAINDER("2", 2);

    PackageKind(String name, int ordinal) {
    }
}