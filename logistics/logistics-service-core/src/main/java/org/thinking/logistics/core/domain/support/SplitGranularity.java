package org.thinking.logistics.core.domain.support;

public enum SplitGranularity {
    REMAINDER_AVAILABLE("1", 1),
    UNSPLIT_MEDIUM_PACKAGE("2", 2),
    UNSPLIT_FULLLOAD_PACKAGE("3", 3),
    DECIMAL_AVAILABLE("4", 4);

    SplitGranularity(String name, int ordinal) {
    }
}