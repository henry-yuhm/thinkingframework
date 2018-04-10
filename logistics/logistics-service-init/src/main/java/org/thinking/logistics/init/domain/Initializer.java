package org.thinking.logistics.init.domain;

public interface Initializer {
    void verify() throws Exception;

    void save();

    void initialize() throws Exception;
}