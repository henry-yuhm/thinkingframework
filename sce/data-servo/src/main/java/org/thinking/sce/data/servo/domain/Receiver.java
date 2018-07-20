package org.thinking.sce.data.servo.domain;

public interface Receiver {
    void verify() throws Exception;

    void save() throws Exception;

    void receive() throws Exception;
}