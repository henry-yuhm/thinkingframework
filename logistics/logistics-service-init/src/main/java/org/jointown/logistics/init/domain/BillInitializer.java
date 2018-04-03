package org.jointown.logistics.init.domain;

public interface BillInitializer {
    void verify() throws Exception;

    void save();

    void initialize() throws Exception;
}