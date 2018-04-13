package org.thinking.logistics.services.core.domain.support;

//包装类型
public enum PackageType {
    WHOLE_PIECES("1", 1),
    REMAINDER("2", 2);

    PackageType(String name, int ordinal) {
    }
}