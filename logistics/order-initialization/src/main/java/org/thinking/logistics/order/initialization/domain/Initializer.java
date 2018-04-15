package org.thinking.logistics.order.initialization.domain;

public interface Initializer {
    void verify() throws Exception;

    void save() throws Exception;

    void initialize() throws Exception;
}