package org.thinking.logistics.order.initializer.domain;

public interface Initiator {
    void verify() throws Exception;

    void save() throws Exception;

    void init() throws Exception;
}