package org.thinking.logistics.services.core.domain.support;

//效期类型
public enum ValidPeriodType {
    ALL("ALL", 0),
    OLD("OLD", 1),
    NEW("NEW", 2);

    ValidPeriodType(String name, int ordinal) {
    }
}