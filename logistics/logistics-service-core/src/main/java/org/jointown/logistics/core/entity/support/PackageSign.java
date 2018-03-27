package org.jointown.logistics.core.entity.support;

public enum PackageSign {
    WHOLE_PIECES("1", 1),
    REMAINDER("2", 2);

    PackageSign(String name, int ordinal) {
    }
}