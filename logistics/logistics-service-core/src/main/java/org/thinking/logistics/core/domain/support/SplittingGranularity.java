package org.thinking.logistics.core.domain.support;

//拆分粒度
public enum SplittingGranularity {
    REMAINDER("1", 1),//拆零
    MEDIUM_PACKAGE("2", 2),//中包装
    WHOLE_PACKAGE("3", 3),//大包装
    DECIMAL("4", 4);//小数

    SplittingGranularity(String name, int ordinal) {
    }
}