package org.thinking.logistics.services.core.domain.support;

public enum NotExistsEntityException {
    OWNER("业主资料不存在"),
    CUSTOMER("客户资料不存在");

    NotExistsEntityException(String name) {
    }
}