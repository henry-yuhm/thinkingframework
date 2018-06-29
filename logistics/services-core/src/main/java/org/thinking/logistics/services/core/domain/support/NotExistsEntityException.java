package org.thinking.logistics.services.core.domain.support;

public enum NotExistsEntityException {
    OWNER("业主不存在"),
    CUSTOMER("客户不存在"),
    ITEM("商品不存在"),
    DETAIL("明细不存在");

    NotExistsEntityException(String name) {
    }
}