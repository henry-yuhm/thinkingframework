package org.thinking.logistics.order.initialization.domain;

public interface Initializer {
    void verify() throws Exception;

    void save();

    void initialize() throws Exception;
}