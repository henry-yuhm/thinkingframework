package org.thinking.sce.order.initializer.domain;

public interface Initiator {
    void verify() throws Exception;

    void save() throws Exception;

    void init() throws Exception;
}